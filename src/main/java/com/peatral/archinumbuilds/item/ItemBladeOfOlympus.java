package com.peatral.archinumbuilds.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.peatral.archinumbuilds.entity.EntityEnergyBall;
import com.peatral.archinumbuilds.handler.ConfigurationHandler;
import com.peatral.archinumbuilds.handler.KeyHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.EnergyUnits;
import com.peatral.archinumbuilds.util.KeyBindings;

import mekanism.api.energy.IEnergizedItem;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBladeOfOlympus extends Item implements IEnergizedItem{

	public IIcon itemIcon;
	public double MAX_ELECTRICITY;
	public ItemBladeOfOlympus(ToolMaterial mat,double maxElectricity) {
		super();
		this.setFull3D();
		MAX_ELECTRICITY = maxElectricity;
		setMaxStackSize(1);

	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4){

		String energyItem = EnergyUnits.UnitFinder(this.getEnergy(stack), ConfigurationHandler.energyUnit);
		String energyMax = EnergyUnits.UnitFinder(this.getMaxEnergy(stack), ConfigurationHandler.energyUnit);
		String energyTransfer = EnergyUnits.UnitFinder(this.getMaxTransfer(stack), ConfigurationHandler.energyUnit);

		if(GuiScreen.isShiftKeyDown() ){		

			if(Keyboard.isKeyDown(KeyBindings.keyModeSwitch.getKeyCode())){
				par3List.add(StatCollector.translateToLocal("boo.ab.desc.details"));
			}else {
				par3List.add(StatCollector.translateToLocal("gui.ab.storedenergy") + energyItem);
				par3List.add(StatCollector.translateToLocal("gui.ab.capacity") + energyMax);
				par3List.add(StatCollector.translateToLocal("gui.ab.maxtransfer") + energyTransfer + "/t");
				par3List.add("");
				par3List.add(StatCollector.translateToLocal("boo.ab.cooldowntime") + stack.getTagCompound().getInteger("coolDownTime"));
				par3List.add(StatCollector.translateToLocal("boo.ab.damageamplifier") + stack.getTagCompound().getInteger("damageAmplifier"));
			}
		}else {
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift"));
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift+key.part1") + Keyboard.getKeyName(KeyBindings.keyModeSwitch.getKeyCode()) + StatCollector.translateToLocal("gui.ab.holdshift+key.part2"));
		}
	}

	public void updateNBT(ItemStack stack){
		if(stack.getTagCompound() == null){
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setInteger("coolDown", 0);
			stack.getTagCompound().setInteger("coolDownTime", 60);
			stack.getTagCompound().setInteger("damageAmplifier", 0);
			stack.getTagCompound().setFloat("healthLevel", 0.0F);
			stack.getTagCompound().setFloat("healthLevelBefore", 0.0F);
		}

		switch(Math.floorDiv(Math.round(stack.getTagCompound().getFloat("healthLevel")), 20)){
		case 1: 
			stack.getTagCompound().setInteger("damageAmplifier", 10);
			break;
		case 2:	
			stack.getTagCompound().setInteger("damageAmplifier", 5);
			stack.getTagCompound().setInteger("coolDownTime", 45);
			break;
		case 3:	
			stack.getTagCompound().setInteger("coolDownTime", 30);
			break;
		case 4:	
			stack.getTagCompound().setInteger("coolDownTime", 15);
			break;
		case 5:
			stack.getTagCompound().setInteger("coolDownTime", 0);
			break;			
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase hitEntity, EntityLivingBase entity){
		World world = entity.worldObj;
		EntityPlayer player = (EntityPlayer) entity;
		if (this.getEnergy(stack) >= 5000){
			hitEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 20 + stack.getTagCompound().getInteger("damageAmplifier"));

			if(!player.capabilities.isCreativeMode){
				this.setEnergy(stack, this.getEnergy(stack) - 5000);

				if(stack.getTagCompound().getInteger("damageAmplifier") > 0){
					stack.getTagCompound().setFloat("healthLevel", stack.getTagCompound().getFloat("healthLevel") - (float)stack.getTagCompound().getInteger("damageAmplifier"));
				}
			}
		}
		return true;		
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){	
		if(stack.getTagCompound().getInteger("coolDown") == 0){
			if(!KeyHandler.isModeSwitchDown){
				player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

				if(this.getEnergy(stack) >= 5000 && Keyboard.isKeyDown(42)){
					world.playSoundAtEntity(player, ArchinumBuilds.MODID + ":electricSpark", 0.5F, 1.0F);

					if (!world.isRemote){
						world.spawnEntityInWorld(new EntityEnergyBall(world, player));
					}

					if(!player.capabilities.isCreativeMode){
						this.setEnergy(stack, this.getEnergy(stack) - 5000);
						stack.getTagCompound().setFloat("healthLevel", stack.getTagCompound().getFloat("healthLevel") - 10.0F);

					}

					stack.getTagCompound().setInteger("coolDown", stack.getTagCompound().getInteger("coolDownTime"));
				}
			}
		}

		if(Keyboard.isKeyDown(42) && KeyHandler.isModeSwitchDown && stack.getTagCompound().getFloat("healthLevel") < 100.0F){
			if(!player.capabilities.isCreativeMode){
				stack.getTagCompound().setFloat("healthLevelBefore", stack.getTagCompound().getFloat("healthLevel"));
				stack.getTagCompound().setFloat("healthLevel", Math.min(stack.getTagCompound().getFloat("healthLevelBefore") + player.getHealth() - 1.0F, 100));
				player.attackEntityFrom(DamageSource.magic, Math.min(player.getHealth() - 1.0F, 100 - stack.getTagCompound().getFloat("healthLevelBefore")));
			}

		}
		return stack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool){
		updateNBT(stack);
		if(stack.getTagCompound().getInteger("coolDown") > 0){
			stack.getTagCompound().setInteger("coolDown", stack.getTagCompound().getInteger("coolDown") - 1);
		}
	}

	@Override
	public boolean func_150897_b(Block block)
	{
		return block == Blocks.web;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
	{
		if ((double)p_150894_3_.getBlockHardness(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
		{
			p_150894_1_.damageItem(2, p_150894_7_);
		}

		return true;
	}



	@Override
	public void registerIcons(IIconRegister register){
		itemIcon = register.registerIcon(ArchinumBuilds.MODID + ":BladeOfOlympus");
	}

	@Override
	public IIcon getIconFromDamage(int meta){
		return itemIcon;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack){
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack){
		return 1D-(getEnergy(stack)/getMaxEnergy(stack));
	}

	public ItemStack getUnchargedItem(){
		return new ItemStack(this);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list){
		ItemStack discharged = new ItemStack(this);
		list.add(discharged);
		ItemStack charged = new ItemStack(this);
		setEnergy(charged, ((IEnergizedItem)charged.getItem()).getMaxEnergy(charged));
		list.add(charged);
	}


	@Override
	public double getEnergy(ItemStack itemStack){
		if(itemStack.stackTagCompound == null){
			return 0;
		}

		return itemStack.stackTagCompound.getDouble("electricity");
	}

	@Override
	public void setEnergy(ItemStack itemStack, double amount){
		if(itemStack.stackTagCompound == null){
			itemStack.setTagCompound(new NBTTagCompound());
		}

		double electricityStored = Math.max(Math.min(amount, getMaxEnergy(itemStack)), 0);
		itemStack.stackTagCompound.setDouble("electricity", electricityStored);
	}

	@Override
	public double getMaxEnergy(ItemStack itemStack){
		return MAX_ELECTRICITY;
	}

	@Override
	public double getMaxTransfer(ItemStack itemStack){
		return getMaxEnergy(itemStack)*0.05;//0.005
	}

	@Override
	public boolean canReceive(ItemStack itemStack){
		return getMaxEnergy(itemStack)-getEnergy(itemStack) > 0;
	}

	@Override
	public boolean canSend(ItemStack itemStack){
		return false;
	}

}
