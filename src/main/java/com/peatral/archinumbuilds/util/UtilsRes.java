package com.peatral.archinumbuilds.util;

import com.peatral.archinumbuilds.ArchinumBuilds;
import net.minecraft.util.ResourceLocation;

public class UtilsRes {
    public static ResourceLocation getRes(Type type, String name){
        return new ResourceLocation(ArchinumBuilds.MODID + ":" + type.getPrefix() + name);
    }

    public static enum Type{
        GUI_ELEMENT("textures/gui/elements"),
        GUI_CONTAINER("textures/gui/container");

        private String prefix;

        Type(String s) {
            this.prefix = s;
        }

        public String getPrefix() {
            return this.prefix + "/";
        }

    }
}
