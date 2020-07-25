package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotionTypes
{
	public static final DeferredRegister<Potion> POTION_TYPES = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Chocolate.MOD_ID);
	
	public static final RegistryObject<Potion> HASTE = POTION_TYPES.register("haste", () -> new Potion(new EffectInstance(Effects.HASTE, 1800)));
	public static final RegistryObject<Potion> LONG_HASTE = POTION_TYPES.register("long_haste", () -> new Potion(new EffectInstance(Effects.HASTE, 3600)));
	public static final RegistryObject<Potion> STRONG_HASTE = POTION_TYPES.register("strong_haste", () -> new Potion(new EffectInstance(Effects.HASTE, 900, 1)));
	
	public static final RegistryObject<Potion> MINING_FATIGUE = POTION_TYPES.register("mining_fatigue", () -> new Potion(new EffectInstance(Effects.MINING_FATIGUE, 1800)));
	public static final RegistryObject<Potion> LONG_MINING_FATIGUE = POTION_TYPES.register("long_mining_fatigue", () -> new Potion(new EffectInstance(Effects.MINING_FATIGUE, 3600)));
	public static final RegistryObject<Potion> STRONG_MINING_FATIGUE = POTION_TYPES.register("strong_mining_fatigue", () -> new Potion(new EffectInstance(Effects.MINING_FATIGUE, 900, 1)));
	
	public static final RegistryObject<Potion> RESISTANCE = POTION_TYPES.register("resistance", () -> new Potion(new EffectInstance(Effects.RESISTANCE, 1800)));
	public static final RegistryObject<Potion> LONG_RESISTANCE = POTION_TYPES.register("long_resistance", () -> new Potion(new EffectInstance(Effects.RESISTANCE, 3600)));
	public static final RegistryObject<Potion> STRONG_RESISTANCE = POTION_TYPES.register("strong_resistance", () -> new Potion(new EffectInstance(Effects.RESISTANCE, 900, 1)));
	
	public static final RegistryObject<Potion> WITHER = POTION_TYPES.register("wither", () -> new Potion(new EffectInstance(Effects.WITHER, 600)));
	public static final RegistryObject<Potion> LONG_WITHER = POTION_TYPES.register("long_wither", () -> new Potion(new EffectInstance(Effects.WITHER, 1200)));
	public static final RegistryObject<Potion> STRONG_WITHER = POTION_TYPES.register("strong_wither", () -> new Potion(new EffectInstance(Effects.WITHER, 300, 1)));
}
