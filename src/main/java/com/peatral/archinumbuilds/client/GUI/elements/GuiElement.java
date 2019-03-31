package com.peatral.archinumbuilds.client.GUI.elements;

import com.peatral.archinumbuilds.client.GUI.IGuiWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiElement {
    public int x = 0, y = 0;
    public Minecraft mc = Minecraft.getMinecraft();
    public IGuiWrapper guiWrapper;

    public ResourceLocation def, res;

    public GuiElement(IGuiWrapper guiWrapper, ResourceLocation def, ResourceLocation res) {

        this.guiWrapper = guiWrapper;
        this.def = def;
        this.res = res;
    }

    public GuiElement setPosition(int x, int y){
        this.x = x;
        this.y = y;

        return this;
    }

    public void renderBg(int x, int y){
    }

    public void renderFg(int x, int y){
    }

    public void update(){
    }

    public void mousePressed(int mouseX, int mouseY, int mouseButton, int x, int y){
    }
}
