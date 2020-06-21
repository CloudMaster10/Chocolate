package com.cloud.chocolate;

import com.cloud.chocolate.init.ModBlocks;
import com.cloud.chocolate.world.gen.feature.ModFeatures;
import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Chocolate.MOD_ID)
public class Chocolate
{
    //private static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "chocolate";
	public static Chocolate instance;

    public Chocolate()
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(Chocolate::commonInit);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(Chocolate::clientInit));

    	instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public static void commonInit(FMLCommonSetupEvent event)
	{	
    	// Place Features
		for (Biome biomeIn : ForgeRegistries.BIOMES.getValues())
		{
			if (biomeIn == Biomes.BEACH)
			{
				biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModFeatures.PALM_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.35F, 2))));
			}
		}
		
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
        
        // Set Strippable
        //AxeItem.BLOCK_STRIPPING_MAP.put(ModBlocks.palm_log, ModBlocks.stripped_palm_log);
        //AxeItem.BLOCK_STRIPPING_MAP.put(ModBlocks.palm_wood, ModBlocks.stripped_palm_wood);
        
        // Set Compostable
        ComposterBlock.CHANCES.put(ModBlocks.palm_sapling, 0.3F);
        ComposterBlock.CHANCES.put(ModBlocks.palm_fronds, 0.3F);
        
	}
    
    public static void clientInit(FMLClientSetupEvent event)
	{
    	BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        
        // Foliage Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefault(),
	        ModBlocks.palm_fronds);
        
        // Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(BlockState, null, null, tintIndex); }, 
        	ModBlocks.palm_fronds);
	}
    
}
