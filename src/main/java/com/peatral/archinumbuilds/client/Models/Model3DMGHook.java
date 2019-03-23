package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3DMGHook extends ModelBase {
	ModelRenderer Shaft;
	ModelRenderer MetalTop;
	ModelRenderer MetalLeft;
	ModelRenderer MetalBottom;
	ModelRenderer MetalRight;

	public Model3DMGHook() {
		textureWidth = 64;
		textureHeight = 32;

		Shaft = new ModelRenderer(this, 0, 0);
		Shaft.addBox(-0.5F, 20.5F, -1.5F, 1, 1, 6);
		Shaft.setRotationPoint(0F, 0F, 0F);
		Shaft.setTextureSize(64, 32);
		Shaft.mirror = true;
		setRotation(Shaft, 0F, 0F, 0F);
		MetalTop = new ModelRenderer(this, 8, 0);
		MetalTop.addBox(-0.5F, -1.5F, -0.7F, 1, 1, 3);
		MetalTop.setRotationPoint(0F, 21F, 0F);
		MetalTop.setTextureSize(64, 32);
		MetalTop.mirror = true;
		setRotation(MetalTop, 0.3F, 0F, 0F);
		MetalLeft = new ModelRenderer(this, 8, 0);
		MetalLeft.addBox(0.5F, 20.5F, -0.7F, 1, 1, 3);
		MetalLeft.setRotationPoint(0F, 0F, 0F);
		MetalLeft.setTextureSize(64, 32);
		MetalLeft.mirror = true;
		setRotation(MetalLeft, 0F, 0.3F, 0F);
		MetalBottom = new ModelRenderer(this, 8, 0);
		MetalBottom.addBox(-0.5F, 0.5F, -0.7F, 1, 1, 3);
		MetalBottom.setRotationPoint(0F, 21F, 0F);
		MetalBottom.setTextureSize(64, 32);
		MetalBottom.mirror = true;
		setRotation(MetalBottom, -0.3F, 0F, 0F);
		MetalRight = new ModelRenderer(this, 8, 0);
		MetalRight.addBox(-1.5F, 20.5F, -0.7F, 1, 1, 3);
		MetalRight.setRotationPoint(0F, 0F, 0F);
		MetalRight.setTextureSize(64, 32);
		MetalRight.mirror = true;
		setRotation(MetalRight, 0F, -0.3F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shaft.render(f5);
		MetalTop.render(f5);
		MetalLeft.render(f5);
		MetalBottom.render(f5);
		MetalRight.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}

}
