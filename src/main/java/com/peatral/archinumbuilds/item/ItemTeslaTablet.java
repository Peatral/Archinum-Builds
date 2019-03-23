package com.peatral.archinumbuilds.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.peatral.archinumbuilds.handler.ConfigurationHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.EnergyUnits;
import com.peatral.archinumbuilds.util.KeyBindings;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mekanism.api.MekanismConfig.general;
import mekanism.api.energy.EnergizedItemManager;
import mekanism.api.energy.IEnergizedItem;
import mekanism.common.item.ItemMekanism;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemTeslaTablet extends ItemMekanism implements IEnergizedItem {
	public IIcon itemIcon;
	public double MAX_ELECTRICITY;

	public ItemTeslaTablet(double maxElectricity) {
		super();
		MAX_ELECTRICITY = maxElectricity;
		setMaxStackSize(1);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		String energyItem = EnergyUnits.UnitFinder(this.getEnergy(par1ItemStack), ConfigurationHandler.energyUnit);
		String energyMax = EnergyUnits.UnitFinder(this.getMaxEnergy(par1ItemStack), ConfigurationHandler.energyUnit);
		String energyTransfer = EnergyUnits.UnitFinder(this.getMaxTransfer(par1ItemStack),
				ConfigurationHandler.energyUnit);

		if (GuiScreen.isShiftKeyDown()) {
			if (Keyboard.isKeyDown(KeyBindings.keyModeSwitch.getKeyCode())) {
				par3List.add(StatCollector.translateToLocal("tt.ab.desc.details.line1"));
				par3List.add(StatCollector.translateToLocal("tt.ab.desc.details.line2")
						+ Modes.values()[par1ItemStack.getTagCompound().getInteger("mode")].getName());
				par3List.add(Modes.values()[par1ItemStack.getTagCompound().getInteger("mode")].getDescription());
			} else {
				par3List.add(StatCollector.translateToLocal("gui.ab.storedenergy") + energyItem);
				par3List.add(StatCollector.translateToLocal("gui.ab.capacity") + energyMax);
				par3List.add(StatCollector.translateToLocal("gui.ab.maxtransfer") + energyTransfer + "/t");
			}
		} else {
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift"));
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift+key.part1")
					+ Keyboard.getKeyName(KeyBindings.keyModeSwitch.getKeyCode())
					+ StatCollector.translateToLocal("gui.ab.holdshift+key.part2"));
		}
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && !Keyboard.isKeyDown(42) && stack.getTagCompound().getInteger("mode") >= 2) {
			if (this.getEnergy(stack) >= 500000) {

				world.playSoundAtEntity(player, ArchinumBuilds.MODID + ":electricSpark", 0.5F, 1.0F);

				float f = 1.0f;

				float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
				float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;

				double d = (double) f;

				double d1 = player.prevPosX + (player.posX - player.prevPosX) * d;
				double d2 = (player.prevPosY + (player.posY - player.prevPosY) * d + 1.6200000000000001d)
						- (double) player.yOffset;
				double d3 = player.prevPosZ + (player.posZ - player.prevPosZ) * d;

				Vec3 vec1 = Vec3.createVectorHelper(d1, d2, d3);

				float f11 = MathHelper.cos(-f2 * 0.01745329f - 3.141593f);
				float f12 = MathHelper.sin(-f2 * 0.01745329f - 3.141593f);
				float f13 = -MathHelper.cos(-f1 * 0.01745329f);
				float f14 = MathHelper.sin(-f1 * 0.01745329f);

				float f15 = f12 * f13;
				float f16 = f14;
				float f17 = f11 * f13;

				double d11 = 5000d;

				Vec3 vec2 = vec1.addVector((double) f15 * d11, (double) f16 * d11, (double) f17 * d11);

				MovingObjectPosition position = world.func_147447_a(vec1, vec2, false, true, true);

				if (position != null && position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int x = position.blockX;
					int y = position.blockY;
					int z = position.blockZ;

					world.addWeatherEffect((new EntityLightningBolt(world, x, y, z)));

					if (stack.getTagCompound().getInteger("mode") == 3) {
						world.createExplosion(player, x, y, z, ConfigurationHandler.teslaExplosionsStrength, true);
					}

					if (!player.capabilities.isCreativeMode) {
						this.setEnergy(stack, this.getEnergy(stack) - 500000);
					}

				}
			}
		} else if (Keyboard.isKeyDown(42)) {
			if (stack.getTagCompound() == null) {
				stack.setTagCompound(new NBTTagCompound());
			}

			stack.getTagCompound().setInteger("mode", (stack.getTagCompound().getInteger("mode")+1)%4);

			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(
						"§2[" + ArchinumBuilds.MODID + "] " + StatCollector.translateToLocal("ttmodes.ab.toggle.message")
								+ Modes.values()[stack.getTagCompound().getInteger("mode")].getName()));
			}
		}
		return stack;

	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool) {
		if (getEnergy(stack) > 0 && stack.getTagCompound().getInteger("mode") == 1) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;

				double prevEnergy = getEnergy(stack);

				for (ItemStack itemstack : player.inventory.armorInventory) {
					chargeItemStack(itemstack, stack);
					/*
					 * if(prevEnergy != getEnergy(itemstack)){ break; }
					 */
				}

				for (ItemStack itemstack : player.inventory.mainInventory) {
					chargeItemStack(itemstack, stack);
					/*
					 * if(prevEnergy != getEnergy(itemstack)){ break; }
					 */
				}
			}
		}
	}

	public void chargeItemStack(ItemStack itemstack, ItemStack stack) {
		if (itemstack != null) {

			if (itemstack.getItem() instanceof IEnergizedItem) {
				this.setEnergy(stack, this.getEnergy(stack) - EnergizedItemManager.charge(itemstack, getEnergy(stack)));
				// EnergizedItemManager.discharge(new ItemStack(this),
				// EnergizedItemManager.charge(stack, getEnergy(new ItemStack(this))));
			}
			/*
			 * else if(MekanismUtils.useIC2() && stack.getItem() instanceof IElectricItem){
			 * double sent = ElectricItem.manager.charge(stack,
			 * (int)(getEnergy(stack)*general.TO_IC2), 4, true, false)*general.FROM_IC2;
			 * setEnergy(stack, getEnergy(stack) - sent); }
			 */
			else if (MekanismUtils.useRF() && stack.getItem() instanceof IEnergyContainerItem) {
				IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();

				int itemEnergy = (int) Math.round(Math.min(Math.sqrt(item.getMaxEnergyStored(stack)),
						item.getMaxEnergyStored(stack) - item.getEnergyStored(stack)));
				int toTransfer = (int) Math.round(Math.min(itemEnergy, (getEnergy(stack) * general.TO_TE)));

				setEnergy(stack, getEnergy(stack) - (item.receiveEnergy(stack, toTransfer, false) * general.FROM_TE));
			}
		}
	}

	public enum Modes {
		BATTERY("battery"),
		LOADER("loader"),
		LIGHTNING("lightning"),
		LIGHTNING2("lightning2");

		private String id;

		private Modes(String id) {
			this.id = id;
		}

		public static Modes getFromName(String s1) {
			for (Modes r : values()) {
				if (r.id.toLowerCase().equals(s1.toLowerCase())) {
					return r;
				}
			}

			return null;
		}

		public String getName() {
			return StatCollector.translateToLocal("ttmodes.ab.name." + this.id);
		}

		public String getDescription() {
			return StatCollector.translateToLocal("ttmodes.ab.desc." + this.id);
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArchinumBuilds.MODID + ":TeslaTablet");
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return itemIcon;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1D - (getEnergy(stack) / getMaxEnergy(stack));
	}

	public ItemStack getUnchargedItem() {
		return new ItemStack(this);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		ItemStack discharged = new ItemStack(this);
		list.add(discharged);
		ItemStack charged = new ItemStack(this);
		setEnergy(charged, ((IEnergizedItem) charged.getItem()).getMaxEnergy(charged));
		list.add(charged);
	}

	@Override
	public double getEnergy(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			return 0;
		}

		return itemStack.stackTagCompound.getDouble("electricity");
	}

	@Override
	public void setEnergy(ItemStack itemStack, double amount) {
		if (itemStack.stackTagCompound == null) {
			itemStack.setTagCompound(new NBTTagCompound());
		}

		double electricityStored = Math.max(Math.min(amount, getMaxEnergy(itemStack)), 0);
		itemStack.stackTagCompound.setDouble("electricity", electricityStored);
	}

	@Override
	public double getMaxEnergy(ItemStack itemStack) {
		return MAX_ELECTRICITY;
	}

	@Override
	public double getMaxTransfer(ItemStack itemStack) {
		return getMaxEnergy(itemStack) * 0.05;// 0.005
	}

	@Override
	public boolean canReceive(ItemStack itemStack) {
		if (itemStack.getTagCompound().getInteger("mode") == 0 || itemStack.getTagCompound().getInteger("mode") == 1) {
			return getMaxEnergy(itemStack) - getEnergy(itemStack) > 0;
		} else {
			return false;
		}

	}

	@Override
	public boolean canSend(ItemStack itemStack) {
		if (itemStack.getTagCompound().getInteger("mode") == 0 || itemStack.getTagCompound().getInteger("mode") == 1) {
			return getEnergy(itemStack) > 0;
		} else {
			return false;
		}

	}

}
