package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.client.GUI.elements.GuiElementProgress;
import com.peatral.archinumbuilds.client.GUI.elements.GuiElementTab;
import com.peatral.archinumbuilds.client.GUI.elements.GuiElementTempBar;
import com.peatral.archinumbuilds.inventory.container.ContainerFractionatingColumn;
import com.peatral.archinumbuilds.tileentity.TileEntityFractionatingColumn;
import com.peatral.archinumbuilds.util.UtilsRes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiFractionatingColumn extends GuiBasic {

    public GuiFractionatingColumn(InventoryPlayer inventoryPlayer, final TileEntityFractionatingColumn tileEntity) {
        super(new ContainerFractionatingColumn(inventoryPlayer, tileEntity), tileEntity, UtilsRes.getRes(UtilsRes.Type.GUI_CONTAINER, "fractionatingColumn.png"));
        this.inventoryOffset = 8;

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((TileEntityFractionatingColumn)GuiFractionatingColumn.this.tileEntity).getProgressScaled(4);
            }
        }, this, GuiElementProgress.BUBBLE, this.res).setPosition(66, 33));

        this.guiElements.add(new GuiElementTempBar(new GuiElementTempBar.ITempInfoHandler() {
            public double getTemp() {
                return ((TileEntityFractionatingColumn)GuiFractionatingColumn.this.tileEntity).getTempScaled();
            }
        }, this, this.res).setPosition(147, 17));

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((TileEntityFractionatingColumn)GuiFractionatingColumn.this.tileEntity).getProgressScaled(0);
            }
        }, this, GuiElementProgress.RIGHT, this.res).setPosition(85, 59));

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((TileEntityFractionatingColumn)GuiFractionatingColumn.this.tileEntity).getProgressScaled(1);
            }
        }, this, GuiElementProgress.RIGHT, this.res).setPosition(85, 40));

        this.guiElements.add(new GuiElementProgress(new GuiElementProgress.IProgressInfoHandler() {
            public double getProgress() {
                return ((TileEntityFractionatingColumn)GuiFractionatingColumn.this.tileEntity).getProgressScaled(2);
            }
        }, this, GuiElementProgress.RIGHT, this.res).setPosition(85, 21));

        this.guiElements.add(new GuiElementTab(this, this.res).setPosition(176, 48));
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        super.drawGuiContainerBackgroundLayer(p_146976_1_, p_146976_2_, p_146976_3_);

        //x,y,u,v,w,h
        /*
        int i1 = (int) this.tileEntity.getFuelScaled() * 37;
        this.drawTexturedModalRect(k + 19, l + 18 + 37 - i1, 176, 13 + 37 - i1, 6, i1);

        int i2 = (int) this.tileEntity.getToFracScaled() * 37;
        this.drawTexturedModalRect(k + 42, l + 18 + 37 - i2, 182, 13 + 37 - i2, 6, i2);
        */
    }
}


