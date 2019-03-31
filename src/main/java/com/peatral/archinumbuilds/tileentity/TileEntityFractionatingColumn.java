package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.recipes.RecipesFractionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityFractionatingColumn extends TileEntityBasic {

    public static final int stepsLevel1 = 100; // in Ticks
    public static final int stepsLevel2 = 10;  // in Steps of Level 1
    public static final int stepsLevel3 = 20;  // in Steps of Level 2
    public static final int maxFuel = 200;
    public static final int maxToFractionize = 200;
    public static final int maxTemp = 3000;
    private static final int inputSlot = 0;
    private static final int fuelSlot = 1;
    private static final int[] outputSlots = new int[]{2, 3, 4};
    public int fuel;
    public int toFractionize;
    public int temperature;
    public int progressLevel1;
    public int progressLevel2;
    public int progressLevel3;
    private ItemStack recipe;
    private int countTillCooldown;
    private int countTillConsumeFuel;

    public TileEntityFractionatingColumn() {
        super("fractionatingColumn", 5);
    }

    @SideOnly(Side.CLIENT)
    public double getFuelScaled() {
        return (float) this.fuel / maxFuel;
    }

    @SideOnly(Side.CLIENT)
    public double getToFracScaled() {
        return (float) this.toFractionize / maxToFractionize;
    }

    @SideOnly(Side.CLIENT)
    public double getTempScaled() {
        return (float) this.temperature / maxTemp;
    }

    @SideOnly(Side.CLIENT)
    public double getProgressScaled(int type) {
        switch (type) {
            case 0:
                return (float) this.progressLevel1 / stepsLevel1;
            case 1:
                return (float) this.progressLevel2 / stepsLevel2;
            case 2:
                return (float) this.progressLevel3 / stepsLevel3;
            case 4:
                return (float) ((this.progressLevel1 * 2) % stepsLevel1) / stepsLevel1;
            default:
                return 0.0f;
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        this.fuel = nbtTagCompound.getInteger("Fuel");
        this.toFractionize = nbtTagCompound.getInteger("toFrac");
        this.temperature = nbtTagCompound.getInteger("Temp");
        this.recipe = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("Recipe"));
        this.progressLevel1 = nbtTagCompound.getInteger("prog1");
        this.progressLevel2 = nbtTagCompound.getInteger("prog2");
        this.progressLevel3 = nbtTagCompound.getInteger("prog3");

        this.countTillCooldown = nbtTagCompound.getInteger("countTillCooldown");
        this.countTillConsumeFuel = nbtTagCompound.getInteger("countTillConsumeFuel");
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        this.recipe.writeToNBT(nbttagcompound1);
        nbtTagCompound.setTag("Recipe", nbttagcompound1);
        nbtTagCompound.setInteger("Fuel", this.fuel);
        nbtTagCompound.setInteger("toFrac", this.toFractionize);
        nbtTagCompound.setInteger("Temp", this.temperature);
        nbtTagCompound.setInteger("prog1", this.progressLevel1);
        nbtTagCompound.setInteger("prog2", this.progressLevel2);
        nbtTagCompound.setInteger("prog3", this.progressLevel3);

        nbtTagCompound.setInteger("countTillCooldown", this.countTillCooldown);
        nbtTagCompound.setInteger("countTillConsumeFuel", this.countTillConsumeFuel);
    }


    //TODO: improve automation access

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (slot == fuelSlot ? isItemFuel(itemStack) : (slot != inputSlot || RecipesFractionatingColumn.fractionating().getFractionatingResult(itemStack) != null));
    }

    //TODO: improve automation access

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ?
                outputSlots :
                (side == 1 ?
                        new int[]{inputSlot} :
                        new int[]{fuelSlot}
                );
    }

    //TODO: improve automation access

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    //TODO: improve automation access

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return slot != inputSlot && slot != fuelSlot;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        boolean flag = false;

        if (!this.worldObj.isRemote) {
            if (this.fuel > 0 && this.temperature < maxTemp) {
                this.temperature++;
                flag = true;

            } else if (this.temperature >= maxTemp) {
                this.countTillCooldown = 500;
            } else {
                if (this.countTillCooldown > 0) {
                    this.countTillCooldown--;
                } else if (this.temperature > 0) {
                    this.temperature--;
                    flag = true;
                } else if (this.temperature < 0) this.temperature = 0;
            }

            this.countTillConsumeFuel++;
            if (this.countTillConsumeFuel >= this.temperature * 40 / maxTemp + 10) {
                this.fuel--;
                this.countTillConsumeFuel = 0;
                flag = true;
            }


            //Fill toFractionize
            if (this.canFill()) {
                if (this.recipe == null) {
                    this.recipe = this.inventory[inputSlot].copy();
                    this.decrStackSize(inputSlot, 1);
                    this.toFractionize += 1;
                } else {
                    this.decrStackSize(inputSlot, 1);
                    this.toFractionize += 1;
                }
                flag = true;
            }

            //Fill Fuel
            if (this.isItemFuel(fuelSlot)) {
                if (this.inventory[fuelSlot].stackSize > 0 && this.fuel < maxFuel - 10) {
                    this.decrStackSize(fuelSlot, 1);
                    this.fuel += 10;
                    flag = true;
                }
            }

            if (this.canOperate()) {
                ItemStack[] itemstack = RecipesFractionatingColumn.fractionating().getFractionatingResult(this.recipe);

                this.progressLevel1++;
                if (this.progressLevel1 >= stepsLevel1) {
                    this.progressLevel1 = 0;
                    if (this.inventory[outputSlots[0]] == null) {
                        this.inventory[outputSlots[0]] = itemstack[0].copy();
                    } else if (this.inventory[outputSlots[0]].getItem() == itemstack[0].getItem()) {
                        this.inventory[outputSlots[0]].stackSize += itemstack[0].stackSize; // Forge BugFix: Results may have multiple items
                    }
                    this.progressLevel2++;
                    this.toFractionize--;
                    if (this.progressLevel2 >= stepsLevel2) {
                        this.progressLevel2 = 0;
                        if (this.inventory[outputSlots[1]] == null) {
                            this.inventory[outputSlots[1]] = itemstack[1].copy();
                        } else if (this.inventory[outputSlots[1]].getItem() == itemstack[1].getItem()) {
                            this.inventory[outputSlots[1]].stackSize += itemstack[1].stackSize; // Forge BugFix: Results may have multiple items
                        }
                        this.progressLevel3++;
                        if (this.progressLevel3 >= stepsLevel3) {
                            this.progressLevel3 = 0;
                            if (this.inventory[outputSlots[2]] == null) {
                                this.inventory[outputSlots[2]] = itemstack[2].copy();
                            } else if (this.inventory[outputSlots[2]].getItem() == itemstack[2].getItem()) {
                                this.inventory[outputSlots[2]].stackSize += itemstack[2].stackSize; // Forge BugFix: Results may have multiple items
                            }
                            if (this.toFractionize <= 0)
                                this.recipe = null;
                        }
                    }
                }

                flag = true;
            }
        }

        if (flag) this.markDirty();
    }

    public boolean canOperate() {
        if (this.recipe == null || this.fuel <= 0 || this.toFractionize <= 0) {
            return false;
        } else {
            ItemStack[] results = RecipesFractionatingColumn.fractionating().getFractionatingResult(this.recipe);
            if (results == null) return false;
            if (
                    this.inventory[outputSlots[0]] == null &&
                            this.inventory[outputSlots[1]] == null &&
                            this.inventory[outputSlots[2]] == null) return true;

            int[] resultSizes = new int[outputSlots.length];
            for (int i = 0; i < outputSlots.length; i++) {
                if (this.inventory[outputSlots[i]] != null) {
                    if (!this.inventory[outputSlots[i]].isItemEqual(results[i])) {
                        return false;
                    }
                    resultSizes[i] = inventory[outputSlots[i]].stackSize + results[i].stackSize;
                } else {
                    resultSizes[i] = results[i].stackSize;
                }
            }

            return isSlotFullWithStackSize(outputSlots[0], resultSizes[0]) &&
                    isSlotFullWithStackSize(outputSlots[1], resultSizes[1]) &&
                    isSlotFullWithStackSize(outputSlots[2], resultSizes[2]);
        }
    }

    public boolean canFill() {
        if (this.inventory[inputSlot] == null || this.inventory[inputSlot].stackSize <= 0) {
            return false;
        }

        if (this.recipe == null) {
            return this.toFractionize < maxToFractionize && RecipesFractionatingColumn.fractionating().getFractionatingResult(this.inventory[inputSlot]) != null;
        } else if (this.inventory[inputSlot].isItemEqual(this.recipe) && this.inventory[inputSlot].getItemDamage() == this.recipe.getItemDamage()) {
            return this.toFractionize < maxToFractionize;
        }

        return false;

    }

    public boolean isSlotFullWithStackSize(int slot, int stackSize) {
        if (this.inventory[slot] == null) {
            return stackSize <= getInventoryStackLimit();
        } else {
            return stackSize <= getInventoryStackLimit() && stackSize <= this.inventory[slot].getMaxStackSize();
        }
    }

    public boolean isItemFuel(int slot) {
        return this.inventory[slot] != null && this.inventory[slot].getItem() == Items.coal;
    }

    public boolean isItemFuel(ItemStack itemStack) {
        return itemStack.getItem() == Items.coal;
    }
}
