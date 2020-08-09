package com.cloud.chocolate.world.gen.feature.trunkplacer;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer.Foliage;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

public class LeaningTrunkPlacer extends AbstractTrunkPlacer {
	public LeaningTrunkPlacer(int minHeight, int randHeight1, int randHeight2) {
		super(minHeight, randHeight1, randHeight2);
	}

	@Override
	protected TrunkPlacerType<?> func_230381_a_() {
		return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
	}

	@Override
	public List<Foliage> func_230382_a_(IWorldGenerationReader worldIn, Random rand, int height, BlockPos position, Set<BlockPos> logSet, MutableBoundingBox box, BaseTreeFeatureConfig config)
	{
		//setDirtAt(worldIn, position.down());
		func_236909_a_(worldIn, position.down());
		
		// Place Trunk
		int slant1 = 1 + rand.nextInt(5);
		int slant2 = 1 + rand.nextInt(4);
		Direction[] directs = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
		Direction toSlant = directs[rand.nextInt(3)];
		
		for (int y = 0; y < height; ++y)
		{	
			if (y == slant1 || y == slant2) 
			{
				position = position.offset(toSlant);
			}
			
			// Place Log
			func_236911_a_(worldIn, rand, position.up(y), logSet, box, config);
		}

		return ImmutableList.of(new FoliagePlacer.Foliage(position.up(height), 0, false));
	}
}
