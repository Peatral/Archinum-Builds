package com.peatral.archinumbuilds.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Entity3DMGHook extends EntityArrow 
{
	private float angle;
	private EntityPlayer Owner;

    /** 1 if the player can pick up the arrow */
    public int canBePickedUp;
	
	public Entity3DMGHook(World world, EntityLivingBase player, float f, float angle)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = player;
        this.angle = angle;

        if (player instanceof EntityPlayer)
        {
            this.canBePickedUp = 0;
        }

        this.setSize(0.5F, 0.5F);
        
        this.setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw + this.angle, player.rotationPitch);
                 
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, f * 1.5F, 1.0F);
    }
	
	public EntityPlayer getOwner(){
		return this.Owner;
	}
	
	public void setOwner(EntityPlayer player){
		this.Owner = player;
	}

}
