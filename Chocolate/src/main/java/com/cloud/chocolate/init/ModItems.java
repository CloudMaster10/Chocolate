package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.entity.item.ModBoatEntity;
import com.cloud.chocolate.item.ModBoatItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Chocolate.MOD_ID);

	//public static Item palm_sign;
	
	public static final RegistryObject<Item> PALM_BOAT = ITEMS.register("palm_boat", () -> new ModBoatItem(ModBoatEntity.Type.PALM, new Item.Properties().maxStackSize(1).group(ItemGroup.TRANSPORTATION)));
	public static final RegistryObject<Item> SAKURA_BOAT = ITEMS.register("sakura_boat", () -> new ModBoatItem(ModBoatEntity.Type.SAKURA, new Item.Properties().maxStackSize(1).group(ItemGroup.TRANSPORTATION)));
}
	