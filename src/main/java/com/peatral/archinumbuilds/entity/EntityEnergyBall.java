package com.peatral.archinumbuilds.entity;

import com.peatral.archinumbuilds.handler.ConfigurationHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnergyBall extends EntityThrowable
{
	public EntityEnergyBall(World world)
	{
		super(world);
	}

	public EntityEnergyBall(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	private void explode()
	{
		worldObj.createExplosion(this, this.posX, this.posY, this.posZ, ConfigurationHandler.energyExplosionsStrength, true);
		setDead();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (ticksExisted > 20)
		{
			explode();
		}

		for (int i = 0; i < 10; i++)
		{
			double x = (double)(rand.nextInt(10) - 5) / 8.0D;
			double y = (double)(rand.nextInt(10) - 5) / 8.0D;
			double z = (double)(rand.nextInt(10) - 5) / 8.0D;
			worldObj.spawnParticle("portal", posX, posY, posZ, x, y, z);
		}
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.005F;
	}

	@Override
	public void onImpact(MovingObjectPosition movingObjectPosition)
	{
		explode();
	}
}
