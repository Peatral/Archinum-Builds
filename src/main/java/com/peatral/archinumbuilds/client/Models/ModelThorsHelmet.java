package com.peatral.archinumbuilds.client.Models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.*;

public class ModelThorsHelmet extends ModelBiped
{
	ModelRenderer HelmetTop;
	ModelRenderer HelmetBack;
	ModelRenderer HelmetLeft1;
	ModelRenderer HelmetLeft2;
	ModelRenderer HelmetLeftTop1;
	ModelRenderer HelmetLeftTop2;
	ModelRenderer HelmetRight1;
	ModelRenderer HelmetRight2;
	ModelRenderer HelmetRightTop1;
	ModelRenderer HelmetRightTop2;
	ModelRenderer HelmetLeftDown2;
	ModelRenderer HelmetRightDown2;
	ModelRenderer HelmetLeftDown1;
	ModelRenderer HelmetRightDown1;

	public ModelThorsHelmet()
	{
		textureWidth = 64;
		textureHeight = 64;

		HelmetTop = new ModelRenderer(this, 0, 33);
		HelmetTop.addBox(-4.5F, -8.5F, -4.5F, 9, 1, 9);
		HelmetTop.setRotationPoint(0F, 0F, 0F);
		HelmetTop.setTextureSize(64, 64);
		HelmetTop.mirror = true;
		setRotation(HelmetTop, 0F, 0F, 0F);
		HelmetBack = new ModelRenderer(this, 37, 33);
		HelmetBack.addBox(-3.5F, -7.5F, 3.5F, 7, 5, 1);
		HelmetBack.setRotationPoint(0F, 0F, 0F);
		HelmetBack.setTextureSize(64, 64);
		HelmetBack.mirror = true;
		setRotation(HelmetBack, 0F, 0F, 0F);
		HelmetLeft1 = new ModelRenderer(this, 0, 44);
		HelmetLeft1.addBox(3.5F, -7.5F, -3.5F, 1, 3, 8);
		HelmetLeft1.setRotationPoint(0F, 0F, 0F);
		HelmetLeft1.setTextureSize(64, 64);
		HelmetLeft1.mirror = true;
		setRotation(HelmetLeft1, 0F, 0F, 0F);
		HelmetLeft2 = new ModelRenderer(this, 19, 44);
		HelmetLeft2.addBox(4F, -6.5F, -1.5F, 1, 3, 3);
		HelmetLeft2.setRotationPoint(0F, 0F, 0F);
		HelmetLeft2.setTextureSize(64, 64);
		HelmetLeft2.mirror = true;
		setRotation(HelmetLeft2, 0F, 0F, 0F);
		HelmetLeftTop1 = new ModelRenderer(this, 28, 44);
		HelmetLeftTop1.addBox(3F, -11F, -3.5F, 1, 3, 2);
		HelmetLeftTop1.setRotationPoint(0F, 0F, 0F);
		HelmetLeftTop1.setTextureSize(64, 64);
		HelmetLeftTop1.mirror = true;
		setRotation(HelmetLeftTop1, -0.7504916F, 0F, 0F);
		HelmetLeftTop2 = new ModelRenderer(this, 19, 51);
		HelmetLeftTop2.addBox(3.5F, -2.8F, 3F, 1, 2, 1);
		HelmetLeftTop2.setRotationPoint(0F, 0F, 0F);
		HelmetLeftTop2.setTextureSize(64, 64);
		HelmetLeftTop2.mirror = true;
		setRotation(HelmetLeftTop2, 2.181662F, 0F, 0F);
		HelmetRight1 = new ModelRenderer(this, 0, 44);
		HelmetRight1.addBox(-4.5F, -7.5F, -3.5F, 1, 3, 8);
		HelmetRight1.setRotationPoint(0F, 0F, 0F);
		HelmetRight1.setTextureSize(64, 64);
		HelmetRight1.mirror = true;
		setRotation(HelmetRight1, 0F, 0F, 0F);
		HelmetRight2 = new ModelRenderer(this, 19, 44);
		HelmetRight2.addBox(4F, -6.5F, -1.5F, 1, 3, 3);
		HelmetRight2.setRotationPoint(0F, 0F, 0F);
		HelmetRight2.setTextureSize(64, 64);
		HelmetRight2.mirror = true;
		setRotation(HelmetRight2, 0F, 3.141593F, 0F);
		HelmetRightTop1 = new ModelRenderer(this, 28, 44);
		HelmetRightTop1.addBox(-4F, -11F, -3.5F, 1, 3, 2);
		HelmetRightTop1.setRotationPoint(0F, 0F, 0F);
		HelmetRightTop1.setTextureSize(64, 64);
		HelmetRightTop1.mirror = true;
		setRotation(HelmetRightTop1, -0.7504916F, 0F, 0F);
		HelmetRightTop2 = new ModelRenderer(this, 19, 51);
		HelmetRightTop2.addBox(-4.5F, -2.8F, 3F, 1, 2, 1);
		HelmetRightTop2.setRotationPoint(0F, 0F, 0F);
		HelmetRightTop2.setTextureSize(64, 64);
		HelmetRightTop2.mirror = true;
		setRotation(HelmetRightTop2, 2.181662F, 0F, 0F);
		HelmetLeftDown2 = new ModelRenderer(this, 19, 51);
		HelmetLeftDown2.addBox(2.7F, -12.3F, -4.5F, 1, 2, 1);
		HelmetLeftDown2.setRotationPoint(0F, 0F, 0F);
		HelmetLeftDown2.setTextureSize(64, 64);
		HelmetLeftDown2.mirror = true;
		setRotation(HelmetLeftDown2, -0.9075712F, 0F, 0F);
		HelmetRightDown2 = new ModelRenderer(this, 19, 51);
		HelmetRightDown2.addBox(-3.7F, -12.3F, -4.5F, 1, 2, 1);
		HelmetRightDown2.setRotationPoint(0F, 0F, 0F);
		HelmetRightDown2.setTextureSize(64, 64);
		HelmetRightDown2.mirror = true;
		setRotation(HelmetRightDown2, -0.9075712F, 0F, 0F);
		HelmetLeftDown1 = new ModelRenderer(this, 28, 44);
		HelmetLeftDown1.addBox(3.7F, -2.6F, -4.5F, 1, 3, 2);
		HelmetLeftDown1.setRotationPoint(0F, 0F, 0F);
		HelmetLeftDown1.setTextureSize(64, 64);
		HelmetLeftDown1.mirror = true;
		setRotation(HelmetLeftDown1, -0.7853982F, 0F, 0F);
		HelmetRightDown1 = new ModelRenderer(this, 28, 44);
		HelmetRightDown1.addBox(-4.7F, -2.6F, -4.5F, 1, 3, 2);
		HelmetRightDown1.setRotationPoint(0F, 0F, 0F);
		HelmetRightDown1.setTextureSize(64, 64);
		HelmetRightDown1.mirror = true;
		setRotation(HelmetRightDown1, -0.7853982F, 0F, 0F);

		this.bipedHead.addChild(HelmetTop);
		this.bipedHead.addChild(HelmetBack);
		this.bipedHead.addChild(HelmetLeft1);
		this.bipedHead.addChild(HelmetLeft2);
		this.bipedHead.addChild(HelmetRight1);
		this.bipedHead.addChild(HelmetRight2);
		this.bipedHead.addChild(HelmetLeftDown1);
		this.bipedHead.addChild(HelmetLeftDown2);
		this.bipedHead.addChild(HelmetRightDown1);
		this.bipedHead.addChild(HelmetRightDown2);
		this.bipedHead.addChild(HelmetLeftTop1);
		this.bipedHead.addChild(HelmetLeftTop2);
		this.bipedHead.addChild(HelmetRightTop1);
		this.bipedHead.addChild(HelmetRightTop2);
	}

	/*public void render(entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		HelmetTop.render(f5);
		HelmetBack.render(f5);
		HelmetLeft1.render(f5);
		HelmetLeft2.render(f5);
		HelmetLeftTop1.render(f5);
		HelmetLeftTop2.render(f5);
		HelmetRight1.render(f5);
		HelmetRight2.render(f5);
		HelmetRightTop1.render(f5);
		HelmetRightTop2.render(f5);
		HelmetLeftDown2.render(f5);
		HelmetRightDown2.render(f5);
		HelmetLeftDown1.render(f5);
		HelmetRightDown1.render(f5);
	}*/

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
