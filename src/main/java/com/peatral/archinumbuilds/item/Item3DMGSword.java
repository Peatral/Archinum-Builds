package com.peatral.archinumbuilds.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class Item3DMGSword extends ItemSword {
	public Item3DMGSword(ToolMaterial material) {
		super(material);
		this.isFull3D();
	}

	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		return false;
	}

	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
	
}
