package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.smells.SmellRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

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
