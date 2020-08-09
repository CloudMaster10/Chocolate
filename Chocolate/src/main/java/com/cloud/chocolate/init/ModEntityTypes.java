package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.client.renderer.entity.FungalMooshroomEntityRenderer;
import com.cloud.chocolate.client.renderer.entity.ModBoatRenderer;
import com.cloud.chocolate.entity.item.ModBoatEntity;
import com.cloud.chocolate.entity.passive.FungalMooshroomEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.CowEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Chocolate.MOD_ID);
	
	public static final RegistryObject<EntityType<ModBoatEntity>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<ModBoatEntity>create(ModBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build("boat"));
	
	public static final RegistryObject<EntityType<FungalMooshroomEntity>> FUNGAL_MOOSHROOM = ENTITY_TYPES.register("fungal_mooshroom", () -> EntityType.Builder.<FungalMooshroomEntity>create(FungalMooshroomEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build("fungal_mooshroom"));
	
	// Create entity data
	public static void addEntityAttributes()
	{
		GlobalEntityTypeAttributes.put(FUNGAL_MOOSHROOM.get(), CowEntity.func_234188_eI_().create());
	}
	
	// Set up renderers
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
	{
        RenderingRegistry.registerEntityRenderingHandler(BOAT.get(), ModBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FUNGAL_MOOSHROOM.get(), FungalMooshroomEntityRenderer::new);
	}
}