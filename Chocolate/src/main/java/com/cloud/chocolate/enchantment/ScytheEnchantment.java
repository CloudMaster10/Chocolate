package com.cloud.chocolate.enchantment;

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
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class ScytheEnchantment extends Enchantment
{

	public ScytheEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots)
	{
		super(rarityIn, typeIn, slots);
		
	}

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 20;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return getMinEnchantability(enchantmentLevel) + 50;
    }
    
    @SubscribeEvent
    public static void onPlayerHarvestCrops(BlockEvent.BreakEvent event)
    {
        BlockState state = event.getState();
    	if(state.getBlock() instanceof CropsBlock)
        {
        	CropsBlock crop = (CropsBlock) event.getState().getBlock();
            if(state.get(crop.getAgeProperty()) == crop.getMaxAge())
            {
            	PlayerEntity player = event.getPlayer();
            	ItemStack heldItem = player.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
	            if(!heldItem.isEmpty())
	            {
		            if(EnchantmentHelper.getEnchantments(heldItem).containsKey(ModEnchantments.SCYTHE.get()))
			        {
			            // Harvest all mature crops in the 3x3 area surrounding pos and if the tool has sprouting, replant
			            World world = player.getEntityWorld();
			            BlockPos pos = event.getPos().add(-1, 0, -1);
			            BlockPos thisPos = pos;
			            BlockState thisState = state;
			            
			            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SPROUTING.get(), heldItem);
						if(level > 0)
						{
							for(int i = 0; i < 9; i++)
				            {
								thisPos = pos.add(i / 3, 0, i % 3);
								thisState = world.getBlockState(thisPos).getBlockState();
								SproutingEnchantment.replantCrop(thisState, world, thisPos, player, event.getPos(), level);
				            }
						}
						else
						{
							for(int i = 0; i < 9; i++)
				            {
								thisPos = pos.add(i / 3, 0, i % 3);
								thisState = world.getBlockState(thisPos).getBlockState();
								harvestCrop(thisState, world, thisPos, player, event.getPos());
				            }
						}
			        }
			    }
            }
        }
    }
    
    private static void harvestCrop(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockPos originalPos)
    {
    	if(state.getBlock() instanceof CropsBlock)
        {
            CropsBlock crop = (CropsBlock) state.getBlock();
            if(state.get(crop.getAgeProperty()) == crop.getMaxAge())
            {
	            // Destroy the crop and drop the items in the world
	            world.setBlockState(pos, Blocks.AIR.getDefaultState());
	            if(!player.isCreative())
	            {
	            	Block.spawnDrops(state, world, pos);
	            }
	            if(!pos.equals(originalPos))
	            {
	            	world.playEvent(2001, pos, Block.getStateId(state));
	            }
            }
        }
    }
}

