package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityBasic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBasic extends Container {
    protected int invOffset = 0;
    protected TileEntityBasic tileEntity;
    private InventoryPlayer inventoryPlayer;

    public ContainerBasic(InventoryPlayer inventoryPlayer, TileEntityBasic tileEntity) {
        this.tileEntity = tileEntity;
        this.inventoryPlayer = inventoryPlayer;
    }

    public void addInventory(){
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + invOffset));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + invOffset));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileEntity.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotID) {
        return null;
    }
}
