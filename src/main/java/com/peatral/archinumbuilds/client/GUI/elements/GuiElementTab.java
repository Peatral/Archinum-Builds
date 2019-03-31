package com.peatral.archinumbuilds.client.GUI.elements;

import com.peatral.archinumbuilds.client.GUI.IGuiWrapper;
import com.peatral.archinumbuilds.util.UtilsRes;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GuiElementTab extends GuiElement {

    public static int tabExpandSpeed = 8;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;

    public static final ResourceLocation RESOURCE_RIGHT = UtilsRes.getRes(UtilsRes.Type.GUI_ELEMENT, "tab_right.png");
    public static final ResourceLocation RESOURCE_LEFT = UtilsRes.getRes(UtilsRes.Type.GUI_ELEMENT, "tab_left.png");

    public boolean open;
    public boolean fullyOpen;
    public int side = RIGHT;

    public int offsetX = 0;
    public int offsetY = 0;

    public int minWidth = 22;
    public int maxWidth = 64;
    public int currentWidth = minWidth;

    public int minHeight = 22;
    public int maxHeight = 64;
    public int currentHeight = minHeight;

    public Set<GuiElement> guiElements = new HashSet<GuiElement>();

    public GuiElementTab(IGuiWrapper guiWrapper, ResourceLocation def) {
        super(guiWrapper, def, RESOURCE_RIGHT);
    }

    public GuiElementTab(int side, IGuiWrapper guiWrapper, ResourceLocation def) {
        super(guiWrapper, def, RESOURCE_RIGHT);

        this.side = side;

        if(side == LEFT){
            this.res = RESOURCE_LEFT;
        }
    }

    @Override
    public void renderBg(int x, int y) {
        super.renderBg(x, y);

        mc.getTextureManager().bindTexture(this.res);

        int xPosition = x + posX();
        int yPosition = y + this.y;

        guiWrapper.drawTexturedRect(xPosition, yPosition + 4, 0, 256 - currentHeight + 4, 4, currentHeight - 4);
        guiWrapper.drawTexturedRect(xPosition + 4, yPosition, 256 - currentWidth + 4, 0, currentWidth - 4, 4);
        guiWrapper.drawTexturedRect(xPosition, yPosition, 0, 0, 4, 4);
        guiWrapper.drawTexturedRect(xPosition + 4, yPosition + 4, 256 - currentWidth + 4, 256 - currentHeight + 4, currentWidth - 4, currentHeight - 4);

        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.renderBg(xPosition, yPosition);
        }

        mc.getTextureManager().bindTexture(this.def);
    }

    public GuiElementTab setOffsets(int x, int y) {

        this.x -= this.offsetX;
        this.y -= this.offsetY;
        this.offsetX = x;
        this.offsetY = y;
        this.x += this.offsetX;
        this.y += this.offsetY;

        return this;
    }

    @Override
    public GuiElementTab setPosition(int x, int y) {

        this.x = x + this.offsetX;
        this.y = y + this.offsetY;

        return this;
    }

    @Override
    public void update() {

        if (open && currentWidth < maxWidth) {
            currentWidth += tabExpandSpeed;
        } else if (!open && currentWidth > minWidth) {
            currentWidth -= tabExpandSpeed;
        }
        if (currentWidth > maxWidth) {
            currentWidth = maxWidth;
        } else if (currentWidth < minWidth) {
            currentWidth = minWidth;
        }
        if (open && currentHeight < maxHeight) {
            currentHeight += tabExpandSpeed;
        } else if (!open && currentHeight > minHeight) {
            currentHeight -= tabExpandSpeed;
        }
        if (currentHeight > maxHeight) {
            currentHeight = maxHeight;
        } else if (currentHeight < minHeight) {
            currentHeight = minHeight;
        }
        if (!fullyOpen && open && currentWidth == maxWidth && currentHeight == maxHeight) {
            setFullyOpen();
        }
    }

    protected void drawTabIcon(String iconName) {

        //this.guiWrapper.drawIcon(iconName, posXOffset(), this.y + 3, 1);
    }

    /**
     * Shortcut to correct for the proper X position.
     */
    protected int posX() {

        if (side == LEFT) {
            return this.x - currentWidth;
        }
        return this.x;
    }

    public boolean intersectsWith(int mouseX, int mouseY, int x, int y) {

        int shiftX = x + posX() + offsetX;
        int shiftY = y + this.y + offsetY;

        if (side == LEFT) {
            if (mouseX <= shiftX && mouseX >= shiftX - currentWidth && mouseY >= shiftY && mouseY <= shiftY + currentHeight) {
                return true;
            }
        } else if (mouseX >= shiftX && mouseX <= shiftX + currentWidth && mouseY >= shiftY && mouseY <= shiftY + currentHeight) {
            return true;
        }
        return false;
    }

    public boolean isFullyOpened() {
        return fullyOpen;
    }

    public void setFullyOpen() {

        open = true;
        currentWidth = maxWidth;
        currentHeight = maxHeight;
        fullyOpen = true;

        updateElements();
    }

    public void toggleOpen() {

        if (open) {
            open = false;
            if (side == LEFT) {
                //TabTracker.setOpenedLeftTab(null);
            } else {
                //TabTracker.setOpenedRightTab(null);
            }
            fullyOpen = false;
        } else {
            open = true;
            if (side == LEFT) {
                //TabTracker.setOpenedLeftTab(this.getClass());
            } else {
                //TabTracker.setOpenedRightTab(this.getClass());
            }
        }

        updateElements();
    }

    @Override
    public void mousePressed(int mouseX, int mouseY, int mouseButton, int x, int y) {
        super.mousePressed(mouseX, mouseY, mouseButton, x, y);
        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.mousePressed(mouseX, mouseY, mouseButton, x, y);
        }

        if(mouseButton == 0){
            if(intersectsWith(mouseX, mouseY, x, y)){
                toggleOpen();
            }
        }
    }

    public void updateElements(){
        Iterator<GuiElement> itr = guiElements.iterator();
        while (itr.hasNext()) {
            GuiElement p = itr.next();
            p.update();
        }
    }
}
