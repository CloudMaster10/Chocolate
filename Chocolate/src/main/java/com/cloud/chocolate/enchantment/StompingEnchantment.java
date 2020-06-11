package com.cloud.chocolate.enchantment;

import java.util.List;

import com.cloud.chocolate.Chocolate;
import com.cloud.chocolate.init.ModEnchantments;
import com.cloud.chocolate.util.ModDamageSource;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class StompingEnchantment extends Enchantment
{
    public StompingEnchantment(Enchantment.Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots)
    {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return enchantmentLevel * 10;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment)
    {
        if(enchantment instanceof ProtectionEnchantment)
        {
            ProtectionEnchantment protection = (ProtectionEnchantment) enchantment;
            return protection.protectionType != ProtectionEnchantment.Type.FALL;
        }
        
        return true;
    }
    
    public boolean isTreasureEnchantment() {
        return true;
    }

    @SubscribeEvent
    public static void onFallDamage(LivingDamageEvent event)
    {
        if(event.getSource() == DamageSource.FALL)
        {
            LivingEntity stomperEntity = event.getEntityLiving();
            if(stomperEntity instanceof PlayerEntity || stomperEntity instanceof MobEntity)
            {
                ItemStack stack = stomperEntity.getItemStackFromSlot(EquipmentSlotType.FEET);
                if(!stack.isEmpty())
                {
                	int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.stomping, stack);
                	if(level > 0)
                	{
                		float heightFactor = Math.min(2.0F, 1.0F + (event.getAmount() * 0.05F));
                        float levelFactor = 1.0F + ((level - 1) * 0.3F);
                        
                        float strengthFactor = 1.0F * levelFactor * heightFactor;

                        // Finds living entities in a five block radius around the stomper to be damaged
                        List<LivingEntity> entities = stomperEntity.world.getEntitiesWithinAABB(LivingEntity.class, stomperEntity.getBoundingBox().grow(level + 1, level * 0.5, level + 1), LivingEntity::isAlive);
                        entities.remove(stomperEntity);
                        
                        // If the stomper is a player and PVP is not enabled, prevent stomping from damaging players
                        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
                        if(stomperEntity instanceof PlayerEntity && !server.isPVPEnabled())
                        {
                        	for(LivingEntity livingEntity : entities)
                        	{
                        		if(livingEntity instanceof PlayerEntity) 
                        		{
                        			entities.remove(livingEntity);
                        		}
                        	}
                        }

                        if(entities.size() > 0)
                        {
                        	// Play stomp sound and spawn particles at the location of the stomper
                            if(stomperEntity.world instanceof ServerWorld)
                            {
                                // Spawn particles at stopmer
                            	BlockState state = stomperEntity.world.getBlockState(stomperEntity.getPosition().down());
                                ServerWorld serverWorld = (ServerWorld) stomperEntity.world;
                                serverWorld.spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, state), stomperEntity.getPosX(), stomperEntity.getPosY(), stomperEntity.getPosZ(), (int) (100 * strengthFactor), 0, 0.2, 0, 750.0 * strengthFactor);
                                
                                // Play stomp sound in the appropriate category
                                SoundCategory soundCategory = SoundCategory.HOSTILE;
                                
                                if(stomperEntity instanceof PlayerEntity)
                                {
                                	soundCategory = SoundCategory.PLAYERS;
                                }
                              
                                serverWorld.playSound(null, stomperEntity.getPosX(), stomperEntity.getPosY(), stomperEntity.getPosZ(), SoundEvents.ENTITY_PLAYER_SMALL_FALL, soundCategory, strengthFactor, 0.8F / strengthFactor);
                            }
                            
                            // Damages boots once for each entity being stomped
                            stack.damageItem(entities.size(), stomperEntity, entity1 -> {
                                entity1.sendBreakAnimation(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, EquipmentSlotType.FEET.getIndex()));
                            });
                            
                            // If fall damage is greater than the health of the entities to be stomped, the player takes the excess damage
                            float fallDamage = event.getAmount();
                            float totalHealth = 0.0F;
                        	for(LivingEntity livingEntity : entities)
                        	{
                        		totalHealth += livingEntity.getHealth();
                        	}
                        	
                        	event.setAmount(Math.max(0, fallDamage - totalHealth));
                            
                            // Deal damage and recoil to stomped entities
                            float damage = (float) (fallDamage / Math.sqrt(entities.size()));
                            for(LivingEntity livingEntity : entities)
                        	{
                            	// Cause stomped entity to be recoiled
                            	float distanceFactor = 1.5F - (livingEntity.getDistance(stomperEntity) * 0.2F);
                                float stompStrength = 0.1F * strengthFactor * distanceFactor;
                                Vec3d direction = new Vec3d(livingEntity.getPosX() - stomperEntity.getPosX(), 0, livingEntity.getPosZ() - stomperEntity.getPosZ()).normalize();
                                
                                livingEntity.setMotion(direction.x * stompStrength, stompStrength * 0.8F, direction.z * stompStrength);
                                livingEntity.addVelocity(direction.x * stompStrength, stompStrength * 0.8F, direction.z * stompStrength);
                                livingEntity.velocityChanged = true;
                                
                                // Deal damage to stomped entity
                                livingEntity.attackEntityFrom(ModDamageSource.STOMPING, damage);
                                livingEntity.setRevengeTarget(stomperEntity);
                        	}
                        }
                    }
                }
            }
        }
    }
}