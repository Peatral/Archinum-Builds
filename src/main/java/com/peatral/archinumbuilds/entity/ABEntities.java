package com.peatral.archinumbuilds.entity;

import com.peatral.archinumbuilds.ArchinumBuilds;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ABEntities {
	public static void mainRegistry(){
		EntityRegistry.registerModEntity(EntityEnergyBall.class, "EMP", 0, ArchinumBuilds.instance, 64, 10, true);
	}
}
