package com.peatral.archinumbuilds.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemExcalibur extends ItemSword{

	public ItemExcalibur(ToolMaterial material) {
		super(material);

	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase player)
	{
		World world = player.worldObj;
		if(!world.isRemote){
			world.createExplosion(player, hitEntity.getPosition(1.0F).xCoord, hitEntity.getPosition(1.0F).yCoord, hitEntity.getPosition(1.0F).zCoord, 2.0F, true);
		}
		return false;		
	}

}
