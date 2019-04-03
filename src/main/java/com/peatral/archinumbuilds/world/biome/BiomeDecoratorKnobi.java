package com.peatral.archinumbuilds.world.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.*;

public class BiomeDecoratorKnobi extends BiomeDecorator {
    //protected int knobiPerChunk;
    protected int resintreesPerChunk;
    //public WorldGenerator knobiGen;

    public BiomeDecoratorKnobi(int anz) {
        super();
        this.resintreesPerChunk = anz;
        //this.knobiGen = new WorldGenKnobi(ABBlocks.Knobi.sand, 7);
    }

    @Override
    public void decorateChunk(World worldIn, Random rand, BiomeGenBase biomeIn, int chunkX, int chunkY)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = worldIn;
            this.randomGenerator = rand;
            this.chunk_X = chunkX;
            this.chunk_Z = chunkY;
            this.genDecorations(biomeIn);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biomeIn)
    {
        //super.genDecorations(biomeIn);
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        this.generateOres();
        int i;
        int j;
        int k;

        /*doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, ABBlocks.Knobi);
        for (int i = 0; doGen && i < this.knobiPerChunk; ++i) {

            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.knobiGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }*/

        i = this.resintreesPerChunk;

        if (this.randomGenerator.nextInt(10) == 0)
        {
            ++i;
        }

        int l;
        int i1;

        boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
        for (j = 0; j < i; ++j)
        {
            k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            i1 = this.currentWorld.getHeightValue(k, l);
            WorldGenAbstractTree treeGen = biomeIn.func_150567_a(this.randomGenerator);
            //treeGen.setScale(1.0D, 1.0D, 1.0D);
            if (treeGen.generate(this.currentWorld, this.randomGenerator, k, i1, l))
            {
                treeGen.func_150524_b(this.currentWorld, this.randomGenerator, k, i1, l);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }

    private int nextInt(int i) {
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
    }
}
