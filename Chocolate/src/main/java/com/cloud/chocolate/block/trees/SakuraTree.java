package com.cloud.chocolate.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.cloud.chocolate.init.ModFeatures;
import com.cloud.chocolate.world.biome.ModBiomeFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SakuraTree extends Tree {
	  @Nullable
	  protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_)
	  {
		  return randomIn.nextInt(5) == 0 ? ModFeatures.FANCY_SAKURA_TREE.withConfiguration(ModBiomeFeatures.FANCY_SAKURA_TREE_CONFIG) : Feature.NORMAL_TREE.withConfiguration(ModBiomeFeatures.SAKURA_TREE_CONFIG);
	  }
}
