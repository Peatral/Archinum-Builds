package com.peatral.archinumbuilds.handler;

import java.io.File;

import com.peatral.archinumbuilds.ArchinumBuilds;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	
	public static String CATEGORY_UPDATECHECK = "UpdateCheck";
	public static String CATEGORY_STUFF = "Stuff";
	
	public static Boolean updateCheck; 
	public static Integer energyUnit;
	
	public static Float teslaExplosionsStrength;
	public static Float energyExplosionsStrength;
	
	public static Integer bladeOlympusCoolDown;
	
	public static Integer archinumMinY;
	public static Integer archinumMaxY;
	public static Integer archinumMinVein;
	public static Integer archinumMaxVein;
	public static Integer archinumSpawnChance;

	public static Double playerMass;
	public static Double threedmgStiffness;
	
	public static void mainRegistry(String configDir){
		if(configuration == null){
			File path = new File(configDir + "/" + ArchinumBuilds.MODID + ".cfg");
			
			configuration = new Configuration(path);
			loadConfiguration();
		}
	}
	
	public static void loadConfiguration(){
		updateCheck = configuration.getBoolean("Check for Updates", Configuration.CATEGORY_GENERAL, true, "Allow to check for Updates");
		energyUnit = configuration.getInt("Energy Unit", Configuration.CATEGORY_GENERAL, 0, 0, 3, "Set Energy-Unit used by ArchinumBuilds. 0=J, 1=EU, 2=RF, 3=MJ");
		
		teslaExplosionsStrength = configuration.getFloat("Tesla Explosions Strength", Configuration.CATEGORY_GENERAL, 5.0F, 1.0F, 10.0F, "The strength of the TeslaTablet-Explosions.");
		energyExplosionsStrength = configuration.getFloat("Energy Explosions Strength", Configuration.CATEGORY_GENERAL, 0.75F, 0.1F, 10.0F, "The strength of the EnergyBall-Explosions.");
		
		bladeOlympusCoolDown = configuration.getInt("Blade of Olympus CoolDown", Configuration.CATEGORY_GENERAL, 60, 0, 1000000, "The length of the CoolDown of the Blade of Olympus EnergyBall.");
		
		archinumMinY = configuration.getInt("Archinum Min Y", Configuration.CATEGORY_GENERAL, 5, 0, 255, "The minimum height Archinum Ore can be found on.");
		archinumMaxY = configuration.getInt("Archinum Max Y", Configuration.CATEGORY_GENERAL, 50, 0, 255, "The maximum height Archinum Ore can be found on.");
		archinumMinVein = configuration.getInt("Archinum Min Vein", Configuration.CATEGORY_GENERAL, 2, 1, 25, "The minimum vein size Archinum Ore can be found in.");
		archinumMaxVein = configuration.getInt("Archinum Max Vein", Configuration.CATEGORY_GENERAL, 7, 1, 25, "The maximum vein size Archinum Ore can be found in.");
		archinumSpawnChance = configuration.getInt("Archinum Spawn Chance", Configuration.CATEGORY_GENERAL, 10, 0, 100, "The chance Archinum Ore can be spawned.");


		playerMass = (double) configuration.getFloat("Player Mass", Configuration.CATEGORY_GENERAL, 7.5f, 0.1f, 100.0f, "");
		threedmgStiffness = (double) configuration.getFloat("3DMG Stiffness", Configuration.CATEGORY_GENERAL, 1.1f, 0.1f, 10.0f, "");

		if(configuration.hasChanged()){
			configuration.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(ArchinumBuilds.MODID)){
			loadConfiguration();
		}
	}
	
	public static Configuration getConfiguration(){
		return configuration;
	}
}
