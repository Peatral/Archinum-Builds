package com.peatral.archinumbuilds.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ABBiomeRegistry {
    public static BiomeGenBase biomeKnob;

    public static void mainRegistry() {
        initializeBiome();
        registerBiome();
    }

    public static void initializeBiome() {
        biomeKnob = new BiomeGenKnobi(137).setBiomeName("Biome.KNOB").setColor(0xfa9418);
    }

    public static void registerBiome() {
        BiomeDictionary.registerBiomeType(biomeKnob, Type.SAVANNA);
        BiomeManager.addSpawnBiome(biomeKnob);
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(biomeKnob, 10));
        BiomeManager.addVillageBiome(biomeKnob, false);
    }
}
