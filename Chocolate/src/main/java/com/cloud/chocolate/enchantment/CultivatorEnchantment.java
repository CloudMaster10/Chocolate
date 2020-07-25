package com.cloud.chocolate.enchantment;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModEnchantments;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class CultivatorEnchantment extends Enchantment
{
	public CultivatorEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots)
	{
		super(rarityIn, typeIn, slots);
		HoeItem.HOE_LOOKUP.put(Blocks.PODZOL, Blocks.FARMLAND.getDefaultState());
		HoeItem.HOE_LOOKUP.put(Blocks.MYCELIUM, Blocks.FARMLAND.getDefaultState());
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
    public static void onUseHoe(UseHoeEvent event)
    {
        ItemUseContext context = event.getContext();
        int affectedBlocks = till(context.getWorld(), context.getPos(), context.getFace(), context.getItem(), context.getPlayer());
        
        // Damage the hoe by the number of blocks tilled
        if(affectedBlocks > 0 && context.getPlayer() != null)
        {
            context.getItem().damageItem(affectedBlocks - 1, context.getPlayer(), player -> player.sendBreakAnimation(context.getHand()));
            event.setResult(Event.Result.ALLOW);
        }
    }

    private static int till(World world, BlockPos eventPos, Direction face, ItemStack stack, PlayerEntity player)
    {
        if(!stack.isEmpty())
        {
	        if(EnchantmentHelper.getEnchantments(stack).containsKey(ModEnchantments.CULTIVATOR.get()))
	        {
		        
		        if(face != Direction.DOWN && !player.isCrouching())
		        {
		            // Till all blocks in the 3x3 area surrounding pos
		        	int maxBlocks = stack.getMaxDamage() - stack.getDamage();
		            int affectedBlocks = 0;
		            
		            eventPos = eventPos.add(-1, 0, -1);
		            for(int i = 0; i < 9 && i < maxBlocks; i++)
		            {
		                // If there is no solid block above this pos, and it is found in HOE_LOOKUP, till the block
		            	BlockPos thisPos = eventPos.add(i / 3, 0, i % 3);
		                boolean air = world.isAirBlock(thisPos.up());
		                boolean replaceable = world.getBlockState(thisPos.up()).getMaterial().isReplaceable();
		                if(air || replaceable)
		                {
		                    BlockState groundState = HoeItem.HOE_LOOKUP.get(world.getBlockState(thisPos).getBlock());
		                    if(groundState != null)
		                    {
		                        world.setBlockState(thisPos, groundState, 11);
		                        affectedBlocks++;
		                        if(!air)
		                        {
		                            world.setBlockState(thisPos.up(), Blocks.AIR.getDefaultState());
		                        }
		                    }
		                }
		            }
		            // If any block in range was tilled, play the hoe till sound
		            if(affectedBlocks > 0)
		            {
		                world.playSound(player, eventPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		                return affectedBlocks;
		            }
		        }
	        }
        }
        
        return 0;
    }
}