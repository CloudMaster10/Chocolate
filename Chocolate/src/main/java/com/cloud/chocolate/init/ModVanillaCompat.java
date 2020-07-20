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
        
        fireblock.setFireInfo(ModBlocks.sakura_blossoms, 30, 60);
        fireblock.setFireInfo(ModBlocks.sakura_log, 5, 5);
        fireblock.setFireInfo(ModBlocks.sakura_wood, 5, 5);
        fireblock.setFireInfo(ModBlocks.stripped_sakura_log, 5, 5);
        fireblock.setFireInfo(ModBlocks.stripped_sakura_wood, 5, 5);
        fireblock.setFireInfo(ModBlocks.sakura_planks, 5, 20);
        fireblock.setFireInfo(ModBlocks.sakura_slab, 5, 20);
        fireblock.setFireInfo(ModBlocks.sakura_stairs, 5, 20);
        fireblock.setFireInfo(ModBlocks.sakura_fence, 5, 20);
        fireblock.setFireInfo(ModBlocks.sakura_fence_gate, 5, 20);
        
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
        ComposterBlock.CHANCES.put(ModBlocks.palm_sapling, 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.palm_fronds, 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.sakura_sapling, 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.sakura_blossoms, 0.3F);
        
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
	
	private static void addStripping() throws NoSuchFieldException, IllegalAccessException
	{
	    Field map = ObfuscationReflectionHelper.findField(AxeItem.class, "field_203176_a" /*"BLOCK_STRIPPING_MAP"*/ );

	    Field modifiersField = Field.class.getDeclaredField("modifiers");
	    modifiersField.setAccessible(true);
	    modifiersField.setInt(map, map.getModifiers() & ~Modifier.FINAL);

	    map.setAccessible(true);
	    Map<Block, Block> strip_map = (Map<Block, Block>) map.get(null);
	    HashMap<Block, Block> new_map = new HashMap<>(strip_map);
	    
	    new_map.put(ModBlocks.palm_log, ModBlocks.stripped_palm_log);
	    new_map.put(ModBlocks.palm_wood, ModBlocks.stripped_palm_wood);
	    new_map.put(ModBlocks.sakura_log, ModBlocks.stripped_sakura_log);
	    new_map.put(ModBlocks.sakura_wood, ModBlocks.stripped_sakura_wood);
	    
	    map.set(null, new_map);
	}
}
