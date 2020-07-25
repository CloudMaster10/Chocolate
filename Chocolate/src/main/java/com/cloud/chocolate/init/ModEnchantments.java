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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments {
    
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Chocolate.MOD_ID);
	
	public static final RegistryObject<Enchantment> STOMPING = ENCHANTMENTS.register("stomping", () -> new StompingEnchantment(Rarity.UNCOMMON, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET}));
	
	public static final RegistryObject<Enchantment> SPROUTING = ENCHANTMENTS.register("sprouting", () -> new SproutingEnchantment(Rarity.UNCOMMON, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));
	public static final RegistryObject<Enchantment> CULTIVATOR = ENCHANTMENTS.register("cultivator", () -> new CultivatorEnchantment(Rarity.UNCOMMON, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));
	public static final RegistryObject<Enchantment> SCYTHE = ENCHANTMENTS.register("scythe", () -> new ScytheEnchantment(Rarity.RARE, ModEnchantmentType.HOE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND}));
}