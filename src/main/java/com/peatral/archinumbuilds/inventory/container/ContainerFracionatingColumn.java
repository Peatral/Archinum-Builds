package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityFracionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFracionatingColumn extends Container {
    private TileEntityFracionatingColumn tileEntityFracionatingColumn;
    private int fuel;
    private int toFractionize;
    private int temperature;
    private int progressLevel1;
    private int progressLevel2;
    private int progressLevel3;

    public ContainerFracionatingColumn(InventoryPlayer inventoryPlayer, TileEntityFracionatingColumn tileEntityFracionatingColumn) {
        this.tileEntityFracionatingColumn = tileEntityFracionatingColumn;

        this.addSlotToContainer(new Slot(tileEntityFracionatingColumn, 0, 37, 60));
        this.addSlotToContainer(new Slot(tileEntityFracionatingColumn, 1, 14, 60));

        this.addSlotToContainer(new Slot(tileEntityFracionatingColumn, 2, 112, 58));
        this.addSlotToContainer(new Slot(tileEntityFracionatingColumn, 3, 112, 39));
        this.addSlotToContainer(new Slot(tileEntityFracionatingColumn, 4, 112, 20));

        int i;
        int offset = 8;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + offset));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142 + offset));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityFracionatingColumn.fuel);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityFracionatingColumn.toFractionize);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityFracionatingColumn.temperature);
        iCrafting.sendProgressBarUpdate(this, 3, this.tileEntityFracionatingColumn.progressLevel1);
        iCrafting.sendProgressBarUpdate(this, 4, this.tileEntityFracionatingColumn.progressLevel2);
        iCrafting.sendProgressBarUpdate(this, 5, this.tileEntityFracionatingColumn.progressLevel3);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if (this.fuel != this.tileEntityFracionatingColumn.fuel) {
                iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityFracionatingColumn.fuel);
            }

            if (this.toFractionize != this.tileEntityFracionatingColumn.toFractionize) {
                iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityFracionatingColumn.toFractionize);
            }

            if (this.temperature != this.tileEntityFracionatingColumn.temperature) {
                iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityFracionatingColumn.temperature);
            }

            if (this.progressLevel1 != this.tileEntityFracionatingColumn.progressLevel1) {
                iCrafting.sendProgressBarUpdate(this, 3, this.tileEntityFracionatingColumn.progressLevel1);
            }

            if (this.progressLevel2 != this.tileEntityFracionatingColumn.progressLevel2) {
                iCrafting.sendProgressBarUpdate(this, 4, this.tileEntityFracionatingColumn.progressLevel2);
            }

            if (this.progressLevel3 != this.tileEntityFracionatingColumn.progressLevel3) {
                iCrafting.sendProgressBarUpdate(this, 5, this.tileEntityFracionatingColumn.progressLevel3);
            }
        }

        this.fuel = this.tileEntityFracionatingColumn.fuel;
        this.toFractionize = this.tileEntityFracionatingColumn.toFractionize;
        this.temperature = this.tileEntityFracionatingColumn.temperature;
        this.progressLevel1 = this.tileEntityFracionatingColumn.progressLevel1;
        this.progressLevel2 = this.tileEntityFracionatingColumn.progressLevel2;
        this.progressLevel3 = this.tileEntityFracionatingColumn.progressLevel3;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            this.tileEntityFracionatingColumn.fuel = value;
        }

        if (id == 1) {
            this.tileEntityFracionatingColumn.toFractionize = value;
        }

        if (id == 2) {
            this.tileEntityFracionatingColumn.temperature = value;
        }

        if (id == 3) {
            this.tileEntityFracionatingColumn.progressLevel1 = value;
        }

        if (id == 4) {
            this.tileEntityFracionatingColumn.progressLevel2 = value;
        }

        if (id == 5) {
            this.tileEntityFracionatingColumn.progressLevel3 = value;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return this.tileEntityFracionatingColumn.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotID) {
        return null;
    }
}
