package com.cloud.chocolate.item;

import java.util.List;
import java.util.function.Predicate;

import com.cloud.chocolate.entity.item.ModBoatEntity;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ModBoatItem extends Item
{
	private static final Predicate<Entity> field_219989_a = EntityPredicates.NOT_SPECTATING.and(Entity::canBeCollidedWith);
	private final ModBoatEntity.Type type;

	public ModBoatItem(ModBoatEntity.Type typeIn, Item.Properties properties)
	{
		super(properties);
		this.type = typeIn;
		DispenserBlock.registerDispenseBehavior(this, new DispenserModBoatBehavior(typeIn));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.ANY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS)
		{
			return ActionResult.resultPass(itemstack);
		}
		else
		{
			Vector3d vec3d = playerIn.getLook(1.0F);
			double d0 = 5.0D;
			List<Entity> list = worldIn.getEntitiesInAABBexcluding(playerIn, playerIn.getBoundingBox().expand(vec3d.scale(5.0D)).grow(1.0D), field_219989_a);
			if (!list.isEmpty())
			{
				Vector3d vec3d1 = playerIn.getEyePosition(1.0F);

				for (Entity entity : list)
				{
					AxisAlignedBB axisalignedbb = entity.getBoundingBox().grow((double) entity.getCollisionBorderSize());
					if (axisalignedbb.contains(vec3d1))
					{
						return ActionResult.resultPass(itemstack);
					}
				}
			}

			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK)
			{
				ModBoatEntity boat = new ModBoatEntity(worldIn, raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
				boat.setModBoatType(this.type);
				boat.rotationYaw = playerIn.rotationYaw;
				if (!worldIn.hasNoCollisions(boat, boat.getBoundingBox().grow(-0.1D)))
				{
					return ActionResult.resultFail(itemstack);
				}
				else
				{
					if (!worldIn.isRemote)
					{
						worldIn.addEntity(boat);
						if (!playerIn.abilities.isCreativeMode)
						{
							itemstack.shrink(1);
						}
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
					return ActionResult.resultSuccess(itemstack);
				}
			}
			else
			{
				return ActionResult.resultPass(itemstack);
			}
		}
	}
	
	static class DispenserModBoatBehavior extends DefaultDispenseItemBehavior
	{
    	private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
    	private final ModBoatEntity.Type type;

    	public DispenserModBoatBehavior(ModBoatEntity.Type type)
    	{
    		this.type = type;
    	}

    	public ItemStack dispenseStack(IBlockSource iBlockSource, ItemStack stack)
    	{
    		Direction direction = iBlockSource.getBlockState().get(DispenserBlock.FACING);
    		World world = iBlockSource.getWorld();
    		double x = iBlockSource.getX() + (double) ((float) direction.getXOffset() * 1.125f);
    		double y = iBlockSource.getY() + (double) ((float) direction.getYOffset() * 1.125f);
    		double z = iBlockSource.getZ() + (double) ((float) direction.getZOffset() * 1.125f);
    		BlockPos pos = iBlockSource.getBlockPos().offset(direction);
    		double adjustY;
    		if(world.getFluidState(pos).isTagged(FluidTags.WATER))
    		{
    			adjustY = 1.0;
    		}
    		else
    		{
    			if(!world.getBlockState(pos).isAir() || !world.getFluidState(pos.down()).isTagged(FluidTags.WATER))
    			{
    				return this.defaultDispenseItemBehavior.dispense(iBlockSource, stack);
    			}
    			adjustY = 0d;
    		}
    		
    		ModBoatEntity boat = new ModBoatEntity(world, x, y + adjustY, z);
    		boat.setModBoatType(this.type);
    		boat.rotationYaw = direction.getHorizontalAngle();
    		world.addEntity(boat);
    		
    		stack.shrink(1);
    		return stack;
    	}

    	protected void playDispenseSound(IBlockSource iBlockSource)
    	{
    		iBlockSource.getWorld().playEvent(1000, iBlockSource.getBlockPos(), 0);
    	}
    }
}