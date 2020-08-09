package com.cloud.chocolate.events;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.entity.passive.FungalMooshroomEntity;
import com.cloud.chocolate.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class MooshroomConversion
{
	public static final HashMap<UUID, Integer> CONVERSION_TIME_MAP = new HashMap<UUID, Integer>();
	
	@SubscribeEvent
	public static void manageConversion(LivingUpdateEvent event)
	{
		if(event.getEntity() instanceof MobEntity)
		{
			MobEntity entity = (MobEntity) event.getEntity();
			if(entity.getType() == EntityType.MOOSHROOM)
			{
				World world = entity.getEntityWorld();
				Biome biome = world.getBiome(entity.getPositionUnderneath());
				BlockState blockstate = world.getBlockState(entity.getPositionUnderneath());
				
				UUID id = entity.getUniqueID();
				
				if(CONVERSION_TIME_MAP.get(id) == null)
				{
					// If entity is not in the map, check if it should be added
					if(conversionCheck(biome, blockstate) != null)
					{
						CONVERSION_TIME_MAP.put(id, 0);
					}
				}
				else
				{
					// If entity is in the map, check if it chould be removed or converted
					FungalMooshroomEntity.Type toConvert = conversionCheck(biome, blockstate);
					
					if(toConvert == null)
					{
						CONVERSION_TIME_MAP.remove(id);
					}
					else if(CONVERSION_TIME_MAP.get(id) > 1200)
					{
						if (entity.isAlive())
						{
							// Convert Mooshroom into Fungal Mooshroom (func_233656_b_)
							FungalMooshroomEntity fungalMooshroomEntity = ModEntityTypes.FUNGAL_MOOSHROOM.get().create(world);
							fungalMooshroomEntity.copyLocationAndAnglesFrom(entity);
							fungalMooshroomEntity.setChild(entity.isChild());
							fungalMooshroomEntity.setNoAI(entity.isAIDisabled());
							
							
							fungalMooshroomEntity.setFungalMooshroomType(toConvert);
							
							if (entity.hasCustomName())
							{
								fungalMooshroomEntity.setCustomName(entity.getCustomName());
								fungalMooshroomEntity.setCustomNameVisible(entity.isCustomNameVisible());
							}

							if (entity.isNoDespawnRequired())
							{
								fungalMooshroomEntity.enablePersistence();
							}
							
							fungalMooshroomEntity.setInvulnerable(entity.isInvulnerable());
							
							world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, SoundCategory.AMBIENT, 0.4F, 2.0F);
							world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_COW_AMBIENT, SoundCategory.AMBIENT, 0.4F, 0.75F);
							
							world.addEntity(fungalMooshroomEntity);
							entity.remove();
							
							fungalMooshroomEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 0));
						}
						else
						{
							CONVERSION_TIME_MAP.remove(id);
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void updateConversionMap(TickEvent.ServerTickEvent event)
	{
		if(!CONVERSION_TIME_MAP.isEmpty())
		{
			for(Entry<UUID, Integer> entry : CONVERSION_TIME_MAP.entrySet())
			{
				entry.setValue(entry.getValue() + 1);
			}
		}
	}
	
	private static FungalMooshroomEntity.Type conversionCheck(Biome biome, BlockState blockstate)
	{
		if(biome == Biomes.CRIMSON_FOREST && (blockstate == Blocks.CRIMSON_NYLIUM.getDefaultState() || blockstate == Blocks.NETHER_WART_BLOCK.getDefaultState()))
		{
			return FungalMooshroomEntity.Type.CRIMSON;
		}
		
		if(biome == Biomes.WARPED_FOREST && (blockstate == Blocks.WARPED_NYLIUM.getDefaultState() || blockstate == Blocks.WARPED_WART_BLOCK.getDefaultState()))
		{
			return FungalMooshroomEntity.Type.WARPED;
		}
		
		return null;
	}
}
