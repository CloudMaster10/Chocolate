package com.cloud.chocolate.init;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.client.renderer.entity.ModBoatRenderer;
import com.cloud.chocolate.entity.item.ModBoatEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	public static EntityType<ModBoatEntity> boat;
	
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
	{
    	boat = EntityType.Builder.<ModBoatEntity>create(ModBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build(Chocolate.MOD_ID + ":boat"); //.setCustomClientFactory((spawnEntity, world) -> new ModBoatEntity(boat, world))
        registerEntity(boat, "boat");
	}
    
    // Register new entity
    private static EntityType<?> registerEntity(EntityType<?> entity, String name)
    {
        entity.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(entity);
        return entity;
    }
    
    // Set up renderers
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
	{
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends ModBoatEntity>)boat, ModBoatRenderer::new);
	}
}