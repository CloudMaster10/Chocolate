package com.cloud.chocolate.item;

import javax.annotation.Nullable;

import com.cloud.chocolate.client.renderer.gui.ModEditSignScreen;
import com.cloud.chocolate.tileentity.ModSignTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModSignItem extends WallOrFloorItem
{
	public ModSignItem(Item.Properties propertiesIn, Block floorBlockIn, Block wallBlockIn)
	{
		super(floorBlockIn, wallBlockIn, propertiesIn);
	}

	protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state)
	{
		boolean flag = super.onBlockPlaced(pos, worldIn, player, stack, state);
		if (!worldIn.isRemote && !flag && player != null)
		{			
			ModSignTileEntity sign = (ModSignTileEntity) worldIn.getTileEntity(pos);
			sign.setPlayer(player);
			
			ClientPlayerEntity clientPlayer = (ClientPlayerEntity) player;
			clientPlayer.mc.displayGuiScreen(new ModEditSignScreen(sign));
			
			//Minecraft.getInstance().player.openSignEditor(signTile);
			//Minecraft.getInstance().displayGuiScreen(new ModEditSignScreen(sign));	
			
			//player.openSignEditor((SignTileEntity)worldIn.getTileEntity(pos));
		}

		return flag;
	}
}