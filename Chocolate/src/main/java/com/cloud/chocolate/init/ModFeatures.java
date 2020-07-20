package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.world.gen.feature.PalmTreeFeature;

import net.minecraft.world.gen.feature.FancyTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
	public static final Feature<TreeFeatureConfig> PALM_TREE = new PalmTreeFeature(TreeFeatureConfig::deserializeFoliage);
	public static final Feature<TreeFeatureConfig> SAKURA_TREE = new TreeFeature(TreeFeatureConfig::deserializeFoliage);
	public static final Feature<TreeFeatureConfig> FANCY_SAKURA_TREE = new FancyTreeFeature(TreeFeatureConfig::deserializeFoliage);
	
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
    	registerFeature(PALM_TREE, "palm_tree");
    	registerFeature(SAKURA_TREE, "sakura_tree");
    	registerFeature(FANCY_SAKURA_TREE, "fancy_sakura_tree");
	}
    
    // Register new feature
    public static Feature<?> registerFeature(Feature<?> entry, String name)
    {
        entry.setRegistryName(name);
        ForgeRegistries.FEATURES.register(entry);
        return entry;
    }
}