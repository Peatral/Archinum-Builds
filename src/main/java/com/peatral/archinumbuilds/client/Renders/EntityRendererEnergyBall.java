package com.peatral.archinumbuilds.client.Renders;

import org.lwjgl.opengl.GL11;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.client.Models.ModelEnergyBall;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class EntityRendererEnergyBall extends Render
{
	private static final ResourceLocation texture = new ResourceLocation(ArchinumBuilds.MODID, "textures/entity/energyBall.png");
	private ModelBase model;

	public EntityRendererEnergyBall()
	{
		model = new ModelEnergyBall();
	}

	@Override
	public ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick)
	{
		GL11.glPushMatrix();
		bindTexture(texture);
		GL11.glTranslated(x, y - 1.25D, z);
		model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
