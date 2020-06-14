package com.cloud.chocolate.enchantment;

import java.util.List;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModEnchantments;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class SproutingEnchantment extends Enchantment
{

	public SproutingEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) 
	{
		super(rarityIn, typeIn, slots);
	}
	
	@Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 30;
    }
    
    @SubscribeEvent
    public static void onPlayerHarvestCrops(BlockEvent.BreakEvent event)
    {
    	if(event.getState().getBlock() instanceof CropsBlock)
        {
            ItemStack heldItem = event.getPlayer().getItemStackFromSlot(EquipmentSlotType.MAINHAND);
            if(!heldItem.isEmpty())
            {
	            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.sprouting, heldItem);
				if(level > 0)
				{
		            World world = event.getPlayer().getEntityWorld();
		            replantCrop(event.getState(), world, event.getPos(), event.getPlayer(), event.getPos(), level);
	            }
            }
        }
    }

    protected static void replantCrop(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockPos originalPos, int level)
    {
        if(state.getBlock() instanceof CropsBlock)
        {
            CropsBlock crop = (CropsBlock) state.getBlock();
            if(state.get(crop.getAgeProperty()) == crop.getMaxAge())
            {
	            ItemStack stack = crop.getItem(world, pos, state);
	            if(stack.getItem() instanceof BlockItem)
	            {
	                BlockItem blockItem = (BlockItem) stack.getItem();
	                if(blockItem.getBlock() instanceof CropsBlock)
	                {
	                    // Search for seeds in the crop block drop
	                	ItemStack seeds = ItemStack.EMPTY;
	                    List<ItemStack> drops = Block.getDrops(state, (ServerWorld) world, pos, null);
	                    for(ItemStack drop : drops)
	                    {
	                        if(drop.getItem() == stack.getItem())
	                        {
	                            seeds = drop.split(1);
	                            break;
	                        }
	                    }
	                    
	                    // If the crop block dropped no seeds, search the player's inventory
	                    if(seeds.isEmpty())
	                    {
	                    	for(int i = 0; i < player.inventory.getSizeInventory(); i++)
	                        {
	                            ItemStack inventoryStack = player.inventory.getStackInSlot(i);
	                            if(inventoryStack.getItem() == stack.getItem())
	                            {
	                                seeds = inventoryStack;
	                                break;
	                            }
	                        }
	                    }
	                    
	                    // Destroy the crop and drop the items in the world
	                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
	                    if(!player.isCreative())
	                    {
		                    drops.forEach(drop -> Block.spawnAsEntity(world, pos, drop));
		                    state.spawnAdditionalDrops(world, pos, ItemStack.EMPTY);
	                    }
	                    if(!pos.equals(originalPos))
	                    {
	                        world.playEvent(2001, pos, Block.getStateId(state));
	                    }
	                    
	                    // If seeds were found and they can be planted, replant the crop and remove a seed
	                    BlockState seedState = blockItem.getBlock().getDefaultState();
	                    if(!seeds.isEmpty() & blockItem.getBlock().isValidPosition(seedState, world, pos))
	                    {
	                        // Chance for planted crop to be planted at a higher age
	                    	int age = 0;
		                    double growthChance = Math.random();
		                    if(growthChance >= 1.0 - ((level - 1) * 0.05)) // Level 1: 0%, Level 2: 5%, Level 3: 10%
		                    {
		                    	age = 2;
		                    }
		                    else if(growthChance >= 1.0 - (level * 0.15)) // Level 1: 15%, Level 2: 25%, Level 3: 35%
		                    {
		                    	age = 1;
		                    }
	                    	
	                    	final int newAge = age;
	                    	
	                    	MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
	                        server.enqueue(new TickDelayedTask(0, () -> world.setBlockState(pos, seedState.with(BlockStateProperties.AGE_0_7, newAge), 3)));
	                        
	                        seeds.shrink(1);
	                    }
	                }
	            }
            }
        }
    }
}
