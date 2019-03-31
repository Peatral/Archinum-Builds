package com.peatral.archinumbuilds.inventory.container;

import com.peatral.archinumbuilds.tileentity.TileEntityBasic;
import com.peatral.archinumbuilds.tileentity.TileEntityFragranceMixer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFragranceMixer extends ContainerBasic {

    public ContainerFragranceMixer(InventoryPlayer inventoryPlayer, TileEntityBasic tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.addInventory();

        this.addSlotToContainer(new Slot(tileEntity, 0, 39, 34));
        this.addSlotToContainer(new Slot(tileEntity, 1, 59, 34));
        this.addSlotToContainer(new Slot(tileEntity, 2, 107, 34));
        this.addSlotToContainer(new Slot(tileEntity, 3, 127, 34));
    }
}