package com.cloud.chocolate.events;

import java.util.List;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class BonemealUsesShortGrass {
	
	@SubscribeEvent
	public static void onBonemeal(BonemealEvent event)
	{
		if (event.getBlock() == Blocks.GRASS_BLOCK.getDefaultState())
		{
			event.setResult(Result.ALLOW);
			
			World world = event.getWorld();
			BlockPos pos = event.getPos();
			GrassBlock grassBlock = (GrassBlock)event.getBlock().getBlock();
			
			if(world.getBlockState(event.getPos().up()).isAir())
			{
				if(world instanceof ServerWorld)
				{
					if(grassBlock.canUseBonemeal(world, world.getRandom(), pos, event.getBlock()))
					{
						// Grow grass
						for (int i = 0; i < 128; ++i)
						{
							BlockPos thisPos = pos.up();
							int j = 0;

							while (true)
							{
								if (j >= i / 16)
								{
									BlockState thisState = world.getBlockState(thisPos);
									
									// Grow tall grass into double tall grass (1/10 chance)
									if (thisState.getBlock() == Blocks.GRASS && world.getRandom().nextInt(10) == 0)
									{
										//BlockState tallGrassState = Blocks.GRASS.getDefaultState();
										((IGrowable) Blocks.GRASS).grow((ServerWorld) world, world.getRandom(), thisPos, thisState);
									}
									
									// Grow short grass into tall grass (1/8 chance)
									if (thisState.getBlock() == ModBlocks.SHORT_GRASS.get() && world.getRandom().nextInt(8) == 0)
									{
										//BlockState shortGrassState = ModBlocks.short_grass.getDefaultState();
										((IGrowable) ModBlocks.SHORT_GRASS.get()).grow((ServerWorld) world, world.getRandom(), thisPos, thisState);
									}
									
									if (thisState.isAir())
									{
										// Plant a flower (1/8 chance) or grass
										BlockState stateToPlant;
										if (world.getRandom().nextInt(8) == 0)
										{
											List<ConfiguredFeature<?, ?>> list = world.getBiome(thisPos).getFlowers();
											if (list.isEmpty())
											{
												break;
											}
					
											ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig) (list.get(0)).config).feature;
											stateToPlant = ((FlowersFeature) configuredfeature.feature).getFlowerToPlace(world.getRandom(), thisPos, configuredfeature.config);
										}
										else
										{
											Biome.TempCategory thisTemp = world.getBiome(thisPos).getTempCategory();
											
											double grassChance;
											
											switch(thisTemp)
											{
												case OCEAN:
												case MEDIUM:
												default:
													grassChance = 0.5;
													break;
												case WARM:
												case COLD:
													grassChance = 0.1;
													break;
											}
											
											if (world.getRandom().nextDouble() < grassChance)
											{
												stateToPlant = Blocks.GRASS.getDefaultState();
											}
											else
											{
												stateToPlant = ModBlocks.SHORT_GRASS.get().getDefaultState();
											}
											
										}
										
										// Plant if possible
										if (stateToPlant.isValidPosition(world, thisPos))
										{
											world.setBlockState(thisPos, stateToPlant);
										}
									}
										
									break;
								}

								thisPos = thisPos.add(world.getRandom().nextInt(3) - 1, (world.getRandom().nextInt(3) - 1) * world.getRandom().nextInt(3) / 2, world.getRandom().nextInt(3) - 1);
								if (world.getBlockState(thisPos.down()) != event.getBlock() || world.getBlockState(thisPos).isOpaqueCube(world, thisPos))
								{
									break;
								}

								++j;
							}
						}
					}
				}
			}
		}
	}
	
	public static void oldOnBonemeal(BonemealEvent event)
	{
		if (event.getBlock() == Blocks.GRASS_BLOCK.getDefaultState())
		{
			event.setCanceled(true);
			
			BlockPos blockpos = event.getPos().up();
			//BlockState tallGrassState = Blocks.GRASS.getDefaultState();
			World world = event.getWorld();
			
			for (int i = 0; i < 128; ++i)
			{
				BlockPos thisPos = blockpos;
				int j = 0;

				while (true)
				{
					if (j >= i / 16)
					{
							BlockState thisState = world.getBlockState(thisPos);
							
							// Grow tall grass into double tall grass (1/10 chance)
							/*
							if (thisState.getBlock() == Blocks.GRASS && world.getRandom().nextInt(10) == 0)
							{
								//((IGrowable) tallGrassState.getBlock()).grow((ServerWorld) world, world.getRandom(), thisPos, thisState);
								world.setBlockState(thisPos, Blocks.TALL_GRASS.getDefaultState());
							}
							*/
							
	
							if (!thisState.isAir())
							{
								break;
							}
	
							// Plant a flower (1/8 chance) or tall grass
							BlockState stateToPlant;
							if (world.getRandom().nextInt(8) == 0)
							{
								List<ConfiguredFeature<?, ?>> list = world.getBiome(thisPos).getFlowers();
								if (list.isEmpty())
								{
									break;
								}
	
								ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig) (list.get(0)).config).feature;
								stateToPlant = ((FlowersFeature) configuredfeature.feature).getFlowerToPlace(world.getRandom(), thisPos, configuredfeature.config);
							}
							else
							{
								stateToPlant = Blocks.GRASS.getDefaultState();;
							}
							
							// Plant if possible
							if (stateToPlant.isValidPosition(world, thisPos))
							{
								world.setBlockState(thisPos, stateToPlant);
							}
							
							break;
					}

					thisPos = thisPos.add(world.getRandom().nextInt(3) - 1, (world.getRandom().nextInt(3) - 1) * world.getRandom().nextInt(3) / 2, world.getRandom().nextInt(3) - 1);
					if (world.getBlockState(thisPos.down()) != event.getBlock() || world.getBlockState(thisPos).isOpaqueCube(world, thisPos))
					{
						break;
					}

					++j;
				}
			}
		}
	}
}