package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.client.Models.ModelThorsHelmet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmorThorsHelmet extends ItemArmor{
	public Integer time = 60;

	public ItemArmorThorsHelmet(int type) {
		super(EnumHelper.addArmorMaterial("thorsHelmet", 0, new int[]{3, 0, 0, 0}, 0), 0, type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel (EntityLivingBase entityLiving, ItemStack itemstack, int stackSlot){

		ModelBiped stackModel = new ModelThorsHelmet();

		if(stackModel != null){
			stackModel.bipedHead.showModel = stackSlot == 0;
			stackModel.bipedHeadwear.showModel = false;
			stackModel.bipedBody.showModel = stackSlot == 1 || stackSlot == 2;
			stackModel.bipedRightArm.showModel = stackSlot == 1;
			stackModel.bipedLeftArm.showModel = stackSlot == 1;
			stackModel.bipedRightLeg.showModel = stackSlot == 2 || stackSlot == 3;
			stackModel.bipedLeftLeg.showModel = stackSlot == 2 || stackSlot == 3;

			stackModel.isSneak = entityLiving.isSneaking();
			stackModel.isRiding = entityLiving.isRiding();
			stackModel.isChild = entityLiving.isChild();

			stackModel.heldItemRight = 0;
			stackModel.aimedBow = false;

			EntityPlayer player = (EntityPlayer)entityLiving;

			ItemStack held_item = player.getEquipmentInSlot(0);

			if (held_item != null){
				stackModel.heldItemRight = 1;

				if (player.getItemInUseCount() > 0){

					EnumAction enumaction = held_item.getItemUseAction();

					if (enumaction == EnumAction.bow){
						stackModel.aimedBow = true;
					}else if (enumaction == EnumAction.block){
						stackModel.heldItemRight = 3;
					}
				}
			}
		}

		return stackModel;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return ArchinumBuilds.MODID + ":textures/armor/thorsHelmet.png";
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		ItemStack boots = player.getCurrentArmor(0);
		ItemStack legs = player.getCurrentArmor(1);
		ItemStack chest = player.getCurrentArmor(2);
		ItemStack helmet = player.getCurrentArmor(3);
		
		if(stack.getTagCompound() == null){
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("timer", time);
		}

		if(helmet != null && stack.getTagCompound().getInteger("timer") == time){
			if(helmet.getItem() == ABItems.thorsHelmet){
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, time+1, 3));
				stack.getTagCompound().setInteger("timer", 0);
			}
		}
		if(stack.getTagCompound().getInteger("timer") != time){
			stack.getTagCompound().setInteger("timer", stack.getTagCompound().getInteger("timer") - 1);
		}
	}
}
