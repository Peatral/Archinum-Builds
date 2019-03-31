package com.peatral.archinumbuilds.client.GUI.elements;

import com.peatral.archinumbuilds.client.GUI.IGuiWrapper;
import com.peatral.archinumbuilds.util.UtilsRes;
import net.minecraft.util.ResourceLocation;

public class GuiElementBurnTime extends GuiElement {

    public IBurnTimeInfoHandler burnTimeHandler;

    public GuiElementBurnTime(IBurnTimeInfoHandler tempHandler, IGuiWrapper container, ResourceLocation def) {
        super(container, def, UtilsRes.getRes(UtilsRes.Type.GUI_ELEMENT, "burn_time.png"));
        this.burnTimeHandler = tempHandler;
    }

    public void renderBg(int x, int y) {
        this.mc.getTextureManager().bindTexture(this.res);

        int burntime = (int) Math.ceil(this.burnTimeHandler.getBurnTime() * 14);

        this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 0, 0, 14, 14);
        this.guiWrapper.drawTexturedRect(x + this.x, y + this.y + 14 - burntime, 16, 0 + 14 - burntime, 14, burntime);

        this.mc.getTextureManager().bindTexture(this.def);
    }

    public abstract static class IBurnTimeInfoHandler{
        public IBurnTimeInfoHandler() {
        }

        public abstract double getBurnTime();
    }
}
