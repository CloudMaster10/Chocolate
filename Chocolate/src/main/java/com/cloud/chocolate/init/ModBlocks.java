package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.block.PalmFrondsBlock;
import com.cloud.chocolate.block.PalmSaplingBlock;
import com.cloud.chocolate.block.trees.PalmTree;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StainedGlassBlock;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Chocolate.MOD_ID)
@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{	
	// Soul Sandstone
	public static Block soul_sandstone;
	public static Block soul_sandstone_slab;
	public static Block soul_sandstone_stairs;
	public static Block soul_sandstone_wall;
	public static Block chiseled_soul_sandstone;
	public static Block cut_soul_sandstone;
	public static Block cut_soul_sandstone_slab;
	public static Block smooth_soul_sandstone;
	public static Block smooth_soul_sandstone_slab;
	public static Block smooth_soul_sandstone_stairs;
	
	public static Block bamboo_block;
	public static Block bamboo_slab;
	public static Block bamboo_stairs;
	public static Block bamboo_fence;
	public static Block bamboo_fence_gate;
	public static Block dried_bamboo_block;
	public static Block dried_bamboo_slab;
	public static Block dried_bamboo_stairs;
	public static Block dried_bamboo_fence;
	public static Block dried_bamboo_fence_gate;
	
	public static Block palm_sapling;
	public static Block palm_fronds;
	public static Block palm_log;
	public static Block palm_wood;
	public static Block stripped_palm_log;
	public static Block stripped_palm_wood;
	public static Block palm_planks;
	public static Block palm_slab;
	public static Block palm_stairs;
	public static Block palm_fence;
	public static Block palm_fence_gate;
	public static Block palm_door;
	public static Block palm_trapdoor;
	public static Block palm_button;
	public static Block palm_pressure_plate;
	//public static Block palm_sign;
	//public static Block palm_wall_sign;
	public static Block potted_palm_sapling;
	
	public static Block framed_glass;
	public static Block framed_white_stained_glass;
	public static Block framed_orange_stained_glass;
	public static Block framed_magenta_stained_glass;
	public static Block framed_light_blue_stained_glass;
	public static Block framed_yellow_stained_glass;
	public static Block framed_lime_stained_glass;
	public static Block framed_pink_stained_glass;
	public static Block framed_gray_stained_glass;
	public static Block framed_light_gray_stained_glass;
	public static Block framed_cyan_stained_glass;
	public static Block framed_purple_stained_glass;
	public static Block framed_blue_stained_glass;
	public static Block framed_brown_stained_glass;
	public static Block framed_green_stained_glass;
	public static Block framed_red_stained_glass;
	public static Block framed_black_stained_glass;
	public static Block framed_glass_pane;
	public static Block framed_white_stained_glass_pane;
	public static Block framed_orange_stained_glass_pane;
	public static Block framed_magenta_stained_glass_pane;
	public static Block framed_light_blue_stained_glass_pane;
	public static Block framed_yellow_stained_glass_pane;
	public static Block framed_lime_stained_glass_pane;
	public static Block framed_pink_stained_glass_pane;
	public static Block framed_gray_stained_glass_pane;
	public static Block framed_light_gray_stained_glass_pane;
	public static Block framed_cyan_stained_glass_pane;
	public static Block framed_purple_stained_glass_pane;
	public static Block framed_blue_stained_glass_pane;
	public static Block framed_brown_stained_glass_pane;
	public static Block framed_green_stained_glass_pane;
	public static Block framed_red_stained_glass_pane;
	public static Block framed_black_stained_glass_pane;
	
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		// Soul Sandstone
		soul_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN).hardnessAndResistance(0.8F).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS, "soul_sandstone");
		soul_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "soul_sandstone_slab");
		soul_sandstone_stairs = registerBlock(new StairsBlock(() -> soul_sandstone.getDefaultState(), Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "soul_sandstone_stairs");
		soul_sandstone_wall = registerBlock(new WallBlock(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "soul_sandstone_wall");
		chiseled_soul_sandstone = registerBlock(new Block(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "chiseled_soul_sandstone");
		cut_soul_sandstone = registerBlock(new Block(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "cut_soul_sandstone");
		cut_soul_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "cut_soul_sandstone_slab");
		smooth_soul_sandstone = registerBlock(new Block(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "smooth_soul_sandstone");
		smooth_soul_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "smooth_soul_sandstone_slab");
		smooth_soul_sandstone_stairs = registerBlock(new StairsBlock(() -> smooth_soul_sandstone.getDefaultState(), Block.Properties.from(soul_sandstone)), ItemGroup.BUILDING_BLOCKS, "smooth_soul_sandstone_stairs");
		
		// Bamboo
		bamboo_block = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.BAMBOO, MaterialColor.GRASS).hardnessAndResistance(1.0F).sound(SoundType.BAMBOO)), ItemGroup.BUILDING_BLOCKS, "bamboo_block");
		bamboo_slab = registerBlock(new SlabBlock(Block.Properties.from(bamboo_block)), ItemGroup.BUILDING_BLOCKS, "bamboo_slab");
		bamboo_stairs = registerBlock(new StairsBlock(() -> bamboo_block.getDefaultState(), Block.Properties.from(bamboo_block)), ItemGroup.BUILDING_BLOCKS, "bamboo_stairs");
		bamboo_fence = registerBlock(new FenceBlock(Block.Properties.from(bamboo_block)), ItemGroup.BUILDING_BLOCKS, "bamboo_fence");
		bamboo_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.from(bamboo_block)), ItemGroup.REDSTONE, "bamboo_fence_gate");
		dried_bamboo_block = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.BAMBOO, MaterialColor.SAND).hardnessAndResistance(1.0F).sound(SoundType.BAMBOO)), ItemGroup.BUILDING_BLOCKS, "dried_bamboo_block");
		dried_bamboo_slab = registerBlock(new SlabBlock(Block.Properties.from(dried_bamboo_block)), ItemGroup.BUILDING_BLOCKS, "dried_bamboo_slab");
		dried_bamboo_stairs = registerBlock(new StairsBlock(() -> dried_bamboo_block.getDefaultState(), Block.Properties.from(dried_bamboo_block)), ItemGroup.BUILDING_BLOCKS, "dried_bamboo_stairs");
		dried_bamboo_fence = registerBlock(new FenceBlock(Block.Properties.from(dried_bamboo_block)), ItemGroup.BUILDING_BLOCKS, "dried_bamboo_fence");
		dried_bamboo_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.from(dried_bamboo_block)), ItemGroup.REDSTONE, "dried_bamboo_fence_gate");
		
		// Palm
		palm_sapling = registerBlock(new PalmSaplingBlock(new PalmTree(), Block.Properties.from(Blocks.OAK_SAPLING)) {}, ItemGroup.DECORATIONS, "palm_sapling");
		palm_fronds = registerBlock(new PalmFrondsBlock(Block.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS, "palm_fronds");
		palm_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS, "palm_log");
		palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS, "palm_wood");
		stripped_palm_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS, "stripped_palm_log");
		stripped_palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.from(stripped_palm_log)), ItemGroup.BUILDING_BLOCKS, "stripped_palm_wood");
		palm_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), ItemGroup.BUILDING_BLOCKS, "palm_planks");
		palm_slab = registerBlock(new SlabBlock(Block.Properties.from(palm_planks)), ItemGroup.BUILDING_BLOCKS, "palm_slab");
		palm_stairs = registerBlock(new StairsBlock(() -> palm_planks.getDefaultState(), Block.Properties.from(palm_planks)), ItemGroup.BUILDING_BLOCKS, "palm_stairs");
		palm_fence = registerBlock(new FenceBlock(Block.Properties.from(palm_planks)), ItemGroup.BUILDING_BLOCKS, "palm_fence");
		palm_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.from(palm_planks)), ItemGroup.REDSTONE, "palm_fence_gate");
		palm_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()) {}, ItemGroup.REDSTONE, "palm_door");
		palm_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()) {}, ItemGroup.REDSTONE, "palm_trapdoor");
		palm_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)) {}, ItemGroup.REDSTONE, "palm_button");
		palm_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)) {}, ItemGroup.REDSTONE, "palm_pressure_plate");
		potted_palm_sapling = registerBlock(new FlowerPotBlock(palm_sapling, Block.Properties.from(Blocks.FLOWER_POT)), "potted_palm_sapling");
		//potted_palm_sapling = registerBlock(new FlowerPotBlock(null, () -> palm_sapling, Block.Properties.from(Blocks.FLOWER_POT)), "potted_palm_sapling");
		
		// Framed Glass
		framed_glass = registerBlock(new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_glass");
		framed_white_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.WHITE, Block.Properties.create(Material.GLASS, DyeColor.WHITE).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_white_stained_glass");
		framed_orange_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.ORANGE, Block.Properties.create(Material.GLASS, DyeColor.ORANGE).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_orange_stained_glass");
		framed_magenta_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.MAGENTA, Block.Properties.create(Material.GLASS, DyeColor.MAGENTA).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_magenta_stained_glass");
		framed_light_blue_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.LIGHT_BLUE, Block.Properties.create(Material.GLASS, DyeColor.LIGHT_BLUE).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_light_blue_stained_glass");
		framed_yellow_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.YELLOW, Block.Properties.create(Material.GLASS, DyeColor.YELLOW).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_yellow_stained_glass");
		framed_lime_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.LIME, Block.Properties.create(Material.GLASS, DyeColor.LIME).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_lime_stained_glass");
		framed_pink_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.PINK, Block.Properties.create(Material.GLASS, DyeColor.PINK).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_pink_stained_glass");
		framed_gray_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.GRAY, Block.Properties.create(Material.GLASS, DyeColor.GRAY).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_gray_stained_glass");
		framed_light_gray_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.LIGHT_GRAY, Block.Properties.create(Material.GLASS, DyeColor.LIGHT_GRAY).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_light_gray_stained_glass");
		framed_cyan_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.CYAN, Block.Properties.create(Material.GLASS, DyeColor.CYAN).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_cyan_stained_glass");
		framed_purple_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.PURPLE, Block.Properties.create(Material.GLASS, DyeColor.PURPLE).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_purple_stained_glass");
		framed_blue_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.BLUE, Block.Properties.create(Material.GLASS, DyeColor.BLUE).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_blue_stained_glass");
		framed_brown_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.BROWN, Block.Properties.create(Material.GLASS, DyeColor.BROWN).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_brown_stained_glass");
		framed_green_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.GREEN, Block.Properties.create(Material.GLASS, DyeColor.GREEN).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_green_stained_glass");
		framed_red_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.RED, Block.Properties.create(Material.GLASS, DyeColor.RED).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_red_stained_glass");
		framed_black_stained_glass = registerBlock(new StainedGlassBlock(DyeColor.BLACK, Block.Properties.create(Material.GLASS, DyeColor.BLACK).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.BUILDING_BLOCKS, "framed_black_stained_glass");
		framed_glass_pane = registerBlock(new PaneBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()) {}, ItemGroup.DECORATIONS, "framed_glass_pane");
		framed_white_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.WHITE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_white_stained_glass_pane");
		framed_orange_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.ORANGE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_orange_stained_glass_pane");
		framed_magenta_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.MAGENTA, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_magenta_stained_glass_pane");
		framed_light_blue_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.LIGHT_BLUE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_light_blue_stained_glass_pane");
		framed_yellow_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.YELLOW, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_yellow_stained_glass_pane");
		framed_lime_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.LIME, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_lime_stained_glass_pane");
		framed_pink_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.PINK, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_pink_stained_glass_pane");
		framed_gray_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.GRAY, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_gray_stained_glass_pane");
		framed_light_gray_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.LIGHT_GRAY, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_light_gray_stained_glass_pane");
		framed_cyan_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.CYAN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_cyan_stained_glass_pane");
		framed_purple_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.PURPLE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_purple_stained_glass_pane");
		framed_blue_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.BLUE, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_blue_stained_glass_pane");
		framed_brown_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.BROWN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_brown_stained_glass_pane");
		framed_green_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.GREEN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_green_stained_glass_pane");
		framed_red_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.RED, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_red_stained_glass_pane");
		framed_black_stained_glass_pane = registerBlock(new StainedGlassPaneBlock(DyeColor.BLACK, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.6F).sound(SoundType.GLASS).notSolid()), ItemGroup.DECORATIONS, "framed_black_stained_glass_pane");
		
		if (FMLEnvironment.dist == Dist.CLIENT)
		{
			RenderTypeLookup.setRenderLayer(palm_sapling, RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(palm_fronds, RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(palm_door, RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(palm_trapdoor, RenderType.getCutout());
			RenderTypeLookup.setRenderLayer(potted_palm_sapling, RenderType.getCutout());
			
			RenderTypeLookup.setRenderLayer(framed_glass, RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(framed_white_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_orange_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_magenta_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_light_blue_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_yellow_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_lime_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_pink_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_gray_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_light_gray_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_cyan_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_purple_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_blue_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_brown_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_green_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_red_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_black_stained_glass, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_glass_pane, RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(framed_white_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_orange_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_magenta_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_light_blue_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_yellow_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_lime_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_pink_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_gray_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_light_gray_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_cyan_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_purple_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_blue_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_brown_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_green_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_red_stained_glass_pane, RenderType.getTranslucent());
			RenderTypeLookup.setRenderLayer(framed_black_stained_glass_pane, RenderType.getTranslucent());
		}
	}
	
	// Register new block and item block
	public static Block registerBlock(Block block, ItemGroup itemGroup, String name)
	{	
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(itemGroup));
        
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        
        return block;
    }
	
	// Register new block for an existing item
	public static Block registerBlock(Block block, BlockItem itemBlock, String name)
	{
	    block.setRegistryName(name);
	    ForgeRegistries.BLOCKS.register(block);
	
	    if (itemBlock != null)
        {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

	    return block;
    }
	
	// Register new block and item block without putting it into creative inventory
	public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(null));
        
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        
        return block;
    }
	
}