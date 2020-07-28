package com.cloud.chocolate.client.renderer.entity;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.client.renderer.entity.layers.FungalMooshroomMushroomLayer;
import com.cloud.chocolate.entity.passive.FungalMooshroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class FungalMooshroomEntityRenderer extends MobRenderer<FungalMooshroomEntity, CowModel<FungalMooshroomEntity>> {
	
	public FungalMooshroomEntityRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CowModel<>(), 0.7F);
		this.addLayer(new FungalMooshroomMushroomLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(FungalMooshroomEntity entity)
	{
		return new ResourceLocation(Chocolate.MOD_ID, "textures/entity/" + entity.getFungalMooshroomType().getName() + "_mooshroom.png");
	}
}
