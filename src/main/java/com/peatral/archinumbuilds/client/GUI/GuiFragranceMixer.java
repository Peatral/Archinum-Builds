package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.inventory.container.ContainerFragranceMixer;
import com.peatral.archinumbuilds.tileentity.TileEntityBasic;
import com.peatral.archinumbuilds.tileentity.TileEntityFragranceMixer;
import com.peatral.archinumbuilds.util.UtilsRes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiFragranceMixer extends GuiBasic {
    public GuiFragranceMixer(InventoryPlayer inventoryPlayer, TileEntityFragranceMixer tileEntity) {
        super(new ContainerFragranceMixer(inventoryPlayer, tileEntity), tileEntity, UtilsRes.getRes(UtilsRes.Type.GUI_CONTAINER, "fragranceMixer.png"));
    }
}


