package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3DMGSingle extends ModelBiped {

	ModelRenderer MGNodLeft;
	ModelRenderer MGNodRight;
	ModelRenderer MGBack;
	ModelRenderer MGBack2;
	ModelRenderer MGBackThingLeft;
	ModelRenderer MGBackThingRight;
	ModelRenderer MGBackThingConnectorRight;
	ModelRenderer MGBackThingConnectorLeft;
	ModelRenderer MGBackConnectorLeft;
	ModelRenderer MGBackConnectorRight;

	public Model3DMGSingle() {
		textureWidth = 64;
		textureHeight = 32;

		MGNodLeft = new ModelRenderer(this, 32, 0);
		MGNodLeft.addBox(1F, 9F, 1.5F, 2, 2, 1);
		MGNodLeft.setRotationPoint(0F, 0F, 0F);
		MGNodLeft.setTextureSize(64, 64);
		MGNodLeft.mirror = true;
		setRotation(MGNodLeft, 0F, 0F, 0F);
		MGNodRight = new ModelRenderer(this, 32, 0);
		MGNodRight.addBox(-3F, 9F, 1.5F, 2, 2, 1);
		MGNodRight.setRotationPoint(0F, 0F, 0F);
		MGNodRight.setTextureSize(64, 64);
		MGNodRight.mirror = true;
		setRotation(MGNodRight, 0F, 0F, 0F);
		MGBack = new ModelRenderer(this, 38, 0);
		MGBack.addBox(-1.5F, 10F, 3F, 3, 2, 3);
		MGBack.setRotationPoint(0F, 0F, 0F);
		MGBack.setTextureSize(64, 64);
		MGBack.mirror = true;
		setRotation(MGBack, 0F, 0F, 0F);
		MGBack2 = new ModelRenderer(this, 32, 3);
		MGBack2.addBox(-0.5F, 10.86667F, 5.3F, 1, 1, 2);
		MGBack2.setRotationPoint(0F, 0F, 0F);
		MGBack2.setTextureSize(64, 64);
		MGBack2.mirror = true;
		setRotation(MGBack2, 0F, 0F, 0F);
		MGBackThingLeft = new ModelRenderer(this, 50, 6);
		MGBackThingLeft.addBox(2F, 8.5F, 3.5F, 2, 5, 5);
		MGBackThingLeft.setRotationPoint(0F, 0F, 0F);
		MGBackThingLeft.setTextureSize(64, 64);
		MGBackThingLeft.mirror = true;
		setRotation(MGBackThingLeft, 0F, 0.2F, 0F);
		MGBackThingRight = new ModelRenderer(this, 43, 6);
		MGBackThingRight.addBox(-4F, 8.5F, 3.5F, 2, 5, 5);
		MGBackThingRight.setRotationPoint(0F, 0F, 0F);
		MGBackThingRight.setTextureSize(64, 64);
		MGBackThingRight.mirror = true;
		setRotation(MGBackThingRight, 0F, -0.2F, 0F);
		MGBackThingConnectorRight = new ModelRenderer(this, 32, 0);
		MGBackThingConnectorRight.addBox(-2.1F, 10.5F, 5.5F, 2, 1, 1);
		MGBackThingConnectorRight.setRotationPoint(0F, 0F, 0F);
		MGBackThingConnectorRight.setTextureSize(64, 64);
		MGBackThingConnectorRight.mirror = true;
		setRotation(MGBackThingConnectorRight, 0F, -0.2F, 0F);
		MGBackThingConnectorLeft = new ModelRenderer(this, 32, 0);
		MGBackThingConnectorLeft.addBox(0.1F, 10.5F, 5.5F, 2, 1, 1);
		MGBackThingConnectorLeft.setRotationPoint(0F, 0F, 0F);
		MGBackThingConnectorLeft.setTextureSize(64, 64);
		MGBackThingConnectorLeft.mirror = true;
		setRotation(MGBackThingConnectorLeft, 0F, 0.2F, 0F);
		MGBackConnectorLeft = new ModelRenderer(this, 40, 5);
		MGBackConnectorLeft.addBox(2.5F, 9F, 3F, 1, 1, 3);
		MGBackConnectorLeft.setRotationPoint(0F, 0F, 0F);
		MGBackConnectorLeft.setTextureSize(64, 64);
		MGBackConnectorLeft.mirror = true;
		setRotation(MGBackConnectorLeft, -0.3F, -0.7F, 0F);
		MGBackConnectorRight = new ModelRenderer(this, 40, 5);
		MGBackConnectorRight.addBox(-3.5F, 9F, 3F, 1, 1, 3);
		MGBackConnectorRight.setRotationPoint(0F, 0F, 0F);
		MGBackConnectorRight.setTextureSize(64, 64);
		MGBackConnectorRight.mirror = true;
		setRotation(MGBackConnectorRight, -0.3F, 0.7F, 0F);
		
		this.bipedBody.addChild(MGBack);
		this.bipedBody.addChild(MGBack2);
		this.bipedBody.addChild(MGBackConnectorLeft);
		this.bipedBody.addChild(MGBackConnectorRight);
		this.bipedBody.addChild(MGBackThingConnectorLeft);
		this.bipedBody.addChild(MGBackThingConnectorRight);
		this.bipedBody.addChild(MGBackThingLeft);
		this.bipedBody.addChild(MGBackThingRight);
		this.bipedBody.addChild(MGNodLeft);
		this.bipedBody.addChild(MGNodRight);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		MGNodLeft.render(f5);
		MGNodRight.render(f5);
		MGBack.render(f5);
		MGBack2.render(f5);
		MGBackThingLeft.render(f5);
		MGBackThingRight.render(f5);
		MGBackThingConnectorRight.render(f5);
		MGBackThingConnectorLeft.render(f5);
		MGBackConnectorLeft.render(f5);
		MGBackConnectorRight.render(f5);
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
