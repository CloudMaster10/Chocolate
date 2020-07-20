package com.cloud.chocolate.world.gen.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.cloud.chocolate.init.ModBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.common.IPlantable;
import net.minecraft.world.IWorld;

public class PalmTreeFeature extends AbstractTreeFeature<TreeFeatureConfig>
{
	public PalmTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}
    
	@Override
	protected boolean place(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set1, Set<BlockPos> set2, MutableBoundingBox box, TreeFeatureConfig config)
	{
		int treeHeight = config.baseHeight;
		boolean flag = true;
		if (position.getY() >= 1 && position.getY() + treeHeight + 1 <= worldIn.getMaxHeight())
		{
			for (int j = position.getY(); j <= position.getY() + 1 + treeHeight; ++j)
			{
				int k = 1;
				if (j == position.getY())
				{
					k = 0;
				}
				if (j >= position.getY() + 1 + treeHeight - 2)
				{
					k = 2;
				}
				
				BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();
				
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
				{
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
					{
						if (j >= 0 && j < worldIn.getMaxHeight())
						{
							if (!canBeReplacedByLogs(worldIn, blockpos$Mutable.setPos(l, j, i1)))
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}
			
			if (!flag)
			{
				return false;
			}
			else if (isSoil(worldIn, position.down(), (IPlantable) ModBlocks.palm_sapling) && position.getY() < worldIn.getMaxHeight() - treeHeight - 1)
			{
				this.setDirtAt(worldIn, position.down(), position);
				
				// Place Trunk
				int slant1 = rand.nextInt(6);
				int slant2 = rand.nextInt(5);
				Direction[] directs = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
				Direction toSlant = directs[rand.nextInt(3)];
				
				for (int y = 0; y < treeHeight; ++y)
				{	
					if(y > 0) {
						position = position.offset(Direction.UP);
						if(y == slant1 || y == slant2)
							position = position.offset(toSlant);
					}

					this.setLog(worldIn, rand, position, set1, box, config);
				}
				
				// Place Canopy
				double canopyType = rand.nextDouble();
				
				if(canopyType <= .45)
					flatCanopy(worldIn, rand, position, set2, box, config);
				else if(canopyType <= .8)
					bushyCanopy(worldIn, rand, position, set2, box, config);
				else
					roundCanopy(worldIn, rand, position, set2, box, config);
				

				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	void placeLeaves(int x, int y, int z, IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set2, MutableBoundingBox box, TreeFeatureConfig config, boolean forceUpdate)
	{
		BlockPos blockpos = new BlockPos(position.getX() + x, position.getY() + y, position.getZ() + z);

		this.setLeaf(worldIn, rand, blockpos, set2, box, config);
		if(forceUpdate)
		{
			((IWorld) worldIn).getPendingBlockTicks().scheduleTick(blockpos, ModBlocks.palm_fronds, 1);
		}

	}
	
	void flatCanopy(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set2, MutableBoundingBox box, TreeFeatureConfig config) {
		// Layer 0
		placeLeaves(-2, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 0, 1, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(-1, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 2, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(0, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, 2, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(1, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 2, worldIn, rand, position, set2, box, config, false);

		placeLeaves(2, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 0, 1, worldIn, rand, position, set2, box, config, false);
		
		// Layer 1
		placeLeaves(-1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 1, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(-3, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -3, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 3, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(3, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 1, 0, worldIn, rand, position, set2, box, config, false);
		
		// Diagonal Leaves
		placeLeaves(-2, 1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-2, 1, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 1, 2, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-4, 0, 0, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-3, 0, -3, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-3, 0, 3, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 0, -4, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 0, 4, worldIn, rand, position, set2, box, config, true);
		placeLeaves(3, 0, -3, worldIn, rand, position, set2, box, config, true);
		placeLeaves(3, 0, 3, worldIn, rand, position, set2, box, config, true);
		placeLeaves(4, 0, 0, worldIn, rand, position, set2, box, config, true);
	}
	
	void bushyCanopy(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set2, MutableBoundingBox box, TreeFeatureConfig config) {		
		// Layer 0
		placeLeaves(-2, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 0, 1, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(-1, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 0, 2, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(0, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, 2, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(1, 0, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 2, worldIn, rand, position, set2, box, config, false);

		placeLeaves(2, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 0, 1, worldIn, rand, position, set2, box, config, false);
		
		// Layer 1
		placeLeaves(-1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 1, worldIn, rand, position, set2, box, config, false);
		
		placeLeaves(-3, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-2, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -3, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 3, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(3, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 1, 0, worldIn, rand, position, set2, box, config, false);
		
		// Diagonal Leaves
		placeLeaves(-2, -1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-2, -1, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, -1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, -1, 2, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-2, 1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-2, 1, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 1, 2, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-4, 0, 0, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 0, -4, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 0, 4, worldIn, rand, position, set2, box, config, true);
		placeLeaves(4, 0, 0, worldIn, rand, position, set2, box, config, true);
	}
	
	void roundCanopy(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> set2, MutableBoundingBox box, TreeFeatureConfig config) {
		// Layer 0
		placeLeaves(-1, 0, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 0, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 0, 0, worldIn, rand, position, set2, box, config, false);
		
		// Layer 1
		placeLeaves(-2, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(-1, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 1, 2, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 1, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(2, 1, 0, worldIn, rand, position, set2, box, config, false);
		
		// Layer 2
		placeLeaves(-1, 2, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 2, -1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 2, 0, worldIn, rand, position, set2, box, config, false);
		placeLeaves(0, 2, 1, worldIn, rand, position, set2, box, config, false);
		placeLeaves(1, 2, 0, worldIn, rand, position, set2, box, config, false);
		
		// Layer 3
		placeLeaves(0, 3, 0, worldIn, rand, position, set2, box, config, false);
		
		// Diagonal Leaves
		placeLeaves(-2, -1, 0, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, -1, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, -1, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, -1, 0, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-2, 0, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-2, 0, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 0, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 0, 2, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-2, 2, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(-2, 2, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 2, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 2, 2, worldIn, rand, position, set2, box, config, true);
		
		placeLeaves(-2, 3, 0, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 3, -2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(0, 3, 2, worldIn, rand, position, set2, box, config, true);
		placeLeaves(2, 3, 0, worldIn, rand, position, set2, box, config, true);
	}
}
