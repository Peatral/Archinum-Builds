package com.peatral.archinumbuilds.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenKnobi extends BiomeGenBase {

    public BiomeGenKnobi(int id) {
        super(id);

        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.stone;

        BiomeDecoratorKnobi biomeDecoratorKnobi = new BiomeDecoratorKnobi();
        biomeDecoratorKnobi.knobiPerChunk = 5;
        biomeDecoratorKnobi.grassPerChunk = 2;
        biomeDecoratorKnobi.treesPerChunk = 10;
        biomeDecoratorKnobi.reedsPerChunk = 5;

        this.theBiomeDecorator = biomeDecoratorKnobi;
    }


}