package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.client.GUI.elements.GuiElementBurnTime;
import com.peatral.archinumbuilds.client.GUI.elements.GuiElementProgress;
import com.peatral.archinumbuilds.inventory.container.ContainerStill;
import com.peatral.archinumbuilds.tileentity.TileEntityStill;
import com.peatral.archinumbuilds.util.UtilsRes;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiStill extends GuiBasic {
    public GuiStill(InventoryPlayer inventoryPlayer, TileEntityStill tileEntity) {
        super(new ContainerStill(inventoryPlayer, tileEntity), tileEntity, UtilsRes.getRes(UtilsRes.Type.GUI_CONTAINER, "still.png"));

        this.guiElements.add(new GuiElementBurnTime(new GuiElementBurnTime.IBurnTimeInfoHandler() {
            public double getBurnTime() {
                return ((TileEntityStill) GuiStill.this.tileEntity).getBurnTimeScaled();
            }
        }, this, this.res).setPosition(55, 36));

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((((TileEntityStill) GuiStill.this.tileEntity).getDestillingProgressScaled() * 2) % 200) / 200;
            }
        }, this, GuiElementProgress.BUBBLE, this.res).setPosition(75, 41));

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((TileEntityStill) GuiStill.this.tileEntity).getDestillingProgressScaled();
            }
        }, this, GuiElementProgress.UP, this.res).setPosition(91, 42));
    }
}
