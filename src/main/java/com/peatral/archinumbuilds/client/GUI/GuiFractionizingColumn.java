package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.tileentity.TileEntityFracionatingColumn;
import com.peatral.archinumbuilds.inventory.container.ContainerFracionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiFractionizingColumn extends GuiContainer
{
    private static final ResourceLocation catalystGuiTextures = new ResourceLocation(ArchinumBuilds.MODID + ":textures/gui/container/fractionatingColumn.png");
    private TileEntityFracionatingColumn tileEntityFracionatingColumn;
    private static final String __OBFID = "CL_00000758";

    public GuiFractionizingColumn(InventoryPlayer inventoryPlayer, TileEntityFracionatingColumn tileEntityFracionatingColumn)
    {
        super(new ContainerFracionatingColumn(inventoryPlayer, tileEntityFracionatingColumn));
        this.tileEntityFracionatingColumn = tileEntityFracionatingColumn;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.tileEntityFracionatingColumn.hasCustomInventoryName() ? this.tileEntityFracionatingColumn.getInventoryName() : I18n.format(this.tileEntityFracionatingColumn.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2 + 8, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(catalystGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize + 8);

        //x,y,u,v,w,h

        int i1 = this.tileEntityFracionatingColumn.getFuelScaled(37);
        this.drawTexturedModalRect(k + 19, l + 18 + 37 - i1, 176, 13 + 37 - i1, 6, i1);

        int i2 = this.tileEntityFracionatingColumn.getToFracScaled(37);
        this.drawTexturedModalRect(k + 42, l + 18 + 37 - i2, 182, 13 + 37 - i2, 6, i2);

        int i3 = this.tileEntityFracionatingColumn.getProgressScaled(2,23);
        this.drawTexturedModalRect(k + 85, l + 21, 176, 0, i3, 13);

        int i4 = this.tileEntityFracionatingColumn.getProgressScaled(1,23);
        this.drawTexturedModalRect(k + 85, l + 40, 176, 0, i4, 13);

        int i5 = this.tileEntityFracionatingColumn.getProgressScaled(0,23);
        this.drawTexturedModalRect(k + 85, l + 59, 176, 0, i5, 13);

        int i6 = this.tileEntityFracionatingColumn.getProgressScaled(4,29);
        this.drawTexturedModalRect(k + 66, l + 33 + 29 - i6, 176, 50 + 29 - i6, 12, i6);

        int i7 = this.tileEntityFracionatingColumn.getTempScaled(58);
        this.drawTexturedModalRect(k + 148, l + 18 + 58 - i7, 188, 13 + 58 - i7, 6, i7);
    }
}


