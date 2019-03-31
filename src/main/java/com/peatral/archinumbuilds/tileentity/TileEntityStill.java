package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.recipes.RecipesStill;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityStill extends TileEntityBasic {
    /**
     * The number of ticks that the still will keep burning
     */
    public int stillBurnTime;
    /**
     * The number of ticks that a fresh copy of the still-burning item would keep the still burning for
     */
    public int currentItemBurnTime;
    /**
     * The number of ticks that the current item has been destilling for
     */
    public int stillDestillingTime;

    public TileEntityStill() {
        super("still", 3);
    }

    @SideOnly(Side.CLIENT)
    public double getDestillingProgressScaled() {
        return (double) this.stillDestillingTime / 200;
    }

    @SideOnly(Side.CLIENT)
    public double getBurnTimeScaled(){
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return (double) stillBurnTime / currentItemBurnTime;
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the still burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            int moddedBurnTime = net.minecraftforge.event.ForgeEventFactory.getFuelBurnTime(itemStack);
            if (moddedBurnTime >= 0) return moddedBurnTime;

            Item item = itemStack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab) {
                    return 150;
                }

                if (block.getMaterial() == Material.wood) {
                    return 300;
                }

                if (block == Blocks.coal_block) {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(itemStack);
        }
    }

    public static boolean isItemFuel(ItemStack itemStack) {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the still burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(itemStack) > 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        this.stillBurnTime = nbtTagCompound.getShort("BurnTime");
        this.stillDestillingTime = nbtTagCompound.getShort("DestillingTime");
        this.currentItemBurnTime = getItemBurnTime(this.inventory[1]);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort("BurnTime", (short) this.stillBurnTime);
        nbtTagCompound.setShort("DestillingTime", (short) this.stillDestillingTime);
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.stillBurnTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean flag = this.stillBurnTime > 0;
        boolean flag1 = false;

        if (this.stillBurnTime > 0) {
            --this.stillBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.stillBurnTime != 0 || this.inventory[1] != null && this.inventory[0] != null) {
                if (this.stillBurnTime == 0 && this.canDestillate()) {
                    this.currentItemBurnTime = this.stillBurnTime = getItemBurnTime(this.inventory[1]);

                    if (this.stillBurnTime > 0) {
                        flag1 = true;

                        if (this.inventory[1] != null) {
                            --this.inventory[1].stackSize;

                            if (this.inventory[1].stackSize == 0) {
                                this.inventory[1] = inventory[1].getItem().getContainerItem(inventory[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canDestillate()) {
                    ++this.stillDestillingTime;

                    if (this.stillDestillingTime == 200) {
                        this.stillDestillingTime = 0;
                        this.destillateItem();
                        flag1 = true;
                    }
                } else {
                    this.stillDestillingTime = 0;
                }
            }

            if (flag != this.stillBurnTime > 0) {
                flag1 = true;
                //BlockCatalyst.updateFurnaceBlockState(this.stillBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    /**
     * Returns true if the still can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canDestillate() {
        if (this.inventory[0] == null) {
            return false;
        } else {
            ItemStack itemstack = RecipesStill.destilling().getStillResult(this.inventory[0]);
            if (itemstack == null) return false;
            if (this.inventory[2] == null) return true;
            if (!this.inventory[2].isItemEqual(itemstack)) return false;
            int result = inventory[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.inventory[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the still source stack into the appropriate smelted item in the still result stack
     */
    public void destillateItem() {
        if (this.canDestillate()) {
            ItemStack itemstack = RecipesStill.destilling().getStillResult(this.inventory[0]);

            if (this.inventory[2] == null) {
                this.inventory[2] = itemstack.copy();
            } else if (this.inventory[2].getItem() == itemstack.getItem()) {
                this.inventory[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.inventory[0].stackSize;

            if (this.inventory[0].stackSize <= 0) {
                this.inventory[0] = null;
            }
        }
    }
}
