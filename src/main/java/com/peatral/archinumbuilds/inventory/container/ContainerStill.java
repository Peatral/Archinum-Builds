package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityBasic;
import com.peatral.archinumbuilds.tileentity.TileEntityStill;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerStill extends ContainerBasic {

    private int stillBurnTime;
    private int currentItemBurnTime;
    private int stillDestillingTime;

    public ContainerStill(InventoryPlayer inventoryPlayer, TileEntityBasic tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.addInventory();

        this.addSlotToContainer(new Slot(tileEntity, 0, 55, 17));
        this.addSlotToContainer(new Slot(tileEntity, 1, 55, 53));
        this.addSlotToContainer(new Slot(tileEntity, 2, 105, 35));
    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, ((TileEntityStill)this.tileEntity).stillBurnTime);
        iCrafting.sendProgressBarUpdate(this, 1, ((TileEntityStill)this.tileEntity).currentItemBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, ((TileEntityStill)this.tileEntity).stillDestillingTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if (this.stillBurnTime != ((TileEntityStill)this.tileEntity).stillBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 0, ((TileEntityStill)this.tileEntity).stillBurnTime);
            }

            if (this.currentItemBurnTime != ((TileEntityStill)this.tileEntity).currentItemBurnTime) {
                iCrafting.sendProgressBarUpdate(this, 1, ((TileEntityStill)this.tileEntity).currentItemBurnTime);
            }

            if (this.stillDestillingTime != ((TileEntityStill)this.tileEntity).stillDestillingTime) {
                iCrafting.sendProgressBarUpdate(this, 2, ((TileEntityStill)this.tileEntity).stillDestillingTime);
            }
        }

        this.stillBurnTime = ((TileEntityStill)this.tileEntity).stillBurnTime;
        this.currentItemBurnTime = ((TileEntityStill)this.tileEntity).currentItemBurnTime;
        this.stillDestillingTime = ((TileEntityStill)this.tileEntity).stillDestillingTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            ((TileEntityStill)this.tileEntity).stillBurnTime = value;
        }

        if (id == 1) {
            ((TileEntityStill)this.tileEntity).currentItemBurnTime = value;
        }

        if (id == 2) {
            ((TileEntityStill)this.tileEntity).stillDestillingTime = value;
        }
    }
}
