package com.peatral.archinumbuilds.handler;

import com.peatral.archinumbuilds.item.ABItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class EventHandlerCommon {
	@SubscribeEvent
	public void onLivingFallEvent(LivingFallEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (((EntityPlayer) event.entity).getCurrentArmor(1) != null){
				if(((EntityPlayer) event.entity).getCurrentArmor(1).getItem() == ABItems.threedmg){
					event.setCanceled(true);
				}
			}
		}
	}
}
