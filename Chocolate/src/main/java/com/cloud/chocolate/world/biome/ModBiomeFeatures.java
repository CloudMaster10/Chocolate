package com.cloud.chocolate.world.biome;

import com.cloud.chocolate.init.ModBlocks;
import com.cloud.chocolate.init.ModFeatures;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class ModBiomeFeatures
{
	private static final BlockState PALM_LOG = ModBlocks.palm_log.getDefaultState();
	private static final BlockState PALM_LEAVES = ModBlocks.palm_fronds.getDefaultState();
	private static final BlockState SAKURA_LOG = ModBlocks.sakura_log.getDefaultState();
	private static final BlockState SAKURA_LEAVES = ModBlocks.sakura_blossoms.getDefaultState();
	private static final BlockState SHORT_GRASS = ModBlocks.short_grass.getDefaultState();
	
	public static final TreeFeatureConfig PALM_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(PALM_LOG), new SimpleBlockStateProvider(PALM_LEAVES), null)).baseHeight(6).setSapling((net.minecraftforge.common.IPlantable)ModBlocks.palm_sapling).build();
	public static final TreeFeatureConfig SAKURA_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(2, 0))).baseHeight(4).heightRandA(2).foliageHeight(3).ignoreVines().setSapling((net.minecraftforge.common.IPlantable)ModBlocks.sakura_sapling).build();
	public static final TreeFeatureConfig FANCY_SAKURA_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)ModBlocks.sakura_sapling).build();
	public static final BlockClusterFeatureConfig SHORT_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SHORT_GRASS), new SimpleBlockPlacer())).tries(32).build();
	
	public static void addPalmTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModFeatures.PALM_TREE.withConfiguration(PALM_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addSakuraTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(ModFeatures.FANCY_SAKURA_TREE.withConfiguration(FANCY_SAKURA_TREE_CONFIG).withChance(0.33333334F)), ModFeatures.SAKURA_TREE.withConfiguration(SAKURA_TREE_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.25F, 1))));
	}
	
	public static void addShortGrass(Biome biomeIn, int frequency)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(SHORT_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
}