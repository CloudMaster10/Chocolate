package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions
{
	public static Potion haste;
	public static Potion long_haste;
	public static Potion strong_haste;
	
	public static Potion mining_fatigue;
	public static Potion long_mining_fatigue;
	public static Potion strong_mining_fatigue;
	
	public static Potion resistance;
	public static Potion long_resistance;
	public static Potion strong_resistance;
	
	public static Potion wither;
	public static Potion long_wither;
	public static Potion strong_wither;
	
	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event)
	{
		haste = registerPotion(new Potion(new EffectInstance(Effects.HASTE, 1800)), "haste");
		long_haste = registerPotion(new Potion(new EffectInstance(Effects.HASTE, 3600)), "long_haste");
		strong_haste = registerPotion(new Potion(new EffectInstance(Effects.HASTE, 900, 1)), "strong_haste");
		
		mining_fatigue = registerPotion(new Potion(new EffectInstance(Effects.MINING_FATIGUE, 1800)), "mining_fatigue");
		long_mining_fatigue = registerPotion(new Potion(new EffectInstance(Effects.MINING_FATIGUE, 3600)), "long_mining_fatigue");
		strong_mining_fatigue = registerPotion(new Potion(new EffectInstance(Effects.MINING_FATIGUE, 900, 1)), "strong_mining_fatigue");
		
		resistance = registerPotion(new Potion(new EffectInstance(Effects.RESISTANCE, 1800)), "resistance");
		long_resistance = registerPotion(new Potion(new EffectInstance(Effects.RESISTANCE, 3600)), "long_resistance");
		strong_resistance = registerPotion(new Potion(new EffectInstance(Effects.RESISTANCE, 900, 1)), "strong_resistance");
		
		wither = registerPotion(new Potion(new EffectInstance(Effects.WITHER, 600)), "wither");
		long_wither = registerPotion(new Potion(new EffectInstance(Effects.WITHER, 1200)), "long_wither");
		strong_wither = registerPotion(new Potion(new EffectInstance(Effects.WITHER, 300, 1)), "strong_wither");
	}
	
	public static Potion registerPotion(Potion potion, String name) {
		
		potion.setRegistryName(name);
		ForgeRegistries.POTION_TYPES.register(potion);
		
		return potion;
	}
	
}
