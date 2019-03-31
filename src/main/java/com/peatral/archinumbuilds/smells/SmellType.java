package com.peatral.archinumbuilds.smells;

import com.peatral.archinumbuilds.util.UtilsLang;
import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SmellType {
    public String name;
    public String unlocalizedName;
    public int color;

    public SmellType(String name, int color) {
        this.name = name;
        this.color = color;
        setUnlocalizedName(this.name);
    }

    public SmellType setUnlocalizedName(String name) {
        this.unlocalizedName = "smell." + name;
        return this;
    }

    public String getLocalizedName() {
        return UtilsLang.localize(this.unlocalizedName);
    }

    public void applySmell(World world, Entity entity, int amplifier){
    }
}
