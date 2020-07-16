package com.cloud.chocolate.world.gen.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class ModFeatures
{
	public static final Feature<TreeFeatureConfig> PALM_TREE = new PalmTreeFeature(TreeFeatureConfig::deserializeFoliage, false);
}
