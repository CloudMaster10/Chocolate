package com.cloud.chocolate.block.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.cloud.chocolate.world.biome.ModBiomeFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class SakuraTree extends Tree {
	  @Nullable
	  protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_)
	  {
		  return randomIn.nextInt(5) == 0 ? Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.FANCY_SAKURA_TREE_CONFIG) : Feature.field_236291_c_.withConfiguration(ModBiomeFeatures.SAKURA_TREE_CONFIG);
	  }
}
