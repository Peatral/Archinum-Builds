package com.peatral.archinumbuilds.client.GUI.elements;

import com.peatral.archinumbuilds.client.GUI.IGuiWrapper;
import com.peatral.archinumbuilds.util.UtilsRes;
import net.minecraft.util.ResourceLocation;

public class GuiElementTempBar extends GuiElement {

    public static final int size = 58;

    public ITempInfoHandler tempHandler;

    public GuiElementTempBar(ITempInfoHandler tempHandler, IGuiWrapper container, ResourceLocation def) {
        super(container, def, UtilsRes.getRes(UtilsRes.Type.GUI_ELEMENT, "temp_bar.png"));
        this.tempHandler = tempHandler;
    }

    public void renderBg(int x, int y) {
        this.mc.getTextureManager().bindTexture(this.res);

        int temp = (int) Math.ceil(this.tempHandler.getTemp() * size);

        this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 0, 0, 8, 60);
        this.guiWrapper.drawTexturedRect(x + this.x + 1, y + this.y + 1 + 58 - temp, 9, 1 + 58 - temp, 6, temp);

        this.mc.getTextureManager().bindTexture(this.def);
    }

    public abstract static class ITempInfoHandler{
        public ITempInfoHandler() {
        }

        public abstract double getTemp();
    }
}
