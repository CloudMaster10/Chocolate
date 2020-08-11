package com.cloud.chocolate.events;

import java.util.ArrayList;
import java.util.Random;

import com.cloud.chocolate.Chocolate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Chocolate.MOD_ID)
public class BiomeExplorerMaps
{	
	private static ArrayList<BiomeMapTrade> commonMapTrades = new ArrayList<BiomeMapTrade>();
	private static ArrayList<BiomeMapTrade> rareMapTrades = new ArrayList<BiomeMapTrade>();
	
	@SubscribeEvent
	public static void onTradesLoaded(WandererTradesEvent event)
	{
		commonMapTrades.add(new BiomeMapTrade(Biomes.DESERT, 8, 0xCCB94E));
		commonMapTrades.add(new BiomeMapTrade(Biomes.MOUNTAINS, 8, 0x8A8A8A));
		commonMapTrades.add(new BiomeMapTrade(Biomes.SWAMP, 10, 0x22370F));
		commonMapTrades.add(new BiomeMapTrade(Biomes.SNOWY_TUNDRA, 10, 0x7FE4FF));
		commonMapTrades.add(new BiomeMapTrade(Biomes.DARK_FOREST, 10, 0x00590A));
		commonMapTrades.add(new BiomeMapTrade(Biomes.SAVANNA, 10, 0x9BA562));
		commonMapTrades.add(new BiomeMapTrade(Biomes.FLOWER_FOREST, 10, 0xDC7BEA));
		
		rareMapTrades.add(new BiomeMapTrade(Biomes.MUSHROOM_FIELDS, 24, 0x4D4273));
		rareMapTrades.add(new BiomeMapTrade(Biomes.JUNGLE, 18, 0x22B600));
		rareMapTrades.add(new BiomeMapTrade(Biomes.GIANT_TREE_TAIGA, 16, 0x5B421F));
		rareMapTrades.add(new BiomeMapTrade(Biomes.BADLANDS, 18, 0xC67F22));
		rareMapTrades.add(new BiomeMapTrade(Biomes.ICE_SPIKES, 22, 0x41D6C9));
	}
	
	@SubscribeEvent
	public static void onTraderSpawn(LivingSpawnEvent.SpecialSpawn event)
	{
		LivingEntity entity = event.getEntityLiving();
		if (entity instanceof WanderingTraderEntity)
		{
			WanderingTraderEntity trader = (WanderingTraderEntity) event.getEntityLiving();
			Random rand = trader.getRNG();
			
			if(rand.nextInt(4) != 0)
			{
				trader.getOffers().add(commonMapTrades.get(rand.nextInt(commonMapTrades.size())).getOffer(trader, rand));
			}
			else
			{
				trader.getOffers().add(rareMapTrades.get(rand.nextInt(rareMapTrades.size())).getOffer(trader, rand));
			}
		}
	}

	private static class BiomeMapTrade implements ITrade
	{
		public final Biome biome;
		public final int price;
		public final String name;
		
		public BiomeMapTrade(Biome biome, int price, int color)
		{
			this.biome = biome;
			this.price = price;
			this.name = "item.chocolate.biome_map." + biome.getRegistryName().getPath();
		}

		@Override
		public MerchantOffer getOffer(Entity entity, Random random)
		{
			ItemStack itemstack = createMap(entity.world, entity.getPosition(), biome, name);
			if (!itemstack.isEmpty())
			{
				return new MerchantOffer(new ItemStack(Items.EMERALD, price), new ItemStack(Items.COMPASS), itemstack, 12, 1, 0.05F);
			}
			
			return null;
		}
	}
	
	public static ItemStack createMap(World world, BlockPos pos, Biome biome, String name)
	{
		if (world instanceof ServerWorld)
		{
			BlockPos biomePos = ((ServerWorld) world).func_241116_a_(biome, pos, 6400, 8);
			if (biomePos != null)
			{
				ItemStack mapStack = FilledMapItem.setupNewMap(world, biomePos.getX(), biomePos.getZ(), (byte) 2, true, true);
				
				FilledMapItem.func_226642_a_((ServerWorld) world, mapStack);
				MapData.addTargetDecoration(mapStack, biomePos, "+", Type.RED_X);
				mapStack.setDisplayName(new TranslationTextComponent(name));
		
				return mapStack;
			}
		}
		
		return ItemStack.EMPTY;
	}
}
