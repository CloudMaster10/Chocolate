package com.cloud.chocolate.block;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModTileEntities;
import com.cloud.chocolate.tileentity.ModSignTileEntity;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public abstract class ModAbstractSignBlock extends AbstractSignBlock
{
	private final ResourceLocation location;

	protected ModAbstractSignBlock(Block.Properties propertiesIn, String type)
	{
		super(propertiesIn, null);
		this.location = new ResourceLocation(Chocolate.MOD_ID, "textures/entity/signs/" + type + ".png");
	}
	
	public boolean hasTileEntity()
	{
		return true;
	}
	
	/*
	@Override
	public TileEntity createNewTileEntity(IBlockReader world)
	{
		return ModTileEntities.SIGN.get().create();
	}
	*/

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		ItemStack itemstack = player.getHeldItem(handIn);
		boolean canEdit = player.abilities.allowEdit;
		boolean canDye = itemstack.getItem() instanceof DyeItem && canEdit;
		if (worldIn.isRemote)
		{
			return canDye ? ActionResultType.SUCCESS : ActionResultType.CONSUME;
		}
		else
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof ModSignTileEntity)
			{
				ModSignTileEntity sign = (ModSignTileEntity) tileentity;
				if (canDye)
				{
					boolean canSetColor = sign.setTextColor(((DyeItem) itemstack.getItem()).getDyeColor());
					if (canSetColor && !player.isCreative())
					{
						itemstack.shrink(1);
					}
				}

				return sign.executeCommand(player) ? ActionResultType.SUCCESS : ActionResultType.PASS;
			}
			else
			{
				return ActionResultType.PASS;
			}
		}
	}
	
	public ResourceLocation getTextureLocation()
	{
		return this.location;
	}
	
	/*
	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	*/
}