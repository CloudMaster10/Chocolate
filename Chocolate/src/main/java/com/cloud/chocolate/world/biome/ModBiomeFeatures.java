package com.cloud.chocolate.world.biome;

import java.util.OptionalInt;

import com.cloud.chocolate.init.ModBlocks;
import com.cloud.chocolate.world.gen.feature.foliageplacer.PalmFoliagePlacer;
import com.cloud.chocolate.world.gen.feature.trunkplacer.LeaningTrunkPlacer;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModBiomeFeatures
{
	private static final BlockState PALM_LOG = ModBlocks.PALM_LOG.get().getDefaultState();
	private static final BlockState PALM_LEAVES = ModBlocks.PALM_FRONDS.get().getDefaultState();
	private static final BlockState SAKURA_LOG = ModBlocks.SAKURA_LOG.get().getDefaultState();
	private static final BlockState SAKURA_LEAVES = ModBlocks.SAKURA_BLOSSOMS.get().getDefaultState();
	private static final BlockState SHORT_GRASS = ModBlocks.SHORT_GRASS.get().getDefaultState();
	
	public static final BaseTreeFeatureConfig PALM_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(PALM_LOG), new SimpleBlockStateProvider(PALM_LEAVES), new PalmFoliagePlacer(3, 0, 0, 0), new LeaningTrunkPlacer(6, 0, 0), new TwoLayerFeature(1, 0, 1))).func_236700_a_().build();
	public static final BaseTreeFeatureConfig SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).func_236700_a_().build();
	public static final BaseTreeFeatureConfig FANCY_SAKURA_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(SAKURA_LOG), new SimpleBlockStateProvider(SAKURA_LEAVES), new FancyFoliagePlacer(2, 0, 4, 0, 4), new FancyTrunkPlacer(3, 11, 0), new TwoLayerFeature(0, 0, 0, OptionalInt.of(4)))).func_236700_a_().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
	public static final BlockClusterFeatureConfig SHORT_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(SHORT_GRASS), new SimpleBlockPlacer())).tries(32).build();
	
	public static void addPalmTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_236291_c_.withConfiguration(PALM_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
	}
	
	public static void addSakuraTrees(Biome biomeIn)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Feature.field_236291_c_.withConfiguration(FANCY_SAKURA_TREE_CONFIG).withChance(0.33333334F)), Feature.field_236291_c_.withConfiguration(SAKURA_TREE_CONFIG))).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.25F, 1))));
	}
	
	public static void addShortGrass(Biome biomeIn, int frequency)
	{
		biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(SHORT_GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(frequency))));
	}
}