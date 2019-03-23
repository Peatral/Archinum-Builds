package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergyBall extends ModelBase {
	ModelRenderer Middle;
	ModelRenderer ShapeX;
	ModelRenderer ShapeY;
	ModelRenderer ShapeZ;

	public ModelEnergyBall() {
		textureWidth = 64;
		textureHeight = 32;

		Middle = new ModelRenderer(this, 0, 0);
		Middle.addBox(-2F, 19F, -2F, 4, 4, 4);
		Middle.setRotationPoint(0F, 0F, 0F);
		Middle.setTextureSize(64, 32);
		Middle.mirror = true;
		setRotation(Middle, 0F, 0F, 0F);
		ShapeX = new ModelRenderer(this, 0, 0);
		ShapeX.addBox(-3F, 20F, -1F, 6, 2, 2);
		ShapeX.setRotationPoint(0F, 0F, 0F);
		ShapeX.setTextureSize(64, 32);
		ShapeX.mirror = true;
		setRotation(ShapeX, 0F, 0F, 0F);
		ShapeY = new ModelRenderer(this, 0, 0);
		ShapeY.addBox(-1F, 18F, -1F, 2, 6, 2);
		ShapeY.setRotationPoint(0F, 0F, 0F);
		ShapeY.setTextureSize(64, 32);
		ShapeY.mirror = true;
		setRotation(ShapeY, 0F, 0F, 0F);
		ShapeZ = new ModelRenderer(this, 0, 0);
		ShapeZ.addBox(-1F, 20F, -3F, 2, 2, 6);
		ShapeZ.setRotationPoint(0F, 0F, 0F);
		ShapeZ.setTextureSize(64, 32);
		ShapeZ.mirror = true;
		setRotation(ShapeZ, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Middle.render(f5);
		ShapeX.render(f5);
		ShapeY.render(f5);
		ShapeZ.render(f5);
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
