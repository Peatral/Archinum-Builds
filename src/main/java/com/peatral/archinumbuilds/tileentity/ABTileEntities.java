package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.ArchinumBuilds;

import cpw.mods.fml.common.registry.GameRegistry;

public class ABTileEntities {
	public static void mainRegistry(){
		registerTileEntity();
	}

	private static void registerTileEntity() {
		GameRegistry.registerTileEntity(TileEntityCatalyst.class, ArchinumBuilds.MODID + ":catalyst");
		GameRegistry.registerTileEntity(TileEntityFracionatingColumn.class, ArchinumBuilds.MODID + ":fractionatingColumn");
	}
}
