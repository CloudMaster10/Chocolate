package com.cloud.chocolate.enchantment;

import com.cloud.chocolate.Chocolate;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.HoeItem;

public class ModEnchantmentType {
	
	public static final EnchantmentType HOE = EnchantmentType.create(Chocolate.MOD_ID + ":hoe", item -> item.getItem() instanceof HoeItem);
	
}
