package com.peatral.archinumbuilds.core;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.handler.GUIHandler;

import cpw.mods.fml.common.network.NetworkRegistry;

public class Proxy {
	public  void registerRenderers(){
		
	}
	
	public void registerNetworkStuff(){
		NetworkRegistry.INSTANCE.registerGuiHandler(ArchinumBuilds.instance, new GUIHandler());
	}
	
	public void registerTileEntity(){
	}

}
