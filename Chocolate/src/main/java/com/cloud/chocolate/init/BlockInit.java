package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Chocolate.MOD_ID)
@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Bus.MOD)
public class BlockInit
{
	// Soul Sandstone
	public static final Block soul_sandstone = null;
	public static final Block chiseled_soul_sandstone = null;
	public static final Block cut_soul_sandstone = null;
	public static final Block smooth_soul_sandstone = null;
	public static final Block soul_sandstone_slab = null;
	public static final Block cut_soul_sandstone_slab = null;
	public static final Block smooth_soul_sandstone_slab = null;
	public static final Block soul_sandstone_wall = null;
	public static final Block soul_sandstone_stairs = null;
	public static final Block smooth_soul_sandstone_stairs = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{	
		// Soul Sandstone
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("soul_sandstone"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("chiseled_soul_sandstone"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("cut_soul_sandstone"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("smooth_soul_sandstone"));
		event.getRegistry().register(new SlabBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("soul_sandstone_slab"));
		event.getRegistry().register(new SlabBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("cut_soul_sandstone_slab"));
		event.getRegistry().register(new SlabBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("smooth_soul_sandstone_slab"));
		event.getRegistry().register(new WallBlock(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)).setRegistryName("soul_sandstone_wall"));
		// Soul Sandstone Stairs and Smooth Soul Sandstone Stairs
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event)
	{
		// Soul Sandstone
		event.getRegistry().register(new BlockItem(soul_sandstone, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("soul_sandstone"));
		event.getRegistry().register(new BlockItem(chiseled_soul_sandstone, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("chiseled_soul_sandstone"));
		event.getRegistry().register(new BlockItem(cut_soul_sandstone, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("cut_soul_sandstone"));
		event.getRegistry().register(new BlockItem(smooth_soul_sandstone, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("smooth_soul_sandstone"));
		event.getRegistry().register(new BlockItem(soul_sandstone_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("soul_sandstone_slab"));
		event.getRegistry().register(new BlockItem(cut_soul_sandstone_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("cut_soul_sandstone_slab"));
		event.getRegistry().register(new BlockItem(smooth_soul_sandstone_slab, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("smooth_soul_sandstone_slab"));
		event.getRegistry().register(new BlockItem(soul_sandstone_wall, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("soul_sandstone_wall"));
		// Soul Sandstone Stairs and Smooth Soul Sandstone Stairs
	}
}