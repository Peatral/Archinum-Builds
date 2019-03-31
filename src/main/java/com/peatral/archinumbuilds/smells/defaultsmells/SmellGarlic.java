package com.peatral.archinumbuilds.smells.defaultsmells;

import com.peatral.archinumbuilds.smells.SmellType;
import com.peatral.archinumbuilds.util.Col;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SmellGarlic extends SmellType {
    public SmellGarlic() {
        super("garlic", Col.toIntFromHex("#E5DAB6"));
    }

    @Override
    public void applySmell(World world, Entity entity, int amplifier) {
        if (!world.isRemote) {
            //entity.attackEntityFrom(new DamageSource("garlic"), amplifier);
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.getId(), amplifier * 20, 1, true));
        }
    }
}
