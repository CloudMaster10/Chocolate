package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.enchantment.CultivatorEnchantment;
import com.cloud.chocolate.enchantment.ModEnchantmentType;
import com.cloud.chocolate.enchantment.ScytheEnchantment;
import com.cloud.chocolate.enchantment.SproutingEnchantment;
import com.cloud.chocolate.enchantment.StompingEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
    
	public static Enchantment stomping;
	
	public static Enchantment sprouting;
	public static Enchantment cultivator;
	public static Enchantment scythe;
	
	@SubscribeEvent
	public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
	{
		stomping = registerEnchantment(new StompingEnchantment(Rarity.UNCOMMON, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET}), "stomping");
		
		sprouting = registerEnchantment(new SproutingEnchantment(Rarity.UNCOMMON, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}), "sprouting");
		cultivator = registerEnchantment(new CultivatorEnchantment(Rarity.UNCOMMON, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}), "cultivator");
		scythe = registerEnchantment(new ScytheEnchantment(Rarity.RARE, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}), "scythe");
	}
	
	public static Enchantment registerEnchantment(Enchantment enchantment, String name) {
		
		enchantment.setRegistryName(name);
		ForgeRegistries.ENCHANTMENTS.register(enchantment);
		
		return enchantment;
	}
	
}