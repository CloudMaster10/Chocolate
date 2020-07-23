package com.cloud.chocolate.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IForgeShearable;

public class ShortGrassBlock extends BushBlock implements IGrowable, IForgeShearable{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);

	   public ShortGrassBlock(Block.Properties properties)
	   {
	      super(properties);
	   }

	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	   {
	      return SHAPE;
	   }

	   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
	   {
	      return true;
	   }

	   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
	   {
	      return true;
	   }

	   public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state)
	   {
	      Block tallGrass = Blocks.GRASS;
	      if (tallGrass.getDefaultState().isValidPosition(world, pos) && world.isAirBlock(pos.up()))
	      {
	    	  world.setBlockState(pos, tallGrass.getDefaultState());
	      }

	   }

	   public Block.OffsetType getOffsetType() {
	      return Block.OffsetType.XYZ;
	   }
}
