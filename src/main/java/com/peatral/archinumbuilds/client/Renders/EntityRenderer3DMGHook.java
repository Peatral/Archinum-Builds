package com.peatral.archinumbuilds.client.Renders;

import org.lwjgl.opengl.GL11;

import com.peatral.archinumbuilds.entity.Entity3DMGHook;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.client.Models.Model3DMGHook;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.util.ResourceLocation;

public class EntityRenderer3DMGHook extends Render {
	private static final ResourceLocation texture = new ResourceLocation(ArchinumBuilds.MODID, "textures/entity/threedmgHook.png");
	private ModelBase model;

	public EntityRenderer3DMGHook() {
		model = new Model3DMGHook();
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}

	@Override
	public void doRender(Entity hook, double x, double y, double z, float yaw, float partialTick)
	{
		GL11.glPushMatrix();
		bindTexture(texture);
		GL11.glTranslated(x, y - 0.3D, z);
		model.render(hook, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		
		this.renderLeath(hook, ((Entity3DMGHook) hook).getOwner(), x, y, z, yaw, partialTick);
	}
	
	private double func_110828_a(double p_110828_1_, double p_110828_3_, double p_110828_5_) {
		return p_110828_1_ + (p_110828_3_ - p_110828_1_) * p_110828_5_;
	}

	public void renderLeath(Entity hook, Entity hookedPlayer, double x, double y, double z, float p_110827_8_, float p_110827_9_) {
		if (hookedPlayer != null) {
			y -= (1.6D - (double) hook.height) * 0.5D;
			Tessellator tessellator = Tessellator.instance;
			double d3 = this.func_110828_a((double) hookedPlayer.prevRotationYaw, (double) hookedPlayer.rotationYaw, (double) (p_110827_9_ * 0.5F)) * 0.01745329238474369D;
			double d4 = this.func_110828_a((double) hookedPlayer.prevRotationPitch, (double) hookedPlayer.rotationPitch, (double) (p_110827_9_ * 0.5F)) * 0.01745329238474369D;
			double d5 = Math.cos(d3);
			double d6 = Math.sin(d3);
			double d7 = Math.sin(d4);

			if (hookedPlayer instanceof EntityHanging) {
				d5 = 0.0D;
				d6 = 0.0D;
				d7 = -1.0D;
			}

			double d8 = Math.cos(d4);
			double d9 = this.func_110828_a(hookedPlayer.prevPosX, hookedPlayer.posX, (double) p_110827_9_) - d5 * 0.7D - d6 * 0.5D * d8;
			double d10 = this.func_110828_a(hookedPlayer.prevPosY + (double) hookedPlayer.getEyeHeight() * 0.7D, hookedPlayer.posY + (double) hookedPlayer.getEyeHeight() * 0.7D, (double) p_110827_9_) - d7 * 0.5D - 0.25D;
			double d11 = this.func_110828_a(hookedPlayer.prevPosZ, hookedPlayer.posZ, (double) p_110827_9_) - d6 * 0.7D + d5 * 0.5D * d8;
			double d12 = this.func_110828_a((double) 0.0, // hook.prevRenderYawOffset,
					(double) 0.0, // hook.renderYawOffset,
					(double) p_110827_9_) * 0.01745329238474369D + (Math.PI / 2D);
			d5 = Math.cos(d12) * (double) hook.width * 0.4D;
			d6 = Math.sin(d12) * (double) hook.width * 0.4D;
			double d13 = this.func_110828_a(hook.prevPosX, hook.posX, (double) p_110827_9_) + d5;
			double d14 = this.func_110828_a(hook.prevPosY, hook.posY, (double) p_110827_9_);
			double d15 = this.func_110828_a(hook.prevPosZ, hook.posZ, (double) p_110827_9_) + d6;
			x += d5;
			z += d6;
			double d16 = (double) ((float) (d9 - d13));
			double d17 = (double) ((float) (d10 - d14));
			double d18 = (double) ((float) (d11 - d15));
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_CULL_FACE);
			boolean flag = true;
			double d19 = 0.025D;
			tessellator.startDrawing(5);
			int i;
			float f2;

			for (i = 0; i <= 24; ++i) {
				if (i % 2 == 0) {
					tessellator.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
				} else {
					tessellator.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
				}

				f2 = (float) i / 24.0F;
				tessellator
						.addVertex(x + d16 * (double) f2 + 0.0D, 
								y + d17 * (double) (f2 * f2 + f2) * 0.5 + (double) ((24.0F - (float) i) / 18.0F + 0.125F),
								z + d18 * (double) f2);
				tessellator.addVertex(x + d16 * (double) f2 + 0.025D,
						y + d17 * (double) (f2 * f2 + f2) * 0.5D + (double) ((24.0F - (float) i) / 18.0F + 0.125F) + 0.025D,
						z + d18 * (double) f2);
			}

			tessellator.draw();
			tessellator.startDrawing(5);

			for (i = 0; i <= 24; ++i) {
				if (i % 2 == 0) {
					tessellator.setColorRGBA_F(0.5F, 0.4F, 0.3F, 1.0F);
				} else {
					tessellator.setColorRGBA_F(0.35F, 0.28F, 0.21000001F, 1.0F);
				}

				f2 = (float) i / 24.0F;
				tessellator.addVertex(x + d16 * (double) f2 + 0.0D,
						y + d17 * (double) (f2 * f2 + f2) * 0.5D + (double) ((24.0F - (float) i) / 18.0F + 0.125F) + 0.025D,
						z + d18 * (double) f2);
				tessellator.addVertex(x + d16 * (double) f2 + 0.025D,
						y + d17 * (double) (f2 * f2 + f2) * 0.5D + (double) ((24.0F - (float) i) / 18.0F + 0.125F),
						z + d18 * (double) f2 + 0.025D);
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
	}
}
