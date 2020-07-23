package com.cloud.chocolate.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.cloud.chocolate.world.biome.ModBiomeFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class PalmTree extends Tree {
	  @Nullable
	  protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_)
	  {
		  return Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.PALM_TREE_CONFIG);
	  }
}