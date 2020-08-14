package com.cloud.chocolate.item;

import java.util.List;

import com.cloud.chocolate.Chocolate;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class ScytheItem extends Item implements IVanishable
{
	private final Multimap<Attribute, AttributeModifier> attributeModifiers;
	
	public ScytheItem(Properties properties)
	{
		super(properties);
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 6.0F, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.0F, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		float damage = (float) (attacker.getAttributeValue(Attributes.ATTACK_DAMAGE));
		List<LivingEntity> entitiesInRange;
		
		if(attacker instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) attacker;
			
			float playerAttackStrength = player.getCooledAttackStrength(0.5F);
			damage = damage * (0.2F + playerAttackStrength * playerAttackStrength * 0.8F);
			
			entitiesInRange = player.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(1.5 /* * playerAttackStrength*/, 0.25, 1.5 /* * playerAttackStrength*/));
			for (LivingEntity livingentity : entitiesInRange)
			{
				if (livingentity != player && livingentity != target && !player.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && player.getDistanceSq(livingentity) < 9.0D)
				{
					livingentity.applyKnockback(0.4F, (double) MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F))));
					livingentity.attackEntityFrom(DamageSource.causePlayerDamage(((PlayerEntity) player)), damage * 0.75F);
				}
			}
			
			player.world.playSound((PlayerEntity) null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
			player.spawnSweepParticles();
		}
		else
		{
			entitiesInRange = attacker.world.getEntitiesWithinAABB(LivingEntity.class, target.getBoundingBox().grow(1.5D, 0.25D, 1.5D));
			for (LivingEntity livingentity : entitiesInRange)
			{
				if (livingentity != attacker && livingentity != target && !attacker.isOnSameTeam(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).hasMarker()) && attacker.getDistanceSq(livingentity) < 9.0D)
				{
					livingentity.applyKnockback(0.4F, (double) MathHelper.sin(attacker.rotationYaw * ((float) Math.PI / 180F)), (double) (-MathHelper.cos(attacker.rotationYaw * ((float) Math.PI / 180F))));
					livingentity.attackEntityFrom(DamageSource.GENERIC, damage * 0.75F);
				}
			}
		}
		
		stack.damageItem(1, attacker, (p_220045_0_) -> {
			p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		
		return true;
	}
	
	@SubscribeEvent
	public static void onPlayerBreakPlant(BlockEvent.BreakEvent event)
	{
		PlayerEntity player = event.getPlayer();
		if(player.getHeldItemMainhand().getItem() instanceof ScytheItem)
		{
			Material material = event.getState().getMaterial();
			if (material == Material.PLANTS || material == Material.TALL_PLANTS || material == Material.OCEAN_PLANT || material == Material.SEA_GRASS)
			{
				World world = player.getEntityWorld();
	            BlockPos pos = event.getPos().add(-1, 0, -1);
				
				// Harvest all plant blocks in the 3x3 area surrounding pos
				BlockPos thisPos;
				BlockState thisState;
				Material thisMaterial;
				for (int i = 0; i < 9; i++)
				{
					thisPos = pos.add(i / 3, 0, i % 3);
					thisState = world.getBlockState(thisPos).getBlockState();
					thisMaterial = thisState.getMaterial();
					
					// Destroy the block and drop the items in the world
					if (thisMaterial == Material.PLANTS || thisMaterial == Material.TALL_PLANTS || thisMaterial == Material.OCEAN_PLANT || thisMaterial == Material.SEA_GRASS)
					{
						world.setBlockState(thisPos, Blocks.AIR.getDefaultState());
						if (!player.isCreative())
						{
							Block.spawnDrops(thisState, world, thisPos);
						}
	
						if (!thisPos.equals(pos))
						{
							world.playEvent(2001, thisPos, Block.getStateId(thisState));
						}
					}
				}
			}
		}
	}

	public boolean canHarvestBlock(BlockState blockIn)
	{
		return false;
	}

	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
	}
	
	public int getItemEnchantability()
	{
		return 1;
	}
}