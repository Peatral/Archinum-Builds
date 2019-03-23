package com.peatral.archinumbuilds.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class ABCreativeTabs {
	public static void mainRegistry(){
		initializeTabs();
		//registerTabs();
	}
	
	public static CreativeTabs tabArchinumBuilds;
	
	public static void initializeTabs(){
		tabArchinumBuilds = new ArchinumBuildsTab("tabArchinumBuilds");
	}
}
