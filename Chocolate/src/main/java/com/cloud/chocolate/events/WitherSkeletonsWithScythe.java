package com.cloud.chocolate.events;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class WitherSkeletonsWithScythe
{
	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinWorldEvent event)
	{
		Entity entity = event.getEntity();
		if(entity instanceof WitherSkeletonEntity)
		{
			if(Math.random() < 0.0625)
			{
				entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.SCYTHE.get()));
			}
		}
	}
}