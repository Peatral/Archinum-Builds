package com.peatral.archinumbuilds.world.biome;

import com.peatral.archinumbuilds.util.ResinWoodTypes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeGenKnobi extends BiomeGenBase {

   //rotected WorldGenResinTree worldGeneratorResinTrees;

    public BiomeGenKnobi(int id)
    {
        super(id);

        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.stone;
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        BiomeDecorator biomeDecorator = new BiomeDecoratorKnobi(16);
        //biomeDecorator.knobiPerChunk = 5;
        biomeDecorator.grassPerChunk = 2;
        //biomeDecorator.treesPerChunk = 10;
        //biomeDecorator.reedsPerChunk = 5;
        return getModdedBiomeDecorator(biomeDecorator);
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        {
            //return (WorldGenAbstractTree)(this.worldGeneratorResinTrees);
            return new WorldGenResinTree(false, rand.nextInt(ResinWoodTypes.values().length));
        }
    }
}