package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelExcalibur extends ModelBase
{
	ModelRenderer ShaftKnob;
	ModelRenderer Shaft;
	ModelRenderer HandleRight2;
	ModelRenderer HandleMiddle;
	ModelRenderer HandleLeft2;
	ModelRenderer HandleLeft1;
	ModelRenderer HandleRight1;
	ModelRenderer BlabeTip;
	ModelRenderer Blade;

	public ModelExcalibur()
	{
		textureWidth = 64;
		textureHeight = 32;

		ShaftKnob = new ModelRenderer(this, 0, 12);
		ShaftKnob.addBox(-1F, 9F, -1F, 2, 1, 2);
		ShaftKnob.setRotationPoint(0F, 0F, 0F);
		ShaftKnob.setTextureSize(64, 32);
		ShaftKnob.mirror = true;
		setRotation(ShaftKnob, 0F, 0F, 0F);
		Shaft = new ModelRenderer(this, 0, 15);
		Shaft.addBox(-0.5F, 0.5F, -0.5F, 1, 9, 1);
		Shaft.setRotationPoint(0F, 0F, 0F);
		Shaft.setTextureSize(64, 32);
		Shaft.mirror = true;
		setRotation(Shaft, 0F, 0F, 0F);
		HandleRight2 = new ModelRenderer(this, 0, 5);
		HandleRight2.addBox(-1.9F, -5F, -0.5F, 1, 2, 1);
		HandleRight2.setRotationPoint(0F, 0F, 0F);
		HandleRight2.setTextureSize(64, 32);
		HandleRight2.mirror = true;
		setRotation(HandleRight2, 0F, 0F, -0.8726646F);
		HandleMiddle = new ModelRenderer(this, 0, 8);
		HandleMiddle.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		HandleMiddle.setRotationPoint(0F, 0F, 0F);
		HandleMiddle.setTextureSize(64, 32);
		HandleMiddle.mirror = true;
		setRotation(HandleMiddle, 0F, 0F, 0F);
		HandleLeft2 = new ModelRenderer(this, 0, 5);
		HandleLeft2.addBox(0.9F, -5F, -0.5F, 1, 2, 1);
		HandleLeft2.setRotationPoint(0F, 0F, 0F);
		HandleLeft2.setTextureSize(64, 32);
		HandleLeft2.mirror = true;
		setRotation(HandleLeft2, 0F, 0F, 0.8726646F);
		HandleLeft1 = new ModelRenderer(this, 0, 0);
		HandleLeft1.addBox(0.4F, -3.3F, -1F, 1, 3, 2);
		HandleLeft1.setRotationPoint(0F, 0F, 0F);
		HandleLeft1.setTextureSize(64, 32);
		HandleLeft1.mirror = true;
		setRotation(HandleLeft1, 0F, 0F, 1.047198F);
		HandleRight1 = new ModelRenderer(this, 0, 0);
		HandleRight1.addBox(-1.4F, -3.3F, -1F, 1, 3, 2);
		HandleRight1.setRotationPoint(0F, 0F, 0F);
		HandleRight1.setTextureSize(64, 32);
		HandleRight1.mirror = true;
		setRotation(HandleRight1, 0F, 0F, -1.047198F);
		BlabeTip = new ModelRenderer(this, 12, 0);
		BlabeTip.addBox(-0.5F, -19.5F, -0.5F, 1, 3, 1);
		BlabeTip.setRotationPoint(0F, 0F, 0F);
		BlabeTip.setTextureSize(64, 32);
		BlabeTip.mirror = true;
		setRotation(BlabeTip, 0F, 0F, 0F);
		Blade = new ModelRenderer(this, 12, 0);
		Blade.addBox(-1F, -16.5F, -0.5F, 2, 17, 1);
		Blade.setRotationPoint(0F, 0F, 0F);
		Blade.setTextureSize(64, 32);
		Blade.mirror = true;
		setRotation(Blade, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		ShaftKnob.render(f5);
		Shaft.render(f5);
		HandleRight2.render(f5);
		HandleMiddle.render(f5);
		HandleLeft2.render(f5);
		HandleLeft1.render(f5);
		HandleRight1.render(f5);
		BlabeTip.render(f5);
		Blade.render(f5);
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
