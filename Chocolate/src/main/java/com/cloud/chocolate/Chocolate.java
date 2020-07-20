package com.cloud.chocolate;

import com.cloud.chocolate.client.renderer.tileentity.ModSignTileEntityRenderer;
import com.cloud.chocolate.init.ModBlocks;
import com.cloud.chocolate.init.ModEntities;
import com.cloud.chocolate.init.ModTileEntities;
import com.cloud.chocolate.init.ModVanillaCompat;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        
        // Testing
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
    }
    
    public static void commonInit(FMLCommonSetupEvent event)
	{	
    	ModVanillaCompat.setup();
	}
    
    public static void clientInit(FMLClientSetupEvent event)
	{
    	// Rendering Set-up
    	ModEntities.registerRendering();
    	ModTileEntities.registerRendering();
    	
    	//ClientRegistry.bindTileEntityRenderer(ModTileEntities.sign, ModSignTileEntityRenderer::new);
    	ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN.get(), ModSignTileEntityRenderer::new);
    	
    	// Foliage Coloring
    	BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        
        // Block Coloring
        blockColors.register((state, world, pos, tintIndex) ->
	        world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefault(),
	        ModBlocks.palm_fronds);
        
        blockColors.register((state, world, pos, tintIndex) ->
        	world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.get(0.5D, 1.0D),
        	ModBlocks.short_grass);
        
        //blockColors.register((state, world, pos, tintIndex) -> 16754619, ModBlocks.sakura_blossoms);
        
        // Item Coloring
        itemColors.register((stack, tintIndex) -> {
            BlockState BlockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(BlockState, null, null, tintIndex); }, 
        		ModBlocks.palm_fronds, ModBlocks.short_grass);
        
        //itemColors.register((stack, tintIndex) -> {return 16754619; }, ModBlocks.sakura_blossoms);
	}
    
}