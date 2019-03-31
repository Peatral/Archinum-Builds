package com.peatral.archinumbuilds.smells.defaultsmells;

import com.peatral.archinumbuilds.smells.SmellType;
import com.peatral.archinumbuilds.util.Col;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SmellSpice extends SmellType {
    public SmellSpice() {
        super("spice", Col.toIntFromHex("#F09623"));
    }

    @Override
    public void applySmell(World world, Entity entity, int amplifier) {
        if(!world.isRemote) {
            entity.setFire(amplifier * 2);
        }
    }
}
