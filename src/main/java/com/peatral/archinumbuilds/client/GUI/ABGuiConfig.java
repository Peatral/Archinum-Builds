package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.handler.ConfigurationHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ABGuiConfig extends GuiConfig{

	
	public ABGuiConfig(GuiScreen guiScreen){
		super(guiScreen, new ConfigElement(ConfigurationHandler.getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), ArchinumBuilds.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.getConfiguration().toString()));
	}
}
