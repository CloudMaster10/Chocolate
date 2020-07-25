package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.client.renderer.entity.ModBoatRenderer;
import com.cloud.chocolate.entity.item.ModBoatEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Chocolate.MOD_ID);
	
	public static final RegistryObject<EntityType<ModBoatEntity>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<ModBoatEntity>create(ModBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build(new ResourceLocation(Chocolate.MOD_ID, "boat").toString()));
    
    // Set up renderers
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
	{
        RenderingRegistry.registerEntityRenderingHandler(BOAT.get(), ModBoatRenderer::new);
	}
}