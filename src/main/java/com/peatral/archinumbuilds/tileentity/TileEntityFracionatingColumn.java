package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.recipes.RecipesFractionatingColumn;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFracionatingColumn extends TileEntity implements ISidedInventory {

    private static final int inputSlot = 0;
    private static final int fuelSlot = 1;
    private static final int[] outputSlots = new int[]{2,3,4};

    public int fuel;
    public int toFractionize;
    private ItemStack recipe;
    public int temperature;
    public int progressLevel1;
    public int progressLevel2;
    public int progressLevel3;
    
    private int countTillCooldown;
    private int countTillConsumeFuel;

    private static final int stepsLevel1 = 100; // in Ticks
    private static final int stepsLevel2 = 10;  // in Steps of Level 1
    private static final int stepsLevel3 = 20;  // in Steps of Level 2
    private static final int maxFuel = 200;
    private static final int maxToFractionize = 200;
    private static final int maxTemp = 1000;

    /**
     * The ItemStacks that hold the items currently being used in the catalyst
     */
    private ItemStack[] inventory = new ItemStack[5];

    private String customInventoryName;

    public int getFuelScaled(int val){
        return this.fuel * val / maxFuel;
    }

    public int getToFracScaled(int val){
        return this.toFractionize * val / maxToFractionize;
    }

    public int getTempScaled(int val){
        return this.temperature * val / maxTemp;
    }

    public int getProgressScaled(int type, int val){
        switch(type){
            case 0:
                return this.progressLevel1 * val / stepsLevel1;
            case 1:
                return this.progressLevel2 * val / stepsLevel2;
            case 2:
                return this.progressLevel3 * val / stepsLevel3;
            case 4:
                return ((this.progressLevel1 * 2) % stepsLevel1) * val / stepsLevel1;
            default:
                return 0;
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.fuel = nbtTagCompound.getInteger("Fuel");
        this.toFractionize = nbtTagCompound.getInteger("toFrac");
        this.temperature = nbtTagCompound.getInteger("Temp");
        this.recipe = ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("Recipe"));
        this.progressLevel1 = nbtTagCompound.getInteger("prog1");
        this.progressLevel2 = nbtTagCompound.getInteger("prog2");
        this.progressLevel3 = nbtTagCompound.getInteger("prog3");

        this.countTillCooldown = nbtTagCompound.getInteger("countTillCooldown");
        this.countTillConsumeFuel = nbtTagCompound.getInteger("countTillConsumeFuel");

        if (nbtTagCompound.hasKey("CustomName", 8)) {
            this.customInventoryName = nbtTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        //nbtTagCompound.setShort("BurnTime", (short) this.catalystBurnTime);
        //nbtTagCompound.setShort("CookTime", (short) this.catalystCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);
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

        if (this.hasCustomInventoryName()) {
            nbtTagCompound.setString("CustomName", this.customInventoryName);
        }
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.inventory[slot] != null) {
            ItemStack itemstack;

            if (this.inventory[slot].stackSize <= amount) {
                itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemstack;
            } else {
                itemstack = this.inventory[slot].splitStack(amount);

                if (this.inventory[slot].stackSize == 0) {
                    this.inventory[slot] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.inventory[slot] != null) {
            ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be recipes or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customInventoryName : "container.fractionatingColumn";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName() {
        return this.customInventoryName != null && this.customInventoryName.length() > 0;
    }

    public void setInventoryName(String customInventoryName) {
        this.customInventoryName = customInventoryName;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    //TODO: improve automation access
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (slot == fuelSlot ? isItemFuel(itemStack) : (slot == inputSlot ? RecipesFractionatingColumn.fractionating().getFractionatingResult(itemStack) != null : true));
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
            if(this.fuel > 0 && this.temperature < maxTemp){
                this.temperature++;
                flag = true;

            }
            else if(this.temperature >= maxTemp){
                this.countTillCooldown = 500;
            }
            else{
                if(this.countTillCooldown > 0) {
                    this.countTillCooldown--;
                }
                else if(this.temperature > 0){
                    this.temperature--;
                    flag = true;
                }
                else if(this.temperature < 0) this.temperature = 0;
            }

            this.countTillConsumeFuel++;
            if(this.countTillConsumeFuel >= this.temperature * 40 / maxTemp + 10){
                this.fuel--;
                this.countTillConsumeFuel = 0;
                flag = true;
            }


            //Fill toFractionize
            if(this.canFill()){
                if(this.recipe == null){
                    this.recipe = this.inventory[inputSlot].copy();
                    this.decrStackSize(inputSlot, 1);
                    this.toFractionize += 1;
                }
                else{
                    this.decrStackSize(inputSlot, 1);
                    this.toFractionize += 1;
                }
                flag = true;
            }

            //Fill Fuel
            if(this.isItemFuel(fuelSlot)) {
                if (this.inventory[fuelSlot].stackSize > 0 && this.fuel < maxFuel-10) {
                    this.decrStackSize(fuelSlot, 1);
                    this.fuel += 10;
                    flag = true;
                }
            }

            if(this.canOperate()){
                ItemStack itemstack[] = RecipesFractionatingColumn.fractionating().getFractionatingResult(this.recipe);

                this.progressLevel1++;
                if(this.progressLevel1 >= stepsLevel1) {
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

        if(flag) this.markDirty();
    }

    public boolean canOperate(){
        if(this.recipe == null || this.fuel <= 0 || this.toFractionize <= 0) {
            return false;
        }
        else {
            ItemStack[] results = RecipesFractionatingColumn.fractionating().getFractionatingResult(this.recipe);
            if (results == null) return false;

            //TODO: For Loops
            if (
                    this.inventory[outputSlots[0]] == null &&
                    this.inventory[outputSlots[1]] == null &&
                    this.inventory[outputSlots[2]] == null) return true;

            int[] resultSizes = new int[outputSlots.length];
            for(int i = 0; i < outputSlots.length; i++) {
                if (this.inventory[outputSlots[i]] != null) {
                    if (!this.inventory[outputSlots[i]].isItemEqual(results[i])) {
                        return false;
                    }
                    resultSizes[i] = inventory[outputSlots[i]].stackSize + results[i].stackSize;
                }
                else{
                    resultSizes[i] = results[i].stackSize;
                }
            }

            return isSlotFullWithStackSize(outputSlots[0], resultSizes[0]) &&
                    isSlotFullWithStackSize(outputSlots[1], resultSizes[1]) &&
                    isSlotFullWithStackSize(outputSlots[2], resultSizes[2]);
        }
    }

    public boolean canFill(){
        if(this.inventory[inputSlot] == null || this.inventory[inputSlot].stackSize <= 0)
        {
            return false;
        }

        if(this.recipe == null){
            if (this.toFractionize < maxToFractionize && RecipesFractionatingColumn.fractionating().getFractionatingResult(this.inventory[inputSlot]) != null) return true;
            else return false;
        }
        else if(this.inventory[inputSlot].isItemEqual(this.recipe) && this.inventory[inputSlot].getItemDamage() == this.recipe.getItemDamage()){
            if (this.toFractionize < maxToFractionize) return true;
            return false;
        }

        return false;

    }

    public boolean isSlotFullWithStackSize(int slot, int stackSize){
        if(this.inventory[slot] == null){
            return stackSize <= getInventoryStackLimit();
        }else {
            return stackSize <= getInventoryStackLimit() && stackSize <= this.inventory[slot].getMaxStackSize();
        }
    }

    public boolean isItemFuel(int slot){
        return this.inventory[slot] != null && this.inventory[slot].getItem() == Items.coal;
    }

    public boolean isItemFuel(ItemStack itemStack){
        return itemStack.getItem() == Items.coal;
    }
}
