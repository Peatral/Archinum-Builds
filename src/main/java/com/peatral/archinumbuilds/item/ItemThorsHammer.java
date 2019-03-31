package com.peatral.archinumbuilds.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.peatral.archinumbuilds.handler.ConfigHandler;
import com.peatral.archinumbuilds.handler.KeyHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.EnergyUnits;
import com.peatral.archinumbuilds.util.KeyBindings;

import mekanism.api.energy.IEnergizedItem;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemThorsHammer extends Item implements IEnergizedItem {

	public IIcon itemIcon;
	public double MAX_ELECTRICITY;

	public ItemThorsHammer(double maxElectricity) {
		super();
		this.setFull3D();
		MAX_ELECTRICITY = maxElectricity;
		setMaxStackSize(1);
	}

	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		String energyItem = EnergyUnits.UnitFinder(this.getEnergy(stack), ConfigHandler.energyUnit);
		String energyMax = EnergyUnits.UnitFinder(this.getMaxEnergy(stack), ConfigHandler.energyUnit);
		String energyTransfer = EnergyUnits.UnitFinder(this.getMaxTransfer(stack), ConfigHandler.energyUnit);

		if (GuiScreen.isShiftKeyDown()) {

			if (Keyboard.isKeyDown(KeyBindings.keyModeSwitch.getKeyCode())) {
				par3List.add(StatCollector.translateToLocal("th.ab.desc.details"));
			} else {
				par3List.add(StatCollector.translateToLocal("gui.ab.storedenergy") + energyItem);
				par3List.add(StatCollector.translateToLocal("gui.ab.capacity") + energyMax);
				par3List.add(StatCollector.translateToLocal("gui.ab.maxtransfer") + energyTransfer + "/t");
				par3List.add(StatCollector.translateToLocal("th.ab.desc.bound")
						+ ((stack.getTagCompound().getLong("boundPlayer") == 0)
								? StatCollector.translateToLocal("th.ab.nobody")
								: stack.getTagCompound().getString("boundPlayerName")));
			}
		} else {
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift"));
			par3List.add(StatCollector.translateToLocal("gui.ab.holdshift+key.part1")
					+ Keyboard.getKeyName(KeyBindings.keyModeSwitch.getKeyCode())
					+ StatCollector.translateToLocal("gui.ab.holdshift+key.part2"));
		}
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase hitEntity, EntityLivingBase player) {
		hitEntity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 20);
		return false;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		long uuid = player.getUniqueID().getMostSignificantBits();
		if (!world.isRemote && !Keyboard.isKeyDown(42) && stack.getTagCompound().getLong("boundPlayer") == uuid) {
			if (this.getEnergy(stack) >= 500000) {
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

					if (!player.capabilities.isCreativeMode) {
						this.setEnergy(stack, this.getEnergy(stack) - 500000);
					}

				}

			}
		} else if (Keyboard.isKeyDown(42)) {

			// BOUND + UNBOUND
			if (KeyHandler.isModeSwitchDown == true) {
				if (stack.getTagCompound().getLong("boundPlayer") == 0) {
					stack.getTagCompound().setLong("boundPlayer", uuid);
					stack.getTagCompound().setString("boundPlayerName", player.getDisplayName());

					if (world.isRemote) {
						player.addChatComponentMessage(new ChatComponentText("§2[" + ArchinumBuilds.MODID + "] "
								+ StatCollector.translateToLocal("th.ab.bound.message") + player.getDisplayName()));
					}
				} else if (stack.getTagCompound().getLong("boundPlayer") == uuid) {

					stack.getTagCompound().setLong("boundPlayer", 0);

					if (world.isRemote) {
						player.addChatComponentMessage(new ChatComponentText(
								"§2[" + ArchinumBuilds.MODID + "] " + StatCollector.translateToLocal("th.ab.unbound.message")));
					}
				}
			}

			// LIGHTNINGSTORM
			else if (stack.getTagCompound().getLong("boundPlayer") == uuid && KeyHandler.isModeSwitchDown == false) {
				int radius = 5;
				List<EntityLivingBase> e = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
						AxisAlignedBB.getBoundingBox(player.posX - radius, player.posY - radius, player.posZ - radius,
								player.posX + radius, player.posY + 5, player.posZ + radius));
				for (int i = 0; i <= e.size() - 1; i++) {
					EntityLivingBase em = (EntityLivingBase) e.get(i);
					if (em instanceof EntityPlayer && em.getUniqueID().getMostSignificantBits() == stack
							.getTagCompound().getLong("boundPlayer")) {

					} else {
						if (this.getEnergy(stack) >= 500000) {
							world.addWeatherEffect((new EntityLightningBolt(world, em.getPosition(1.0F).xCoord,
									em.getPosition(1.0F).yCoord, em.getPosition(1.0F).zCoord)));
							if (!player.capabilities.isCreativeMode) {
								this.setEnergy(stack, this.getEnergy(stack) - 500000);
							}
						}
					}
				}
			}
			// NOTYOURS
			else if (stack.getTagCompound().getLong("boundPlayer") != uuid
					&& stack.getTagCompound().getLong("boundPlayer") != 0) {
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentText(
							"§2[" + ArchinumBuilds.MODID + "] " + StatCollector.translateToLocal("th.ab.notyours.message")));
				}
			}

		}
		// NOTYOURS
		else if (stack.getTagCompound().getLong("boundPlayer") != uuid
				&& stack.getTagCompound().getLong("boundPlayer") != 0) {
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(
						"§2[" + ArchinumBuilds.MODID + "] " + StatCollector.translateToLocal("th.ab.notyours.message")));
			}
		}
		return stack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool) {
		if (stack.getTagCompound().getLong("boundPlayer") == 0) {
			stack.getTagCompound().setString("boundPlayerName", StatCollector.translateToLocal("th.ab.nobody"));
		}
	}

	@Override
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ArchinumBuilds.MODID + ":ThorsHammer");
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
		return getMaxEnergy(itemStack) * 0.05;
	}

	@Override
	public boolean canReceive(ItemStack itemStack) {
		return getMaxEnergy(itemStack) - getEnergy(itemStack) > 0;
	}

	@Override
	public boolean canSend(ItemStack itemStack) {
		return getEnergy(itemStack) > 0;
	}

}
