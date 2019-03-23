package com.peatral.archinumbuilds.world.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class BiomeDecoratorKnobi extends BiomeDecorator {
    protected int knobiPerChunk;
    protected WorldGenerator knobiGen;

    public BiomeDecoratorKnobi() {
        super();
        knobiGen = new WorldGenKnobi();
    }

    @Override
    protected void genDecorations(BiomeGenBase biomegenbase) {
        super.genDecorations(biomegenbase);
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        this.generateOres();

        // boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
        // for (int j = 0; doGen && j < this.knobiPerChunk; ++j) {
        for (int j = 0; j < this.knobiPerChunk; ++j) {
            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            int i = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            knobiGen.generate(this.currentWorld, this.randomGenerator, k, i, l);
        }
    }

    private int nextInt(int i) {
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
    }

}
