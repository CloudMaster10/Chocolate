package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.entity.item.ModBoatEntity;
import com.cloud.chocolate.item.ModBoatItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Chocolate.MOD_ID);

	//public static Item palm_sign;
	
	public static final RegistryObject<Item> PALM_BOAT = ITEMS.register("palm_boat", () -> new ModBoatItem(ModBoatEntity.Type.PALM, new Item.Properties().maxStackSize(1).group(ItemGroup.TRANSPORTATION)));
	public static final RegistryObject<Item> SAKURA_BOAT = ITEMS.register("sakura_boat", () -> new ModBoatItem(ModBoatEntity.Type.SAKURA, new Item.Properties().maxStackSize(1).group(ItemGroup.TRANSPORTATION)));

	//public static final RegistryObject<Item> FUNGAL_MOOSHROOM_SPAWN_EGG = ITEMS.register("fungal_mooshroom_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.FUNGAL_MOOSHROOM.get(), 7537672, 16737536, new Item.Properties().group(ItemGroup.MISC)));
}