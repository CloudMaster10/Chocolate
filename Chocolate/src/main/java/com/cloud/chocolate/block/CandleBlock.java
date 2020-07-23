package com.cloud.chocolate.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.cloud.chocolate.util.ModBlockStateProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CandleBlock extends FallingBlock {
	public static final IntegerProperty CANDLES = ModBlockStateProperties.CANDLES_1_5;
	private static final VoxelShape ONE_SHAPE = Block.makeCuboidShape(6F, 0F, 6F, 10F, 8F, 10F);
	private static final VoxelShape MEDIUM_SHAPE = Block.makeCuboidShape(3F, 0F, 3F, 13F, 9F, 13F);
	private static final VoxelShape FIVE_SHAPE = Block.makeCuboidShape(1F, 0F, 1F, 15F, 10F, 15F);
	
	public CandleBlock(Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(CANDLES, Integer.valueOf(1)));
	}
	
	/*
	public int getLightValue(BlockState state) {
		switch(state.get(CANDLES)) {
			case 1:
			default:
				return 7;
			case 2:
				return 10;
			case 3:
				return 12;
			case 4:
				return 14;
			case 5:
				return 15;
		}
	}
	*/
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = context.getWorld().getBlockState(context.getPos());
		if (blockstate.getBlock() == this) {
			return blockstate.with(CANDLES, Integer.valueOf(Math.min(5, blockstate.get(CANDLES) + 1)));
		}
		else
		{
			return this.getDefaultState();
		}
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return !state.getCollisionShape(worldIn, pos).project(Direction.UP).isEmpty();
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}
	
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return useContext.getItem().getItem() == this.asItem() && state.get(CANDLES) < 5 ? true : this.material.isReplaceable();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(CANDLES)) {
			case 1:
				return ONE_SHAPE;
			case 2:
			case 3:
			case 4:
			default:
				return MEDIUM_SHAPE;
			case 5:
				return FIVE_SHAPE;
		}
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		switch(stateIn.get(CANDLES)) {
		case 1:
		default:
			addParticles(worldIn, pos, 0.5D, 0.6875D, 0.5D);
			return;
		case 2:
			addParticles(worldIn, pos, 0.375D, 0.5D, 0.625D);
			addParticles(worldIn, pos, 0.6875D, 0.75D, 0.3125D);
			return;
		case 3:
			addParticles(worldIn, pos, 0.6875D, 0.4375D, 0.3125D);
			addParticles(worldIn, pos, 0.3125D, 0.5625D, 0.375D);
			addParticles(worldIn, pos, 0.5D, 0.75D, 0.6875D);
			return;
		case 4:
			addParticles(worldIn, pos, 0.625D, 0.4375D, 0.375D);
			addParticles(worldIn, pos, 0.3125D, 0.5D, 0.625D);
			addParticles(worldIn, pos, 0.6875D, 0.625D, 0.6875D);
			addParticles(worldIn, pos, 0.3125D, 0.75D, 0.3125D);
			return;
		case 5:
			addParticles(worldIn, pos, 0.6875D, 0.5D, 0.8125D);
			addParticles(worldIn, pos, 0.3125D, 0.5625D, 0.1857D);
			addParticles(worldIn, pos, 0.8125D, 0.625D, 0.3125D);
			addParticles(worldIn, pos, 0.1875D, 0.6875D, 0.6875D);
			addParticles(worldIn, pos, 0.5D, 0.8125D, 0.5D);
			return;
		}
	}
	
	private void addParticles(World worldIn, BlockPos pos, Double xPos, Double yPos, Double zPos)
	{
		double d0 = pos.getX() + xPos;
		double d1 = pos.getY() + yPos;
		double d2 = pos.getZ() + zPos;
		
		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}