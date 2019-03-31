package com.peatral.archinumbuilds.client.GUI;

import com.peatral.archinumbuilds.client.GUI.elements.GuiElement;
import com.peatral.archinumbuilds.tileentity.TileEntityBasic;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GuiBasic extends GuiContainer implements IGuiWrapper {

    public Set<GuiElement> guiElements = new HashSet<GuiElement>();
    public TileEntityBasic tileEntity;
    public ResourceLocation res;
    public int inventoryOffset = 0;

    public GuiBasic(Container container, TileEntityBasic tileEntity, ResourceLocation res) {
        super(container);
        this.tileEntity = tileEntity;
        this.res = res;
    }

    @Override
    public void drawTexturedRect(int x, int y, int u, int v, int w, int h) {
        this.drawTexturedModalRect(x,y,u,v,w,h);
    }

    @Override
    public void drawTexturedRectFromIcon() {

    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileEntity.hasCustomInventoryName() ? this.tileEntity.getInventoryName() : I18n.format(this.tileEntity.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + ((inventoryOffset > 0) ? inventoryOffset + 2 : 0), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(this.res);
        int guiWidth = (this.width - this.xSize) / 2;
        int guiHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(guiWidth, guiHeight, 0, 0, this.xSize, this.ySize + inventoryOffset);

        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.renderBg(guiWidth, guiHeight);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.update();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int guiWidth = (this.width - this.xSize) / 2;
        int guiHeight = (this.height - this.ySize) / 2;

        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.mousePressed(mouseX, mouseY, mouseButton, guiWidth, guiHeight);
        }
    }
}
