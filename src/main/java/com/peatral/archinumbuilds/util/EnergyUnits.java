package com.peatral.archinumbuilds.util;

public enum EnergyUnits {
	JOULE("J", "Joule", 1.0), 
	ENRGYUNIT("EU", "Energy Unit", 0.1), 
	REDSTONEFLUX("RF", "Redstone Flux", 0.4),
	MINECRAFTJOULE("MJ", "Minecraft Joule", 0.01);

	private String name;
	private String fullname;
	private Double factor;

	private EnergyUnits(String a, String b, Double c) {
		name = a;
		fullname = b;
		factor = c;
	}

	public static EnergyUnits getFromName(String s) {
		for (EnergyUnits r : values()) {
			if (r.name.toLowerCase().equals(s.toLowerCase())) {
				return r;
			}
		}

		return null;
	}

	public String getName() {
		return name;
	}

	public static EnergyUnits getFromFullName(String s) {
		for (EnergyUnits r : values()) {
			if (r.fullname.toLowerCase().trim().equals(s.toLowerCase().trim())) {
				return r;
			}
		}

		return null;
	}

	public String getFullName() {
		return fullname;
	}

	public Double getFactor() {
		return factor;
	}

	/**
	 * Gets the Amount of energy with the Unit like 1.34 MRF or 5.01 kJ with the
	 * metric prefix up to Giga - works with J, EU, RF and MJ.
	 * 
	 * @param amount - amount of energy You want to calculated
	 * @param unit   - Unit You want to use [0=J, 1=EU, 2=RF, 3=MJ]
	 * @return string You want to get
	 */
	public static String UnitFinder(double amount, int unit) {
		String energyUnitString = EnergyUnits.values()[unit].getName();
		Double energyUnitFactor = EnergyUnits.values()[unit].getFactor();

		if (amount * energyUnitFactor < 1000) {
			return (Math.round(100 * ((amount * energyUnitFactor) / 1.0)) / 100.0) + " " + energyUnitString;
		} else if (amount * energyUnitFactor < 1000000) {
			return (Math.round(100 * ((amount * energyUnitFactor) / 1000.0)) / 100.0) + " k" + energyUnitString;
		} else if (amount * energyUnitFactor < 1000000000) {
			return (Math.round(100 * ((amount * energyUnitFactor) / 1000000.0)) / 100.0) + " M" + energyUnitString;
		} else {
			return (Math.round(100 * ((amount * energyUnitFactor) / 1000000000.0)) / 100.0) + " G" + energyUnitString;
		}
	}
}