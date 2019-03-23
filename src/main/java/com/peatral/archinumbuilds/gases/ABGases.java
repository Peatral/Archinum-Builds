package com.peatral.archinumbuilds.gases;

import java.util.ArrayList;
import java.util.List;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.OreGas;

public class ABGases {
	public static void mainRegistry(){
		initializeGases();
		registerGases();
	}
	
	private static List<OreGas> gasList = new ArrayList<OreGas>();
	
	public static Gas Archinum;
	public static Gas Korosium;
	public static OreGas cleanArchinum;
	public static OreGas cleanKorosium;

	
	public static void initializeGases(){
		cleanArchinum = new OreGas("cleanArchinum", "oreArchinum");
		cleanKorosium = new OreGas("cleanKorosium", "oreKorosium");
		Archinum = new OreGas("archinum", "oreArchinum").setCleanGas(cleanArchinum).setUnlocalizedName("archinum").registerFluid().setVisible(true);
		Korosium = new OreGas("korosium", "oreKorosium").setCleanGas(cleanKorosium).setUnlocalizedName("korosium").registerFluid().setVisible(true);
		
	}
	
	public static void registerGases(){
		GasRegistry.register(Archinum);
		GasRegistry.register(Korosium);
		GasRegistry.register(cleanArchinum);
		GasRegistry.register(cleanKorosium);
	}
}
