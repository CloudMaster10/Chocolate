package com.cloud.chocolate.client.renderer.tileentity;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModSignTileEntityRenderer extends TileEntityRenderer<SignTileEntity> {
   /** The ModelSign instance for use in this renderer */
   private final ModSignTileEntityRenderer.SignModel model = new ModSignTileEntityRenderer.SignModel();

   public ModSignTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
      super(rendererDispatcherIn);
   }

   public void render(SignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
      BlockState blockstate = tileEntityIn.getBlockState();
      matrixStackIn.push();
      float f = 0.6666667F;
      if (blockstate.getBlock() instanceof StandingSignBlock) {
         matrixStackIn.translate(0.5D, 0.5D, 0.5D);
         float f1 = -((float)(blockstate.get(StandingSignBlock.ROTATION) * 360) / 16.0F);
         matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
         this.model.signStick.showModel = true;
      } else {
         matrixStackIn.translate(0.5D, 0.5D, 0.5D);
         float f4 = -blockstate.get(WallSignBlock.FACING).getHorizontalAngle();
         matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f4));
         matrixStackIn.translate(0.0D, -0.3125D, -0.4375D);
         this.model.signStick.showModel = false;
      }

      matrixStackIn.push();
      matrixStackIn.scale(0.6666667F, -0.6666667F, -0.6666667F);
      RenderMaterial rendermaterial = getMaterial(blockstate.getBlock());
      IVertexBuilder ivertexbuilder = rendermaterial.getBuffer(bufferIn, this.model::getRenderType);
      this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
      this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
      matrixStackIn.pop();
      FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
      float f2 = 0.010416667F;
      matrixStackIn.translate(0.0D, (double)0.33333334F, (double)0.046666667F);
      matrixStackIn.scale(0.010416667F, -0.010416667F, 0.010416667F);
      int i = tileEntityIn.getTextColor().getTextColor();
      double d0 = 0.4D;
      int j = (int)((double)NativeImage.getRed(i) * 0.4D);
      int k = (int)((double)NativeImage.getGreen(i) * 0.4D);
      int l = (int)((double)NativeImage.getBlue(i) * 0.4D);
      int i1 = NativeImage.getCombined(0, l, k, j);
      int j1 = 20;

      for(int k1 = 0; k1 < 4; ++k1) {
         ITextProperties itextproperties = tileEntityIn.func_235677_a_(k1, (p_239309_1_) -> {
            List<ITextProperties> list = fontrenderer.func_238420_b_().func_238362_b_(p_239309_1_, 90, Style.EMPTY);
            return list.isEmpty() ? ITextProperties.field_240651_c_ : list.get(0);
         });
         if (itextproperties != null) {
            float f3 = (float)(-fontrenderer.func_238414_a_(itextproperties) / 2);
            fontrenderer.func_238416_a_(itextproperties, f3, (float)(k1 * 10 - 20), i1, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
         }
      }

      matrixStackIn.pop();
   }

   public static RenderMaterial getMaterial(Block blockIn) {
      WoodType woodtype;
      if (blockIn instanceof AbstractSignBlock) {
         woodtype = ((AbstractSignBlock)blockIn).getWoodType();
      } else {
         woodtype = WoodType.OAK;
      }

      return Atlases.getSignMaterial(woodtype);
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

      public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
         this.signBoard.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
         this.signStick.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
      }
   }
}