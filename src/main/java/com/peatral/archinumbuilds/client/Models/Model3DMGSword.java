package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3DMGSword extends ModelBase {
	ModelRenderer Blade;
	ModelRenderer BladeTop;
	ModelRenderer HandleTop;
	ModelRenderer HandleFront;
	ModelRenderer GripFront;
	ModelRenderer GripConnector;
	ModelRenderer GripBottom1;
	ModelRenderer GripBottom;
	ModelRenderer HandleBottom;
	ModelRenderer Handle;
	
	
	public Model3DMGSword() {
		textureWidth = 64;
		textureHeight = 32;

		Blade = new ModelRenderer(this, 0, 0);
		Blade.addBox(0F, -17F, 0F, 1, 17, 2);
		Blade.setRotationPoint(0F, 0F, 0F);
		Blade.setTextureSize(64, 32);
		Blade.mirror = true;
		setRotation(Blade, 0F, 0F, 0F);
		BladeTop = new ModelRenderer(this, 23, 0);
		BladeTop.addBox(0F, -18F, 0F, 1, 1, 1);
		BladeTop.setRotationPoint(0F, 0F, 0F);
		BladeTop.setTextureSize(64, 32);
		BladeTop.mirror = true;
		setRotation(BladeTop, 0F, 0F, 0F);
		HandleTop = new ModelRenderer(this, 12, 0);
		HandleTop.addBox(-0.5F, 0F, -0.5F, 2, 2, 3);
		HandleTop.setRotationPoint(0F, 0F, 0F);
		HandleTop.setTextureSize(64, 32);
		HandleTop.mirror = true;
		setRotation(HandleTop, 0F, 0F, 0F);
		HandleFront = new ModelRenderer(this, 23, 0);
		HandleFront.addBox(0F, 2F, -1F, 1, 2, 1);
		HandleFront.setRotationPoint(0F, 0F, 0F);
		HandleFront.setTextureSize(64, 32);
		HandleFront.mirror = true;
		setRotation(HandleFront, 0F, 0F, 0F);
		GripFront = new ModelRenderer(this, 6, 7);
		GripFront.addBox(-0.1F, -0.3F, -2.9F, 1, 3, 1);
		GripFront.setRotationPoint(0F, 0F, 0F);
		GripFront.setTextureSize(64, 32);
		GripFront.mirror = true;
		setRotation(GripFront, 0F, 0F, 0F);
		GripConnector = new ModelRenderer(this, 8, 9);
		GripConnector.addBox(0F, 0.6F, -2.3F, 1, 1, 3);
		GripConnector.setRotationPoint(0F, 0F, 0F);
		GripConnector.setTextureSize(64, 32);
		GripConnector.mirror = true;
		setRotation(GripConnector, -0.2F, 0F, 0F);
		GripBottom1 = new ModelRenderer(this, 6, 7);
		GripBottom1.addBox(0.1F, 4.5F, -1.6F, 1, 2, 1);
		GripBottom1.setRotationPoint(0F, 0F, 0F);
		GripBottom1.setTextureSize(64, 32);
		GripBottom1.mirror = true;
		setRotation(GripBottom1, -0.2F, 0F, 0F);
		GripBottom = new ModelRenderer(this, 6, 7);
		GripBottom.addBox(0F, 2.3F, -3F, 1, 2, 1);
		GripBottom.setRotationPoint(0F, 0F, 0F);
		GripBottom.setTextureSize(64, 32);
		GripBottom.mirror = true;
		setRotation(GripBottom, 0.1F, 0F, 0F);
		HandleBottom = new ModelRenderer(this, 12, 0);
		HandleBottom.addBox(-0.5F, 5.5F, -0.5F, 2, 1, 3);
		HandleBottom.setRotationPoint(0F, 0F, 0F);
		HandleBottom.setTextureSize(64, 32);
		HandleBottom.mirror = true;
		setRotation(HandleBottom, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 6, 0);
		Handle.addBox(0F, 2F, 0F, 1, 5, 2);
		Handle.setRotationPoint(0F, 0F, 0F);
		Handle.setTextureSize(64, 32);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		Blade.render(f5);
		BladeTop.render(f5);
		HandleTop.render(f5);
		HandleFront.render(f5);
		GripFront.render(f5);
		GripConnector.render(f5);
		GripBottom1.render(f5);
		GripBottom.render(f5);
		HandleBottom.render(f5);
		Handle.render(f5);
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
