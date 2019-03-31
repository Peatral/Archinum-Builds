package com.peatral.archinumbuilds.smells.defaultsmells;

import com.peatral.archinumbuilds.smells.SmellType;
import com.peatral.archinumbuilds.util.Col;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SmellSage extends SmellType {
    public SmellSage() {
        super("sage", Col.toIntFromHex("#91FF53"));
    }

    @Override
    public void applySmell(World world, Entity entity, int amplifier) {
        if(!world.isRemote) {
            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.regeneration.getId(),amplifier*30, amplifier/2, true));
        }
    }
}
