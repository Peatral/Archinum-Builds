package com.peatral.archinumbuilds.handler;

import com.peatral.archinumbuilds.tileentity.TileEntityCatalyst;
import com.peatral.archinumbuilds.tileentity.TileEntityFracionatingColumn;
import com.peatral.archinumbuilds.client.GUI.GuiCatalyst;
import com.peatral.archinumbuilds.client.GUI.GuiFractionizingColumn;
import com.peatral.archinumbuilds.client.GUI.GuiHUD;

import com.peatral.archinumbuilds.inventory.container.ContainerCatalyst;
import com.peatral.archinumbuilds.inventory.container.ContainerFracionatingColumn;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GUIHandler implements IGuiHandler{
	
	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event)
	{
		if (event.type != ElementType.EXPERIENCE) return;
		new GuiHUD(Minecraft.getMinecraft());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
			case 0:
				TileEntityCatalyst tileEntityFurnace = (TileEntityCatalyst) world.getTileEntity(x, y, z);
				return new ContainerCatalyst(player.inventory, tileEntityFurnace);
			case 1:
				TileEntityFracionatingColumn tileEntityFracionatingColumn = (TileEntityFracionatingColumn) world.getTileEntity(x, y, z);
				return new ContainerFracionatingColumn(player.inventory, tileEntityFracionatingColumn);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
			case 0:
				TileEntityCatalyst tileEntityTestContainer = (TileEntityCatalyst) world.getTileEntity(x, y, z);
				return new GuiCatalyst(player.inventory, tileEntityTestContainer);
			case 1:
				TileEntityFracionatingColumn tileEntityFracionatingColumn = (TileEntityFracionatingColumn) world.getTileEntity(x, y, z);
				return new GuiFractionizingColumn(player.inventory, tileEntityFracionatingColumn);
		}
		return null;
	}
}