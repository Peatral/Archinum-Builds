package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityCatalyst;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerCatalyst extends Container {
    private static final String __OBFID = "CL_00001748";
    private TileEntityCatalyst tileCatalyst;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCatalyst(InventoryPlayer inventoryPlayer, TileEntityCatalyst tileEntityCatalyst) {
        this.tileCatalyst = tileEntityCatalyst;
        this.addSlotToContainer(new Slot(tileEntityCatalyst, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityCatalyst, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityCatalyst, 2, 116, 35));

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileCatalyst.catalystCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileCatalyst.catalystBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileCatalyst.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.tileCatalyst.catalystCookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileCatalyst.catalystCookTime);
            }

            if (this.lastBurnTime != this.tileCatalyst.catalystBurnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileCatalyst.catalystBurnTime);
            }

            if (this.lastItemBurnTime != this.tileCatalyst.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileCatalyst.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileCatalyst.catalystCookTime;
        this.lastBurnTime = this.tileCatalyst.catalystBurnTime;
        this.lastItemBurnTime = this.tileCatalyst.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.tileCatalyst.catalystCookTime = value;
        }

        if (id == 1) {
            this.tileCatalyst.catalystBurnTime = value;
        }

        if (id == 2) {
            this.tileCatalyst.currentItemBurnTime = value;
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileCatalyst.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotID) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotID != 1 && slotID != 0) {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityCatalyst.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (slotID >= 3 && slotID < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
}