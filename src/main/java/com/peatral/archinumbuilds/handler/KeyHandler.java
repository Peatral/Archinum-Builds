package com.peatral.archinumbuilds.handler;

import com.peatral.archinumbuilds.item.ItemArmor3DMG;
import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.util.KeyBindings;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class KeyHandler {
	public static Boolean isModeSwitchDown = false;
	public static Boolean is3DMGActivateDown = false;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onKeyPressed(KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		World world = Minecraft.getMinecraft().theWorld;
		ItemStack threedmgEquipped = player.getCurrentArmor(1);

		/*
		if (KeyBindings.keyModeSwitch.isPressed()) {

			if (threedmgEquipped != null && threedmgEquipped.getItem() == ModItems.threedmg
					&& threedmgEquipped.getTagCompound() != null) {

				if (threedmgEquipped.getTagCompound().getBoolean("Sword") == false
						&& player.getCurrentEquippedItem() == null) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(
							ModItems.threedmgSword, 1, threedmgEquipped.getTagCompound().getInteger("SwordDamage")));
					threedmgEquipped.getTagCompound().setBoolean("Sword", true);

				} else if (threedmgEquipped.getTagCompound().getBoolean("Sword") == true
						&& player.getCurrentEquippedItem() != null
						&& player.getCurrentEquippedItem().getItem() == ModItems.threedmgSword) {
					threedmgEquipped.getTagCompound().setInteger("SwordDamage",
							player.getCurrentEquippedItem().getItemDamage());
					player.inventory.decrStackSize(player.inventory.currentItem, 1);
					threedmgEquipped.getTagCompound().setBoolean("Sword", false);

				}

			}

			isModeSwitchDown = true;
		} else {
			isModeSwitchDown = false;
		}
		*/

		if (KeyBindings.key3DMGLaunch.isPressed()) {
			if (threedmgEquipped != null && threedmgEquipped.getItem() == ABItems.threedmg) {
				if (player.getCurrentEquippedItem() != null
						&& player.getCurrentEquippedItem().getItem() == ABItems.threedmgSword) {
					if (!((ItemArmor3DMG) threedmgEquipped.getItem()).isLaunched()) {
						((ItemArmor3DMG) threedmgEquipped.getItem()).spawnHooks(world, player);
					} else {
						((ItemArmor3DMG) threedmgEquipped.getItem()).detachHooks();
					}
				}
			}
		}
		if (KeyBindings.key3DMGActivate.isPressed()) {
			is3DMGActivateDown = true;
		} else {
			is3DMGActivateDown = false;
		}
	}

}
