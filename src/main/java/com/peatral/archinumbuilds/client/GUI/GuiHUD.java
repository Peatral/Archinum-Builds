package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.ArchinumBuilds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiHUD extends Gui{
	public GuiHUD(Minecraft mc){
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();

		int guiX = width-34;
		int guiY = height-34;

		EntityPlayer player = mc.thePlayer;

		mc.renderEngine.bindTexture(new ResourceLocation(ArchinumBuilds.MODID, "textures/gui/gui.png"));
		ItemStack stack = player.getCurrentEquippedItem();

		if (stack != null && player.getCurrentEquippedItem().getItem() == ABItems.bladeOlympus) {
			if(stack.getTagCompound() == null){
				stack.setTagCompound(new NBTTagCompound());
			}

			Integer perCentHealthLevel = Math.round(28.0F/100.0F*stack.getTagCompound().getFloat("healthLevel"));

			Integer perCentCoolDown = Math.round((float)28.0F/stack.getTagCompound().getInteger("coolDownTime")*stack.getTagCompound().getInteger("coolDown"));

			drawTexturedModalRect(guiX, guiY, 0, 0, 32, 32);//Background

			drawTexturedModalRect(guiX+18, guiY+2+perCentCoolDown, 51, 0+perCentCoolDown, 12, 28-perCentCoolDown);//CoolDown

			drawTexturedModalRect(guiX+2, guiY+2+28-perCentHealthLevel, 32, 0+28-perCentHealthLevel, 19, perCentHealthLevel);//HealthLevel

			drawString(mc.fontRenderer, Math.floor(Math.round(stack.getTagCompound().getFloat("healthLevel"))/ 20) + "", guiX+6, guiY+14, Integer.parseInt("55FF55", 16));
		
		}else if (stack != null && player.getCurrentEquippedItem().getItem() == ABItems.threedmgSword && player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem() == ABItems.threedmg) {
			ItemStack threedmgEquipped = player.getCurrentArmor(1);
			if(threedmgEquipped.getTagCompound() == null){
				threedmgEquipped.setTagCompound(new NBTTagCompound());
			}

			Integer perCentGas = Math.round(28/500*threedmgEquipped.getTagCompound().getInteger("Gas"));

			drawTexturedModalRect(guiX, guiY, 0, 32, 16, 32);//Background

			drawTexturedModalRect(guiX+2, guiY+2+28-perCentGas, 16, 32+28-perCentGas, 12, perCentGas);//Gas

			drawString(mc.fontRenderer, threedmgEquipped.getTagCompound().getInteger("Gas") + "", guiX+6, guiY+14, Integer.parseInt("55FF55", 16));
		}

	}
}
