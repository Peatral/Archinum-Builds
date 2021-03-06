package com.peatral.archinumbuilds.client.Renders;

import org.lwjgl.opengl.GL11;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.client.Models.ModelGun;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererGun implements IItemRenderer{

	protected ModelGun model;
	public ItemRendererGun(){
		model = new ModelGun();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
		case ENTITY:
		case EQUIPPED:
		case EQUIPPED_FIRST_PERSON:
			return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type){
		case ENTITY:
			GL11.glPushMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ArchinumBuilds.MODID, "textures/models/gunModel.png"));
			GL11.glScalef(2F, 2F, 2F);
			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
		case EQUIPPED:
			GL11.glPushMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ArchinumBuilds.MODID, "textures/models/gunModel.png"));
			GL11.glTranslatef(0.5F, 0.5F, 0F);

			GL11.glRotated(-100, 0, 0, 1);
			GL11.glRotated(90, 0, 1, 0);

			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(ArchinumBuilds.MODID, "textures/models/gunModel.png"));
			GL11.glTranslatef(0.5F, 0.7F, 0F);
			
			GL11.glRotated(-145, 0, 0, 1);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScalef(1.5F, 1.5F, 1.5F);

			model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glPopMatrix();
			break;
		default:
			break;
		}
	}

}
