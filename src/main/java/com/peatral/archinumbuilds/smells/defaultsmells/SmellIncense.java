package com.peatral.archinumbuilds.smells.defaultsmells;

import com.peatral.archinumbuilds.smells.SmellType;
import com.peatral.archinumbuilds.util.Col;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SmellIncense extends SmellType {
    public SmellIncense() {
        super("incense", Col.toIntFromHex("F8FF00"));
    }

    @Override
    public void applySmell(World world, Entity entity, int amplifier) {
        if(!world.isRemote) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.confusion.getId(),amplifier*30, 1, true));
        }
    }
}
