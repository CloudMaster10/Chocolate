package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.entity.item.ModBoatEntity;
import com.cloud.chocolate.item.ModBoatItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	//public static Item palm_sign;
	public static Item palm_boat;
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
        //palm_sign = registerItem(new SignItem((new Item.Properties()).maxStackSize(16).group(ItemGroup.DECORATIONS), ModBlocks.palm_standing_sign, ModBlocks.palm_wall_sign), "palm_sign");
    	palm_boat = registerItem(new ModBoatItem(ModBoatEntity.Type.PALM, (new Item.Properties()).maxStackSize(1).group(ItemGroup.TRANSPORTATION)), "palm_boat");
	}
    
    // Register new item
    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}