package com.cloud.chocolate.world.gen.feature.foliageplacer;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer
{
	public static final Codec<PalmFoliagePlacer> field_236736_a_ = RecordCodecBuilder.create((p_236737_0_) -> {
		return func_236756_b_(p_236737_0_).apply(p_236737_0_, PalmFoliagePlacer::new);
	});

	public PalmFoliagePlacer(int radius, int randRadius, int offset, int randOffset)
	{
		super(radius, randRadius, offset, randOffset);
	}

	@Override
	protected FoliagePlacerType<?> func_230371_a_()
	{
		return FoliagePlacerType.ACACIA;
	}

	@Override
	protected void func_230372_a_(IWorldGenerationReader worldIn, Random rand, BaseTreeFeatureConfig config, int p_230372_4_, Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> leavesSet, int p_230372_9_, MutableBoundingBox box)
	{
		boolean flag = foliage.func_236765_c_();
		BlockPos blockpos = foliage.func_236763_a_().up(p_230372_9_);
		this.func_236753_a_(worldIn, rand, config, blockpos, p_230372_7_ + foliage.func_236764_b_(),     leavesSet, -1 - p_230372_6_, flag, box);
		this.func_236753_a_(worldIn, rand, config, blockpos, p_230372_7_ - 1,                            leavesSet, -p_230372_6_,     flag, box);
		this.func_236753_a_(worldIn, rand, config, blockpos, p_230372_7_ + foliage.func_236764_b_() - 1, leavesSet, 0,                flag, box);
	}

	@Override
	public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
		return 0;
	}

	@Override
	protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
		if (p_230373_3_ == 0)
		{
			return (p_230373_2_ > 1 || p_230373_4_ > 1) && p_230373_2_ != 0 && p_230373_4_ != 0;
		}
		else
		{
			return p_230373_2_ == p_230373_5_ && p_230373_4_ == p_230373_5_ && p_230373_5_ > 0;
		}
	}
}
