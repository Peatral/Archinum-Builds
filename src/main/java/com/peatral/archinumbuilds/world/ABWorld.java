package com.peatral.archinumbuilds.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class ABWorld {
    public static void mainRegistry() {
        initializeWorldGen();
    }

    public static void initializeWorldGen() {
        registerWorldGen(new ABOre(), 1);
    }

    public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability) {
        GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
    }
}
