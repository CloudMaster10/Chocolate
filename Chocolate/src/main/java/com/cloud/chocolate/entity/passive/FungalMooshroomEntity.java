package com.cloud.chocolate.entity.passive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.cloud.chocolate.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IShearable;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class FungalMooshroomEntity extends CowEntity implements IShearable, net.minecraftforge.common.IForgeShearable
{
	private static final DataParameter<String> FUNGAL_MOOSHROOM_TYPE = EntityDataManager.createKey(FungalMooshroomEntity.class, DataSerializers.STRING);
	
	public FungalMooshroomEntity(EntityType<? extends FungalMooshroomEntity> type, World worldIn)
	{
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn)
	{
		if(this.getFungalMooshroomType() == Type.CRIMSON)
		{
			return worldIn.getBlockState(pos.down()).isIn(Blocks.CRIMSON_NYLIUM) ? 10.0F : 8.0F - (worldIn.getBrightness(pos) / 2);
		}
		if(this.getFungalMooshroomType() == Type.WARPED)
		{
			return worldIn.getBlockState(pos.down()).isIn(Blocks.WARPED_NYLIUM) ? 10.0F : 8.0F - (worldIn.getBrightness(pos) / 2);
		}
		
		return 1.0F;
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(FUNGAL_MOOSHROOM_TYPE, FungalMooshroomEntity.Type.CRIMSON.name);
	}

	// Replace with cow entity when sheared
	@Override
	public void func_230263_a_(SoundCategory p_230263_1_)
	{
		this.world.playMovingSound((PlayerEntity) null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, p_230263_1_, 1.0F, 1.0F);
		if (!this.world.isRemote())
		{
			((ServerWorld) this.world).spawnParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.world);
			cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
			cowentity.setHealth(this.getHealth());
			cowentity.renderYawOffset = this.renderYawOffset;
			
			if (this.hasCustomName())
			{
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isNoDespawnRequired())
			{
				cowentity.enablePersistence();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.world.addEntity(cowentity);

			for (int i = 0; i < 3; ++i)
			{
				this.world.addEntity(new ItemEntity(this.world, this.getPosX(), this.getPosYHeight(1.0D), this.getPosZ(), new ItemStack(this.getFungalMooshroomType().renderState.getBlock())));
			}
		}
	}

	// Is shearable
	@Override
	public boolean func_230262_K__()
	{
		return this.isAlive() && !this.isChild();
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound)
	{
		super.writeAdditional(compound);
		compound.putString("Type", this.getFungalMooshroomType().name);
	}

	@Override
	public void readAdditional(CompoundNBT compound)
	{
		super.readAdditional(compound);
		this.setFungalMooshroomType(FungalMooshroomEntity.Type.getTypeByName(compound.getString("Type")));
	}

	public void setFungalMooshroomType(FungalMooshroomEntity.Type typeIn)
	{
		this.dataManager.set(FUNGAL_MOOSHROOM_TYPE, typeIn.name);
	}

	public FungalMooshroomEntity.Type getFungalMooshroomType()
	{
		return FungalMooshroomEntity.Type.getTypeByName(this.dataManager.get(FUNGAL_MOOSHROOM_TYPE));
	}

	@Override
	public FungalMooshroomEntity createChild(AgeableEntity mate)
	{
		FungalMooshroomEntity child = ModEntityTypes.FUNGAL_MOOSHROOM.get().create(this.world);
		child.setFungalMooshroomType(this.getFungalMooshroomType());
		return child;
	}
	
	@Override
	public boolean isShearable(@Nonnull ItemStack item, World world, BlockPos pos)
	{
		return func_230262_K__();
	}
	
	@Nonnull
	@Override
	public List<ItemStack> onSheared(@Nullable PlayerEntity player, @Nonnull ItemStack item, World world, BlockPos pos, int fortune)
	{
		world.playMovingSound(null, this, SoundEvents.ENTITY_MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
		if (!world.isRemote())
		{
			((ServerWorld) this.world).spawnParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosYHeight(0.5D), this.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.world);
			cowentity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
			cowentity.setHealth(this.getHealth());
			cowentity.renderYawOffset = this.renderYawOffset;
			
			if (this.hasCustomName())
			{
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isNoDespawnRequired())
			{
				cowentity.enablePersistence();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.world.addEntity(cowentity);

			List<ItemStack> items = new ArrayList<>();
			for (int i = 0; i < 5; ++i)
			{
				items.add(new ItemStack(this.getFungalMooshroomType().renderState.getBlock()));
			}

			return items;
		}
		
		return Collections.emptyList();
	}
	
	public static enum Type
	{
		CRIMSON("crimson", Blocks.CRIMSON_FUNGUS.getDefaultState()),
		WARPED("warped", Blocks.WARPED_FUNGUS.getDefaultState());

		private final String name;
		private final BlockState renderState;

		private Type(String nameIn, BlockState renderStateIn)
		{
			this.name = nameIn;
			this.renderState = renderStateIn;
		}
		
		public String getName()
		{
			return this.name;
		}

		@OnlyIn(Dist.CLIENT)
		public BlockState getRenderState()
		{
			return this.renderState;
		}

		private static FungalMooshroomEntity.Type getTypeByName(String nameIn)
		{
			for (FungalMooshroomEntity.Type type : values())
			{
				if (type.name.equals(nameIn))
				{
					return type;
				}
			}

			return CRIMSON;
		}
	}
}