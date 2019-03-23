package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGun extends ModelBase
{
	ModelRenderer Middle;
	ModelRenderer Handle;
	ModelRenderer FrontTop;
	ModelRenderer FrontBottom;
	ModelRenderer FrontLeft;
	ModelRenderer FrontRight;
	ModelRenderer FrontMiddle;

	public ModelGun()
	{
		textureWidth = 16;
		textureHeight = 16;

		Middle = new ModelRenderer(this, 0, 0);
		Middle.addBox(-1F, -1F, -5F, 2, 2, 6);
		Middle.setRotationPoint(0F, 0F, 0F);
		Middle.setTextureSize(16, 16);
		Middle.mirror = true;
		setRotation(Middle, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 0, 0);
		Handle.addBox(-0.5F, 0F, 0F, 1, 5, 1);
		Handle.setRotationPoint(0F, 0F, 0F);
		Handle.setTextureSize(16, 16);
		Handle.mirror = true;
		setRotation(Handle, 45F, 0F, 0F);
		FrontTop = new ModelRenderer(this, 0, 10);
		FrontTop.addBox(-0.5F, -4F, -7F, 1, 1, 4);
		FrontTop.setRotationPoint(0F, 0F, 0F);
		FrontTop.setTextureSize(16, 16);
		FrontTop.mirror = true;
		setRotation(FrontTop, 1F, 0F, 0F);
		FrontBottom = new ModelRenderer(this, 0, 10);
		FrontBottom.addBox(-0.5F, 3F, -7F, 1, 1, 4);
		FrontBottom.setRotationPoint(0F, 0F, 0F);
		FrontBottom.setTextureSize(16, 16);
		FrontBottom.mirror = true;
		setRotation(FrontBottom, -1F, 0F, 0F);
		FrontLeft = new ModelRenderer(this, 0, 10);
		FrontLeft.addBox(-4F, -0.5F, -7F, 1, 1, 4);
		FrontLeft.setRotationPoint(0F, 0F, 0F);
		FrontLeft.setTextureSize(16, 16);
		FrontLeft.mirror = true;
		setRotation(FrontLeft, 0F, -1F, 0F);
		FrontRight = new ModelRenderer(this, 0, 10);
		FrontRight.addBox(3F, -0.5F, -7F, 1, 1, 4);
		FrontRight.setRotationPoint(0F, 0F, 0F);
		FrontRight.setTextureSize(16, 16);
		FrontRight.mirror = true;
		setRotation(FrontRight, 0F, 1F, 0F);
		FrontMiddle = new ModelRenderer(this, 0, 0);
		FrontMiddle.addBox(-0.5F, -0.5F, -5.5F, 1, 1, 1);
		FrontMiddle.setRotationPoint(0F, 0F, 0F);
		FrontMiddle.setTextureSize(16, 16);
		FrontMiddle.mirror = true;
		setRotation(FrontMiddle, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Middle.render(f5);
		Handle.render(f5);
		FrontTop.render(f5);
		FrontBottom.render(f5);
		FrontLeft.render(f5);
		FrontRight.render(f5);
		FrontMiddle.render(f5);
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
