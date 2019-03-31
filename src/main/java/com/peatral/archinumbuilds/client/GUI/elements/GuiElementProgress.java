package com.peatral.archinumbuilds.client.GUI.elements;

import com.peatral.archinumbuilds.client.GUI.IGuiWrapper;
import com.peatral.archinumbuilds.util.UtilsRes;
import net.minecraft.util.ResourceLocation;

public class GuiElementProgress extends GuiElement {

    public static int RIGHT = 0;
    public static int LEFT = 1;
    public static int UP = 2;
    public static int DOWN = 3;
    public static int BUBBLE = 4;

    private int type;
    private IProgressInfoHandler progressHandler;

    public GuiElementProgress(IProgressInfoHandler progressHandler, IGuiWrapper guiWrapper, int type, ResourceLocation def) {
        super(guiWrapper, def, UtilsRes.getRes(UtilsRes.Type.GUI_ELEMENT, "progress.png"));
        this.progressHandler = progressHandler;
        this.type = type;
    }

    @Override
    public void renderBg(int x, int y) {
        this.mc.getTextureManager().bindTexture(this.res);

        if(this.type == RIGHT){
            int progress = (int) Math.ceil(progressHandler.getProgress()*23);

            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 64, 0, 23, 13);
            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 96, 0, progress, 13);
        } else if(this.type == LEFT){
            int progress = (int) Math.ceil(progressHandler.getProgress()*23);

            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 64, 16, 23, 13);
            this.guiWrapper.drawTexturedRect(x + this.x + 23 - progress, y + this.y, 96 + 23 - progress, 16, progress, 13);
        } else if(this.type == UP){
            int progress = (int) Math.ceil(progressHandler.getProgress()*27);

            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 32, 0, 7, 27);
            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y + 27 - progress, 48, 0 + 27 - progress, 7, progress);
        } else if(this.type == DOWN){
            int progress = (int) Math.ceil(progressHandler.getProgress()*27);

            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 0, 0, 7, 27);
            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 16, 0, 7, progress);
        } else if(this.type == BUBBLE){
            int progress = (int) Math.ceil(progressHandler.getProgress()*29);

            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y, 0, 32, 12, 29);
            this.guiWrapper.drawTexturedRect(x + this.x, y + this.y + 29 - progress, 16, 32 + 29 - progress, 12, progress);
        }

        this.mc.getTextureManager().bindTexture(this.def);
    }

    public abstract static class IProgressInfoHandler {
        public IProgressInfoHandler() {
        }

        public abstract double getProgress();
    }
}
