package com.peatral.archinumbuilds.world.biome;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

@Deprecated
public class WorldTypeKnobi extends WorldType {

    public WorldTypeKnobi(int par1, String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer) {
        GenLayer ret = new GenLayerBiome(200L, parentLayer, this);
        ret = GenLayerZoom.magnify(1000L, ret, 2);
        ret = new GenLayerBiomeEdge(1000L, ret);
        return ret;
    }

}
