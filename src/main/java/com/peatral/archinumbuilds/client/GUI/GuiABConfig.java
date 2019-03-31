package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.handler.ConfigHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;

import com.peatral.archinumbuilds.util.UtilsLang;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GuiABConfig extends GuiConfig{

	
	public GuiABConfig(GuiScreen guiScreen){
		super(guiScreen, getConfigElements(), ArchinumBuilds.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.getConfiguration().toString()));
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList();
		//new ConfigElement(ConfigurationHandler.getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()
		list.add(new DummyConfigElement.DummyCategoryElement(UtilsLang.localize("ab.configgui.ctgy.general"), "ab.configgui.ctgy.general", GuiABConfig.GeneralEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement(UtilsLang.localize("ab.configgui.ctgy.world"), "ab.configgui.ctgy.world", GuiABConfig.WorldEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement(UtilsLang.localize("ab.configgui.ctgy.fragrance"), "ab.configgui.ctgy.fragrance", GuiABConfig.FragranceEntry.class));
		list.add(new DummyConfigElement.DummyCategoryElement(UtilsLang.localize("ab.configgui.ctgy.combat"), "ab.configgui.ctgy.combat", GuiABConfig.CombatEntry.class));
		return list;
	}

	public static class WorldEntry extends GuiConfigEntries.CategoryEntry {
		public WorldEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, (new ConfigElement(ConfigHandler.configuration.getCategory(ConfigHandler.CATEGORY_WORLD))).getChildElements(), this.owningScreen.modID, ConfigHandler.CATEGORY_WORLD, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
		}
	}

	public static class GeneralEntry extends GuiConfigEntries.CategoryEntry {
		public GeneralEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
			super(owningScreen, owningEntryList, prop);
		}

		protected GuiScreen buildChildScreen() {
			return new GuiConfig(this.owningScreen, (new ConfigElement(ConfigHandler.configuration.getCategory(ConfigHandler.CATEGORY_GENERAL))).getChildElements(), this.owningScreen.modID, ConfigHandler.CATEGORY_GENERAL, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
		}
	}

    public static class CombatEntry extends GuiConfigEntries.CategoryEntry {
        public CombatEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
            super(owningScreen, owningEntryList, prop);
        }

        protected GuiScreen buildChildScreen() {
            return new GuiConfig(this.owningScreen, (new ConfigElement(ConfigHandler.configuration.getCategory(ConfigHandler.CATEGORY_COMBAT))).getChildElements(), this.owningScreen.modID, ConfigHandler.CATEGORY_COMBAT, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
        }
    }

    public static class FragranceEntry extends GuiConfigEntries.CategoryEntry {
        public FragranceEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
            super(owningScreen, owningEntryList, prop);
        }

        protected GuiScreen buildChildScreen() {
            return new GuiConfig(this.owningScreen, (new ConfigElement(ConfigHandler.configuration.getCategory(ConfigHandler.CATEGORY_FRAGRANCE))).getChildElements(), this.owningScreen.modID, ConfigHandler.CATEGORY_FRAGRANCE, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.configuration.toString()));
        }
    }
}
