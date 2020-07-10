package com.cloud.chocolate.client.renderer.tileentity;

import java.util.List;

import com.cloud.chocolate.block.ModAbstractSignBlock;
import com.cloud.chocolate.block.ModStandingSignBlock;
import com.cloud.chocolate.block.ModWallSignBlock;
import com.cloud.chocolate.tileentity.ModSignTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModSignTileEntityRenderer<A extends ModSignTileEntity> extends TileEntityRenderer<A> {
	private final ModSignTileEntityRenderer.SignModel model = new ModSignTileEntityRenderer.SignModel();

	public ModSignTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	public void render(A sign, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer,
			int combinedLightIn, int combinedOverlayIn) {
		BlockState blockstate = sign.getBlockState();
		matrixStack.push();
		if (blockstate.getBlock() instanceof ModStandingSignBlock) {
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			float f1 = -((float) (blockstate.get(ModStandingSignBlock.ROTATION) * 360) / 16.0F);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(f1));
			this.model.signStick.showModel = true;
		} else {
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			float f4 = -blockstate.get(ModWallSignBlock.FACING).getHorizontalAngle();
			matrixStack.rotate(Vector3f.YP.rotationDegrees(f4));
			matrixStack.translate(0.0D, -0.3125D, -0.4375D);
			this.model.signStick.showModel = false;
		}
		float scale = 0.6666667F;
		matrixStack.push();
		matrixStack.scale(scale, -scale, -scale);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.getEntityCutoutNoCull(getTexture(blockstate)));
		this.model.signBoard.render(matrixStack, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		this.model.signStick.render(matrixStack, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStack.pop();

		FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
		float textScale = 0.010416667F;
		matrixStack.translate(0.0F, 0.33333334F, 0.046666667F);
		matrixStack.scale(textScale, -textScale, textScale);
		int i = sign.getTextColor().getTextColor();
		double colorMultiplier = 0.4D;
		int j = (int) ((double) NativeImage.getRed(i) * colorMultiplier);
		int k = (int) ((double) NativeImage.getGreen(i) * colorMultiplier);
		int l = (int) ((double) NativeImage.getBlue(i) * colorMultiplier);
		int i1 = NativeImage.getCombined(0, l, k, j);

		for (int k1 = 0; k1 < 4; ++k1) {
			String itextproperties = sign.getRenderText(k1, (p_212491_1_) -> {
				List<ITextComponent> list = RenderComponentsUtil.splitText(p_212491_1_, 90, fontrenderer, false, true);
				return list.isEmpty() ? "" : list.get(0).getFormattedText();
			});
			if (itextproperties != null) {
				float f3 = (float) (-fontrenderer.getStringWidth(itextproperties) / 2);
				fontrenderer.renderString(itextproperties, f3, (float) (k1 * 10 - 20), i1, false,
						matrixStack.getLast().getMatrix(), buffer, false, 0, combinedLightIn);
			}
		}
		matrixStack.pop();
	}

	public static ResourceLocation getTexture(BlockState state) {
		return ((ModAbstractSignBlock) state.getBlock()).getTextureLocation();
	}

	@OnlyIn(Dist.CLIENT)
	public static final class SignModel extends Model {
		public final ModelRenderer signBoard = new ModelRenderer(64, 32, 0, 0);
		public final ModelRenderer signStick;

		public SignModel() {
			super(RenderType::getEntityCutoutNoCull);
			this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24.0F, 12.0F, 2.0F, 0.0F);
			this.signStick = new ModelRenderer(64, 32, 0, 14);
			this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F);
		}

		public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
				float red, float green, float blue, float alpha) {
			this.signBoard.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			this.signStick.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		}
	}
}