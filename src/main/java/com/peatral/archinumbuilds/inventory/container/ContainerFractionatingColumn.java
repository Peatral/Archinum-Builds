package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityFractionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFractionatingColumn extends ContainerBasic {
    private int fuel;
    private int toFractionize;
    private int temperature;
    private int progressLevel1;
    private int progressLevel2;
    private int progressLevel3;

    public ContainerFractionatingColumn(InventoryPlayer inventoryPlayer, TileEntityFractionatingColumn tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.invOffset = 8;
        this.addInventory();

        this.addSlotToContainer(new Slot(tileEntity, 0, 37, 60));
        this.addSlotToContainer(new Slot(tileEntity, 1, 14, 60));

        this.addSlotToContainer(new Slot(tileEntity, 2, 112, 58));
        this.addSlotToContainer(new Slot(tileEntity, 3, 112, 39));
        this.addSlotToContainer(new Slot(tileEntity, 4, 112, 20));

    }

    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, ((TileEntityFractionatingColumn)this.tileEntity).fuel);
        iCrafting.sendProgressBarUpdate(this, 1, ((TileEntityFractionatingColumn)this.tileEntity).toFractionize);
        iCrafting.sendProgressBarUpdate(this, 2, ((TileEntityFractionatingColumn)this.tileEntity).temperature);
        iCrafting.sendProgressBarUpdate(this, 3, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel1);
        iCrafting.sendProgressBarUpdate(this, 4, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel2);
        iCrafting.sendProgressBarUpdate(this, 5, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel3);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);

            if (this.fuel != ((TileEntityFractionatingColumn)this.tileEntity).fuel) {
                iCrafting.sendProgressBarUpdate(this, 0, ((TileEntityFractionatingColumn)this.tileEntity).fuel);
            }

            if (this.toFractionize != ((TileEntityFractionatingColumn)this.tileEntity).toFractionize) {
                iCrafting.sendProgressBarUpdate(this, 1, ((TileEntityFractionatingColumn)this.tileEntity).toFractionize);
            }

            if (this.temperature != ((TileEntityFractionatingColumn)this.tileEntity).temperature) {
                iCrafting.sendProgressBarUpdate(this, 2, ((TileEntityFractionatingColumn)this.tileEntity).temperature);
            }

            if (this.progressLevel1 != ((TileEntityFractionatingColumn)this.tileEntity).progressLevel1) {
                iCrafting.sendProgressBarUpdate(this, 3, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel1);
            }

            if (this.progressLevel2 != ((TileEntityFractionatingColumn)this.tileEntity).progressLevel2) {
                iCrafting.sendProgressBarUpdate(this, 4, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel2);
            }

            if (this.progressLevel3 != ((TileEntityFractionatingColumn)this.tileEntity).progressLevel3) {
                iCrafting.sendProgressBarUpdate(this, 5, ((TileEntityFractionatingColumn)this.tileEntity).progressLevel3);
            }
        }

        this.fuel = ((TileEntityFractionatingColumn)this.tileEntity).fuel;
        this.toFractionize = ((TileEntityFractionatingColumn)this.tileEntity).toFractionize;
        this.temperature = ((TileEntityFractionatingColumn)this.tileEntity).temperature;
        this.progressLevel1 = ((TileEntityFractionatingColumn)this.tileEntity).progressLevel1;
        this.progressLevel2 = ((TileEntityFractionatingColumn)this.tileEntity).progressLevel2;
        this.progressLevel3 = ((TileEntityFractionatingColumn)this.tileEntity).progressLevel3;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            ((TileEntityFractionatingColumn)this.tileEntity).fuel = value;
        }

        if (id == 1) {
            ((TileEntityFractionatingColumn)this.tileEntity).toFractionize = value;
        }

        if (id == 2) {
            ((TileEntityFractionatingColumn)this.tileEntity).temperature = value;
        }

        if (id == 3) {
            ((TileEntityFractionatingColumn)this.tileEntity).progressLevel1 = value;
        }

        if (id == 4) {
            ((TileEntityFractionatingColumn)this.tileEntity).progressLevel2 = value;
        }

        if (id == 5) {
            ((TileEntityFractionatingColumn)this.tileEntity).progressLevel3 = value;
        }
    }


}
