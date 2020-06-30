package com.cloud.chocolate;

import com.cloud.chocolate.init.ModBlocks;
import com.cloud.chocolate.init.ModPotions;
import com.cloud.chocolate.world.biome.ModDefaultBiomeFeatures;
import com.cloud.chocolate.world.gen.feature.ModFeatures;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.ILightReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Chocolate.MOD_ID)
public class Chocolate
{
    //private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "chocolate";
	public static Chocolate instance;

    public Chocolate()
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(Chocolate::commonInit);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(Chocolate::clientInit));

    	instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public static void commonInit(FMLCommonSetupEvent event)
	{	
    	// Place Features
    	ModDefaultBiomeFeatures.addPalmTrees(Biomes.BEACH);
    	
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.PLAINS, 10);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DESERT, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.FOREST, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.TAIGA, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SWAMP, 10);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.RIVER, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.FROZEN_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.FROZEN_RIVER, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_TUNDRA, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BEACH, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DESERT_HILLS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.WOODED_HILLS, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.TAIGA_HILLS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MOUNTAIN_EDGE, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.JUNGLE, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.JUNGLE_HILLS, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.JUNGLE_EDGE, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DEEP_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.STONE_SHORE, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_BEACH, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BIRCH_FOREST, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BIRCH_FOREST_HILLS, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DARK_FOREST, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA_HILLS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.GIANT_TREE_TAIGA, 14);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.GIANT_TREE_TAIGA_HILLS, 14);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.WOODED_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SAVANNA, 40);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SAVANNA_PLATEAU, 40);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BADLANDS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.WOODED_BADLANDS_PLATEAU, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BADLANDS_PLATEAU, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.WARM_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.LUKEWARM_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.COLD_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DEEP_WARM_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DEEP_LUKEWARM_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DEEP_COLD_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DEEP_FROZEN_OCEAN, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SUNFLOWER_PLAINS, 10);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DESERT_LAKES, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.GRAVELLY_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.FLOWER_FOREST, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.TAIGA_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SWAMP_HILLS, 10);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.ICE_SPIKES, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MODIFIED_JUNGLE, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MODIFIED_JUNGLE_EDGE, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.TALL_BIRCH_FOREST, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.TALL_BIRCH_HILLS, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.DARK_FOREST_HILLS, 4);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.GIANT_SPRUCE_TAIGA, 14);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.GIANT_SPRUCE_TAIGA_HILLS, 14);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SHATTERED_SAVANNA, 40);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.SHATTERED_SAVANNA_PLATEAU, 40);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.ERODED_BADLANDS, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.MODIFIED_BADLANDS_PLATEAU, 2);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BAMBOO_JUNGLE, 50);
    	ModDefaultBiomeFeatures.addShortGrass(Biomes.BAMBOO_JUNGLE_HILLS, 50);
    	
		// Set Flammable
		FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFireInfo(ModBlocks.palm_fronds, 30, 60);
        fireblock.setFireInfo(ModBlocks.palm_log, 5, 5);
        fireblock.setFireInfo(ModBlocks.palm_wood, 5, 5);
        fireblock.setFireInfo(ModBlocks.stripped_palm_log, 5, 5);
        fireblock.setFireInfo(ModBlocks.stripped_palm_wood, 5, 5);
        fireblock.setFireInfo(ModBlocks.palm_planks, 5, 20);
        fireblock.setFireInfo(ModBlocks.palm_slab, 5, 20);
        fireblock.setFireInfo(ModBlocks.palm_stairs, 5, 20);
        fireblock.setFireInfo(ModBlocks.palm_fence, 5, 20);
        fireblock.setFireInfo(ModBlocks.palm_fence_gate, 5, 20);
        
        // Set Strippable
        //AxeItem.BLOCK_STRIPPING_MAP.put(ModBlocks.palm_log, ModBlocks.stripped_palm_log);
        //AxeItem.BLOCK_STRIPPING_MAP.put(ModBlocks.palm_wood, ModBlocks.stripped_palm_wood);
        
        // Set Compostable
        ComposterBlock.CHANCES.put(ModBlocks.palm_sapling, 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.palm_fronds, 0.3F);
        
        // Create Brewing Recipies
        PotionBrewing.addMix(Potions.AWKWARD, Items.NAUTILUS_SHELL, ModPotions.haste);
        PotionBrewing.addMix(ModPotions.haste, Items.REDSTONE, ModPotions.long_haste);
        PotionBrewing.addMix(ModPotions.haste, Items.GLOWSTONE_DUST, ModPotions.strong_haste);
        PotionBrewing.addMix(ModPotions.haste, Items.FERMENTED_SPIDER_EYE, ModPotions.mining_fatigue);
        PotionBrewing.addMix(ModPotions.long_haste, Items.FERMENTED_SPIDER_EYE, ModPotions.long_mining_fatigue);
        PotionBrewing.addMix(ModPotions.strong_haste, Items.FERMENTED_SPIDER_EYE, ModPotions.strong_mining_fatigue);
        PotionBrewing.addMix(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotions.resistance);
        PotionBrewing.addMix(ModPotions.resistance, Items.REDSTONE, ModPotions.long_resistance);
        PotionBrewing.addMix(ModPotions.resistance, Items.GLOWSTONE_DUST, ModPotions.strong_resistance);
        PotionBrewing.addMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.wither);
        PotionBrewing.addMix(ModPotions.wither, Items.REDSTONE, ModPotions.long_wither);
        PotionBrewing.addMix(ModPotions.wither, Items.GLOWSTONE_DUST, ModPotions.strong_wither);
	}
    
    public static void clientInit(FMLClientSetupEvent event)
	{
    	// Foliage Coloring
    	BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        
        // Block Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefault(),
	        ModBlocks.palm_fronds);
        
        blockColors.register((state, world, pos, tintIndex) ->
        	world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
        	ModBlocks.short_grass);
        
        // Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(BlockState, null, null, tintIndex); }, 
        		ModBlocks.palm_fronds, ModBlocks.short_grass);
	}
    
}
