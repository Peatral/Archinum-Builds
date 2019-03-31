package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.entity.Entity3DMGHook;
import com.peatral.archinumbuilds.handler.ConfigHandler;
import com.peatral.archinumbuilds.handler.KeyHandler;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.client.Models.Model3DMGSingle;
import com.peatral.archinumbuilds.util.Vec;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemArmor3DMG extends ItemArmor {
    private Entity3DMGHook hookLeft;
    private Entity3DMGHook hookRight;

    public boolean launched = false;
    public boolean jumped = false;

    public ItemArmor3DMG(int type) {
        super(EnumHelper.addArmorMaterial("3DMG", 0, new int[]{0, 0, 3, 0}, 0), 0, type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int stackSlot) {

        ModelBiped stackModel = new Model3DMGSingle();

        if (stackModel != null) {
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

            EntityPlayer player = (EntityPlayer) entityLiving;

            ItemStack held_item = player.getEquipmentInSlot(0);

            if (held_item != null) {
                stackModel.heldItemRight = 1;

                if (player.getItemInUseCount() > 0) {

                    EnumAction enumaction = held_item.getItemUseAction();

                    if (enumaction == EnumAction.bow) {
                        stackModel.aimedBow = true;
                    } else if (enumaction == EnumAction.block) {
                        stackModel.heldItemRight = 3;
                    }
                }
            }
        }

        return stackModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ArchinumBuilds.MODID + ":textures/armor/3DMG.png";
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {

        // -------------------------------------ACTIONS_START-----------------------------------------------
        if (player.inventory.getCurrentItem() != null
                && player.inventory.getCurrentItem().getItem() == ABItems.threedmgSword) {

            // -----------------KEY_Q--------------
            if (KeyHandler.is3DMGActivateDown && stack.getTagCompound().getInteger("Gas") >= 1 && launched) {
                // F = -kx
                // F = ma
                // a = (-kx)/m

                Vec playerPos = Vec.positionVec(player);

                Vec hookLeftPos = new Vec(this.hookLeft.posX, this.hookLeft.posY, this.hookLeft.posZ);
                Vec hookRightPos = new Vec(this.hookRight.posX, this.hookRight.posY, this.hookRight.posZ);

                Vec middleVec = Vec.middleVec(hookLeftPos, hookRightPos);

                Vec distVec = playerPos.subtract(middleVec);
                double stiffness = ConfigHandler.threedmgStiffness;
                double mass = ConfigHandler.playerMass;

                Vec acceleration = distVec.multiply(-stiffness).divide(mass).constrain(Vec.maxLenth);

                double dist = playerPos.distanceTo(middleVec);

                if(dist <= 75.0d) {
                    if(dist <= 2.0d){
                        this.detachHooks();
                    }
                    else {
                        Vec.applyMotion(player, acceleration);

                        if (!player.capabilities.isCreativeMode) {
                            stack.getTagCompound().setInteger("Gas", stack.getTagCompound().getInteger("Gas") - 1);
                        }
                    }
                }
            }
            if(!this.jumped) {
                this.jumped = true;
                if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
                    Vec.applyMotion(player, new Vec(0, 0, 0));
                }
            }
            else {
                this.jumped = false;
            }
        }
        // -------------------------------------ACTIONS_END-------------------------------------------------

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("Gas", 1000);
        }
    }

    public void detachHooks() {
        if (this.hookRight != null) {
            this.hookRight.setDead();
        }

        if (this.hookLeft != null) {
            this.hookLeft.setDead();
        }

        this.launched = false;
    }

    public void attachHooks(Entity3DMGHook hookLeft, Entity3DMGHook hookRight){
        this.hookLeft = hookLeft;
        this.hookRight = hookRight;
        this.launched = true;
    }

    public void spawnHooks(World world, EntityPlayer player){
        Entity3DMGHook hookLeft = new Entity3DMGHook(world, (EntityLivingBase) player, 5.0f, -20);
        world.spawnEntityInWorld(hookLeft);
        hookLeft.setOwner(player);

        Entity3DMGHook hookRight = new Entity3DMGHook(world, (EntityLivingBase) player, 5.0f, 20);
        world.spawnEntityInWorld(hookRight);
        hookRight.setOwner(player);

        this.attachHooks(hookLeft, hookRight);
    }

    public boolean isLaunched() {
        return launched;
    }
}
