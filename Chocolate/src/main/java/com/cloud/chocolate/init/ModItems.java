package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	//public static Item palm_sign;
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
	{
        //palm_sign = registerItem(new SignItem((new Item.Properties()).maxStackSize(16).group(ItemGroup.DECORATIONS), ModBlocks.palm_sign, ModBlocks.palm_wall_sign), "palm_sign");
	}
    
    // Register new item
    public static Item registerItem(Item item, String name)
    {
    	item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}