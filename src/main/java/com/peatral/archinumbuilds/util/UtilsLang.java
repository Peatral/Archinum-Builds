package com.peatral.archinumbuilds.util;

import net.minecraft.util.StatCollector;

public class UtilsLang {

    public static String localize(String s) {
        return StatCollector.translateToLocal(s);
    }

}
