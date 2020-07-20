package com.cloud.chocolate.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.cloud.chocolate.init.ModFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class PalmTree extends Tree {
	  @Nullable
	  protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_)
	  {
		  return ModFeatures.PALM_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG);
	  }
}
