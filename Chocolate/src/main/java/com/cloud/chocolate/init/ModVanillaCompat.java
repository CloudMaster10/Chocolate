package com.cloud.chocolate.init;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.cloud.chocolate.world.biome.ModBiomeFeatures;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModVanillaCompat
{
	public static void setup()
	{
		// Place Features
    	ModBiomeFeatures.addPalmTrees(Biomes.BEACH);
    	ModBiomeFeatures.addSakuraTrees(Biomes.FLOWER_FOREST);
    	
    	ModBiomeFeatures.addShortGrass(Biomes.PLAINS, 10);
    	ModBiomeFeatures.addShortGrass(Biomes.DESERT, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.FOREST, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.TAIGA, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SWAMP, 10);
    	ModBiomeFeatures.addShortGrass(Biomes.RIVER, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.FROZEN_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.FROZEN_RIVER, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_TUNDRA, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.BEACH, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.DESERT_HILLS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.WOODED_HILLS, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.TAIGA_HILLS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.MOUNTAIN_EDGE, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.JUNGLE, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.JUNGLE_HILLS, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.JUNGLE_EDGE, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.DEEP_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.STONE_SHORE, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_BEACH, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.BIRCH_FOREST, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.BIRCH_FOREST_HILLS, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.DARK_FOREST, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA_HILLS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.GIANT_TREE_TAIGA, 14);
    	ModBiomeFeatures.addShortGrass(Biomes.GIANT_TREE_TAIGA_HILLS, 14);
    	ModBiomeFeatures.addShortGrass(Biomes.WOODED_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SAVANNA, 40);
    	ModBiomeFeatures.addShortGrass(Biomes.SAVANNA_PLATEAU, 40);
    	ModBiomeFeatures.addShortGrass(Biomes.BADLANDS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.WOODED_BADLANDS_PLATEAU, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.BADLANDS_PLATEAU, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.WARM_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.LUKEWARM_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.COLD_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.DEEP_WARM_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.DEEP_LUKEWARM_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.DEEP_COLD_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.DEEP_FROZEN_OCEAN, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SUNFLOWER_PLAINS, 10);
    	ModBiomeFeatures.addShortGrass(Biomes.DESERT_LAKES, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.GRAVELLY_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.FLOWER_FOREST, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.TAIGA_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SWAMP_HILLS, 10);
    	ModBiomeFeatures.addShortGrass(Biomes.ICE_SPIKES, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.MODIFIED_JUNGLE, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.MODIFIED_JUNGLE_EDGE, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.TALL_BIRCH_FOREST, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.TALL_BIRCH_HILLS, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.DARK_FOREST_HILLS, 4);
    	ModBiomeFeatures.addShortGrass(Biomes.SNOWY_TAIGA_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.GIANT_SPRUCE_TAIGA, 14);
    	ModBiomeFeatures.addShortGrass(Biomes.GIANT_SPRUCE_TAIGA_HILLS, 14);
    	ModBiomeFeatures.addShortGrass(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.SHATTERED_SAVANNA, 40);
    	ModBiomeFeatures.addShortGrass(Biomes.SHATTERED_SAVANNA_PLATEAU, 40);
    	ModBiomeFeatures.addShortGrass(Biomes.ERODED_BADLANDS, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.MODIFIED_BADLANDS_PLATEAU, 2);
    	ModBiomeFeatures.addShortGrass(Biomes.BAMBOO_JUNGLE, 50);
    	ModBiomeFeatures.addShortGrass(Biomes.BAMBOO_JUNGLE_HILLS, 50);
    	
		// Set Flammable
		FireBlock fireblock = (FireBlock)Blocks.FIRE;
		
        fireblock.setFireInfo(ModBlocks.PALM_FRONDS.get(), 30, 60);
        fireblock.setFireInfo(ModBlocks.PALM_LOG.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.PALM_WOOD.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.STRIPPED_PALM_LOG.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.STRIPPED_PALM_WOOD.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.PALM_PLANKS.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.PALM_SLAB.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.PALM_STAIRS.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.PALM_FENCE.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.PALM_FENCE_GATE.get(), 5, 20);
        
        fireblock.setFireInfo(ModBlocks.SAKURA_BLOSSOMS.get(), 30, 60);
        fireblock.setFireInfo(ModBlocks.SAKURA_LOG.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.SAKURA_WOOD.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.STRIPPED_SAKURA_LOG.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.STRIPPED_SAKURA_WOOD.get(), 5, 5);
        fireblock.setFireInfo(ModBlocks.SAKURA_PLANKS.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.SAKURA_SLAB.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.SAKURA_STAIRS.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.SAKURA_FENCE.get(), 5, 20);
        fireblock.setFireInfo(ModBlocks.SAKURA_FENCE_GATE.get(), 5, 20);
        
        // Set Strippable
        try
        {
			addStripping();
		}
        catch (NoSuchFieldException | IllegalAccessException e)
        {
			e.printStackTrace();
		}
        
        // Set Compostable
        ComposterBlock.CHANCES.put(ModBlocks.PALM_SAPLING.get(), 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.PALM_FRONDS.get(), 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.SAKURA_SAPLING.get(), 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.SAKURA_BLOSSOMS.get(), 0.3F);
        
        // Create Brewing Recipies
        PotionBrewing.addMix(Potions.AWKWARD, Items.NAUTILUS_SHELL, ModPotionTypes.HASTE.get());
        PotionBrewing.addMix(ModPotionTypes.HASTE.get(), Items.REDSTONE, ModPotionTypes.LONG_HASTE.get());
        PotionBrewing.addMix(ModPotionTypes.HASTE.get(), Items.GLOWSTONE_DUST, ModPotionTypes.STRONG_HASTE.get());
        
        PotionBrewing.addMix(ModPotionTypes.HASTE.get(), Items.FERMENTED_SPIDER_EYE, ModPotionTypes.MINING_FATIGUE.get());
        PotionBrewing.addMix(ModPotionTypes.LONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, ModPotionTypes.LONG_MINING_FATIGUE.get());
        PotionBrewing.addMix(ModPotionTypes.STRONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, ModPotionTypes.STRONG_MINING_FATIGUE.get());
        
        PotionBrewing.addMix(Potions.AWKWARD, Items.SHULKER_SHELL, ModPotionTypes.RESISTANCE.get());
        PotionBrewing.addMix(ModPotionTypes.RESISTANCE.get(), Items.REDSTONE, ModPotionTypes.LONG_RESISTANCE.get());
        PotionBrewing.addMix(ModPotionTypes.RESISTANCE.get(), Items.GLOWSTONE_DUST, ModPotionTypes.STRONG_RESISTANCE.get());
        
        PotionBrewing.addMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotionTypes.WITHER.get());
        PotionBrewing.addMix(ModPotionTypes.WITHER.get(), Items.REDSTONE, ModPotionTypes.LONG_WITHER.get());
        PotionBrewing.addMix(ModPotionTypes.WITHER.get(), Items.GLOWSTONE_DUST, ModPotionTypes.STRONG_WITHER.get());
	}
	
	private static void addStripping() throws NoSuchFieldException, IllegalAccessException
	{
	    Field map = ObfuscationReflectionHelper.findField(AxeItem.class, "field_203176_a" /*"BLOCK_STRIPPING_MAP"*/ );

	    Field modifiersField = Field.class.getDeclaredField("modifiers");
	    modifiersField.setAccessible(true);
	    modifiersField.setInt(map, map.getModifiers() & ~Modifier.FINAL);

	    map.setAccessible(true);
	    Map<Block, Block> strip_map = (Map<Block, Block>) map.get(null);
	    HashMap<Block, Block> new_map = new HashMap<>(strip_map);
	    
	    new_map.put(ModBlocks.PALM_LOG.get(), ModBlocks.STRIPPED_PALM_LOG.get());
	    new_map.put(ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_WOOD.get());
	    new_map.put(ModBlocks.SAKURA_LOG.get(), ModBlocks.STRIPPED_SAKURA_LOG.get());
	    new_map.put(ModBlocks.SAKURA_WOOD.get(), ModBlocks.STRIPPED_SAKURA_WOOD.get());
	    
	    map.set(null, new_map);
	}
}
