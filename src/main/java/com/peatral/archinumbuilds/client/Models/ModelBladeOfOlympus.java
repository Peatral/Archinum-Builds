package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBladeOfOlympus extends ModelBase
{
	ModelRenderer HandleMiddle;
	ModelRenderer HandleBottom;
	ModelRenderer SpikeBottom;
	ModelRenderer SpikeMiddle1;
	ModelRenderer SpikeMiddle2;
	ModelRenderer SpikeTop;
	ModelRenderer BladeMiddleRight1;
	ModelRenderer BladeMiddleRight2;
	ModelRenderer BladeMiddleLeft1;
	ModelRenderer BladeMiddleLeft2;
	ModelRenderer BladeTop2;
	ModelRenderer BladeTop1;
	ModelRenderer Middle1;
	ModelRenderer Middle2;

	public ModelBladeOfOlympus()
	{
		textureWidth = 64;
		textureHeight = 64;

		HandleMiddle = new ModelRenderer(this, 0, 0);
		HandleMiddle.addBox(-1F, -7F, -1F, 2, 16, 2);
		HandleMiddle.setRotationPoint(0F, 0F, 0F);
		HandleMiddle.setTextureSize(64, 64);
		HandleMiddle.mirror = true;
		setRotation(HandleMiddle, 0F, 0F, 0F);
		HandleBottom = new ModelRenderer(this, 0, 19);
		HandleBottom.addBox(-0.5F, 8.5F, -0.5F, 1, 2, 1);
		HandleBottom.setRotationPoint(0F, 0F, 0F);
		HandleBottom.setTextureSize(64, 64);
		HandleBottom.mirror = true;
		setRotation(HandleBottom, 0F, 0F, 0F);
		SpikeBottom = new ModelRenderer(this, 20, 59);
		SpikeBottom.addBox(-1.5F, -0.7F, -0.5F, 3, 1, 1);
		SpikeBottom.setRotationPoint(0F, 0F, 0F);
		SpikeBottom.setTextureSize(64, 64);
		SpikeBottom.mirror = true;
		setRotation(SpikeBottom, 0F, 0F, 0F);
		SpikeMiddle1 = new ModelRenderer(this, 4, 54);
		SpikeMiddle1.addBox(-4.5F, -2F, -1.5F, 9, 1, 3);
		SpikeMiddle1.setRotationPoint(0F, 0F, 0F);
		SpikeMiddle1.setTextureSize(64, 64);
		SpikeMiddle1.mirror = true;
		setRotation(SpikeMiddle1, 0F, 0F, 0F);
		SpikeMiddle2 = new ModelRenderer(this, 0, 49);
		SpikeMiddle2.addBox(-5.5F, -5F, -1.5F, 11, 1, 3);
		SpikeMiddle2.setRotationPoint(0F, 0F, 0F);
		SpikeMiddle2.setTextureSize(64, 64);
		SpikeMiddle2.mirror = true;
		setRotation(SpikeMiddle2, 0F, 0F, 0F);
		SpikeTop = new ModelRenderer(this, 12, 46);
		SpikeTop.addBox(-3.5F, -6.7F, -0.5F, 7, 1, 1);
		SpikeTop.setRotationPoint(0F, 0F, 0F);
		SpikeTop.setTextureSize(64, 64);
		SpikeTop.mirror = true;
		setRotation(SpikeTop, 0F, 0F, 0F);
		BladeMiddleRight1 = new ModelRenderer(this, 18, 0);
		BladeMiddleRight1.addBox(1.5F, -39F, -0.5F, 1, 32, 1);
		BladeMiddleRight1.setRotationPoint(0F, 0F, 0F);
		BladeMiddleRight1.setTextureSize(64, 64);
		BladeMiddleRight1.mirror = true;
		setRotation(BladeMiddleRight1, 0F, 0F, 0F);
		BladeMiddleRight2 = new ModelRenderer(this, 12, 0);
		BladeMiddleRight2.addBox(-2.5F, -39F, -1F, 1, 32, 2);
		BladeMiddleRight2.setRotationPoint(1F, 0F, 0F);
		BladeMiddleRight2.setTextureSize(64, 64);
		BladeMiddleRight2.mirror = true;
		setRotation(BladeMiddleRight2, 0F, 0F, 0F);
		BladeMiddleLeft1 = new ModelRenderer(this, 8, 0);
		BladeMiddleLeft1.addBox(-2.5F, -39F, -0.5F, 1, 32, 1);
		BladeMiddleLeft1.setRotationPoint(0F, 0F, 0F);
		BladeMiddleLeft1.setTextureSize(64, 64);
		BladeMiddleLeft1.mirror = true;
		setRotation(BladeMiddleLeft1, 0F, 0F, 0F);
		BladeMiddleLeft2 = new ModelRenderer(this, 22, 0);
		BladeMiddleLeft2.addBox(-0.5F, -39F, -1F, 1, 32, 2);
		BladeMiddleLeft2.setRotationPoint(1F, 0F, 0F);
		BladeMiddleLeft2.setTextureSize(64, 64);
		BladeMiddleLeft2.mirror = true;
		setRotation(BladeMiddleLeft2, 0F, 0F, 0F);
		BladeTop2 = new ModelRenderer(this, 0, 39);
		BladeTop2.addBox(-2F, -45F, -0.5F, 4, 6, 1);
		BladeTop2.setRotationPoint(0F, 0F, 0F);
		BladeTop2.setTextureSize(64, 64);
		BladeTop2.mirror = true;
		setRotation(BladeTop2, 0F, 0F, 0F);
		BladeTop1 = new ModelRenderer(this, 0, 36);
		BladeTop1.addBox(-1F, -47F, -0.5F, 2, 2, 1);
		BladeTop1.setRotationPoint(0F, 0F, 0F);
		BladeTop1.setTextureSize(64, 64);
		BladeTop1.mirror = true;
		setRotation(BladeTop1, 0F, 0F, 0F);
		Middle1 = new ModelRenderer(this, 0, 23);
		Middle1.addBox(-0.5F, -4F, -1.5F, 1, 2, 3);
		Middle1.setRotationPoint(0F, 0F, 0F);
		Middle1.setTextureSize(64, 64);
		Middle1.mirror = true;
		setRotation(Middle1, 0F, 0F, 0F);
		Middle2 = new ModelRenderer(this, 0, 25);
		Middle2.addBox(-1.5F, -4F, -0.5F, 3, 2, 1);
		Middle2.setRotationPoint(0F, 0F, 0F);
		Middle2.setTextureSize(64, 64);
		Middle2.mirror = true;
		setRotation(Middle2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		HandleMiddle.render(f5);
		HandleBottom.render(f5);
		SpikeBottom.render(f5);
		SpikeMiddle1.render(f5);
		SpikeMiddle2.render(f5);
		SpikeTop.render(f5);
		BladeMiddleRight1.render(f5);
		BladeMiddleRight2.render(f5);
		BladeMiddleLeft1.render(f5);
		BladeMiddleLeft2.render(f5);
		BladeTop2.render(f5);
		BladeTop1.render(f5);
		Middle1.render(f5);
		Middle2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}

}
