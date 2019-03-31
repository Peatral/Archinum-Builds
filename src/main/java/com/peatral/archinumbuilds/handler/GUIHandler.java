package com.peatral.archinumbuilds.handler;

import com.peatral.archinumbuilds.client.GUI.*;
import com.peatral.archinumbuilds.inventory.container.ContainerFragranceMixer;
import com.peatral.archinumbuilds.inventory.container.ContainerStill;
import com.peatral.archinumbuilds.tileentity.*;

import com.peatral.archinumbuilds.inventory.container.ContainerCatalyst;
import com.peatral.archinumbuilds.inventory.container.ContainerFractionatingColumn;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GUIHandler implements IGuiHandler {
	
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event)
	{
		if (event.type != ElementType.EXPERIENCE) return;
		new GuiHUD(Minecraft.getMinecraft());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getGuiElement(ID, player, world, x, y, z, false);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getGuiElement(ID, player, world, x, y, z, true);
	}

	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z, boolean client){
		TileEntityBasic tileEntity = (TileEntityBasic) world.getTileEntity(x, y, z);
		if(client) {
			switch (ID) {
				case 0:
					//return new GuiCatalyst(player.inventory, (TileEntityCatalyst) tileEntity);
				case 1:
					return new GuiFractionatingColumn(player.inventory, (TileEntityFractionatingColumn) tileEntity);
				case 2:
					return new GuiFragranceMixer(player.inventory, (TileEntityFragranceMixer) tileEntity);
				case 3:
					return new GuiStill(player.inventory, (TileEntityStill) tileEntity);
			}
		} else {
			switch (ID) {
				case 0:
					//return new ContainerCatalyst(player.inventory, (TileEntityCatalyst) tileEntity);
				case 1:
					return new ContainerFractionatingColumn(player.inventory, (TileEntityFractionatingColumn) tileEntity);
				case 2:
					return new ContainerFragranceMixer(player.inventory, tileEntity);
				case 3:
					return new ContainerStill(player.inventory, tileEntity);
			}
		}

		return null;
	}
}