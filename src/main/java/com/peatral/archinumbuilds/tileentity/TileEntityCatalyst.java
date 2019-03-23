package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.block.BlockCatalyst;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCatalyst extends TileEntity implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{2, 1};
    private static final int[] slotsSides = new int[]{1};

    /**
     * The number of ticks that the catalyst will keep burning
     */
    public int catalystBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the catalyst burning for
     */
    public int currentItemBurnTime;
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int catalystCookTime;
    /**
     * The ItemStacks that hold the items currently being used in the catalyst
     */
    private ItemStack[] catalystItemStacks = new ItemStack[3];
    private String field_145958_o;

    /**
     * Returns the number of ticks that the supplied fuel item will keep the catalyst burning, or 0 if the item isn't
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
         * Returns the number of ticks that the supplied fuel item will keep the catalyst burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(itemStack) > 0;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.catalystItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot) {
        return this.catalystItemStacks[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int amount) {
        if (this.catalystItemStacks[slot] != null) {
            ItemStack itemstack;

            if (this.catalystItemStacks[slot].stackSize <= amount) {
                itemstack = this.catalystItemStacks[slot];
                this.catalystItemStacks[slot] = null;
                return itemstack;
            } else {
                itemstack = this.catalystItemStacks[slot].splitStack(amount);

                if (this.catalystItemStacks[slot].stackSize == 0) {
                    this.catalystItemStacks[slot] = null;
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
        if (this.catalystItemStacks[slot] != null) {
            ItemStack itemstack = this.catalystItemStacks[slot];
            this.catalystItemStacks[slot] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be recipes or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.catalystItemStacks[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.field_145958_o : "container.catalyst";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName() {
        return this.field_145958_o != null && this.field_145958_o.length() > 0;
    }

    public void func_145951_a(String inventoryName) {
        this.field_145958_o = inventoryName;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", 10);
        this.catalystItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.catalystItemStacks.length) {
                this.catalystItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.catalystBurnTime = nbtTagCompound.getShort("BurnTime");
        this.catalystCookTime = nbtTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.catalystItemStacks[1]);

        if (nbtTagCompound.hasKey("CustomName", 8)) {
            this.field_145958_o = nbtTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) this.catalystBurnTime);
        nbtTagCompound.setShort("CookTime", (short) this.catalystCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.catalystItemStacks.length; ++i) {
            if (this.catalystItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.catalystItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTagCompound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName()) {
            nbtTagCompound.setString("CustomName", this.field_145958_o);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int progress) {
        return this.catalystCookTime * progress / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int progress) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }

        return this.catalystBurnTime * progress / this.currentItemBurnTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning() {
        return this.catalystBurnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.catalystBurnTime > 0;
        boolean flag1 = false;

        if (this.catalystBurnTime > 0) {
            --this.catalystBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.catalystBurnTime != 0 || this.catalystItemStacks[1] != null && this.catalystItemStacks[0] != null) {
                if (this.catalystBurnTime == 0 && this.canSmelt()) {
                    this.currentItemBurnTime = this.catalystBurnTime = getItemBurnTime(this.catalystItemStacks[1]);

                    if (this.catalystBurnTime > 0) {
                        flag1 = true;

                        if (this.catalystItemStacks[1] != null) {
                            --this.catalystItemStacks[1].stackSize;

                            if (this.catalystItemStacks[1].stackSize == 0) {
                                this.catalystItemStacks[1] = catalystItemStacks[1].getItem().getContainerItem(catalystItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.catalystCookTime;

                    if (this.catalystCookTime == 200) {
                        this.catalystCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.catalystCookTime = 0;
                }
            }

            if (flag != this.catalystBurnTime > 0) {
                flag1 = true;
                BlockCatalyst.updateFurnaceBlockState(this.catalystBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    /**
     * Returns true if the catalyst can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt() {
        if (this.catalystItemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.catalystItemStacks[0]);
            if (itemstack == null) return false;
            if (this.catalystItemStacks[2] == null) return true;
            if (!this.catalystItemStacks[2].isItemEqual(itemstack)) return false;
            int result = catalystItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.catalystItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the catalyst source stack into the appropriate smelted item in the catalyst result stack
     */
    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.catalystItemStacks[0]);

            if (this.catalystItemStacks[2] == null) {
                this.catalystItemStacks[2] = itemstack.copy();
            } else if (this.catalystItemStacks[2].getItem() == itemstack.getItem()) {
                this.catalystItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.catalystItemStacks[0].stackSize;

            if (this.catalystItemStacks[0].stackSize <= 0) {
                this.catalystItemStacks[0] = null;
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return slot == 2 ? false : (slot == 1 ? isItemFuel(itemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsBottom : (side == 1 ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
    }
}