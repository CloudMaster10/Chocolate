package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.block.ModAbstractSignBlock;
import com.cloud.chocolate.client.renderer.tileentity.ModSignTileEntityRenderer;
import com.cloud.chocolate.tileentity.ModSignTileEntity;
import com.google.common.base.Supplier;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Chocolate.MOD_ID);

	public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN = createTileEntity("sign", ModSignTileEntity::new, () -> collectSignBlocks());
	
	/*
	public static TileEntityType<ModSignTileEntity> sign;
	
    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
	{
    	sign = (TileEntityType<ModSignTileEntity>) registerTileEntity(TileEntityType.Builder.<ModSignTileEntity>create(ModSignTileEntity::new, collectSignBlocks()), "sign");
	}
    
    
    // Old register new tile entity
    private static <T extends TileEntity> TileEntityType<T> registerTileEntity(TileEntityType.Builder<T> builder, String name) {
    	Type<?> type = null;

        try
        {
           type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, name);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
           if (SharedConstants.developmentMode)
           {
              throw illegalargumentexception;
           }
        }
    	
        TileEntityType<T> tileEntity = builder.build(type);
        
    	tileEntity.setRegistryName(name);
        ForgeRegistries.TILE_ENTITIES.register(tileEntity);
        return tileEntity;
    }
    
    
    // Register new tile entity
    private static TileEntityType<?> registerTileEntity(TileEntityType.Builder<?> builder, String name)
    {
    	Type<?> type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, name);
    	
    	try
    	{
    		type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, name);
    	}
    	catch (IllegalArgumentException illegalargumentexception)
    	{
    		if (SharedConstants.developmentMode)
    		{
    			throw illegalargumentexception;
    		}
    	}
    	
        TileEntityType<?> tileEntity = builder.build(type);
        
    	tileEntity.setRegistryName(name);
        ForgeRegistries.TILE_ENTITIES.register(tileEntity);
        return tileEntity;
    }
    */
	
	public static <T extends TileEntity> RegistryObject<TileEntityType<T>> createTileEntity(String name, Supplier<? extends T> tileEntity, Supplier<Block[]> validBlocks)
	{
		return TILE_ENTITIES.register(name, () -> new TileEntityType<>(tileEntity, Sets.newHashSet(validBlocks.get()), null));
	}
    
    private static Block[] collectSignBlocks()
    {
		return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> block instanceof ModAbstractSignBlock).toArray(Block[]::new);
	}
    
    // Set up renderers
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
	{
    	//ClientRegistry.bindTileEntityRenderer((TileEntityType<? extends ModSignTileEntity>)sign, ModSignTileEntityRenderer::new);
    	//ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN.get(), ModSignTileEntityRenderer::new);
	}
}