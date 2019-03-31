package com.peatral.archinumbuilds.smells;

import com.peatral.archinumbuilds.smells.defaultsmells.SmellGarlic;
import com.peatral.archinumbuilds.smells.defaultsmells.SmellIncense;
import com.peatral.archinumbuilds.smells.defaultsmells.SmellSage;
import com.peatral.archinumbuilds.smells.defaultsmells.SmellSpice;

public class ABSmells {

    public static SmellType spice;
    public static SmellType garlic;
    public static SmellType sage;
    public static SmellType incense;

    public static void mainRegistry() {
        initializeSmells();
        registerSmells();
    }

    public static void initializeSmells() {
        spice = new SmellSpice();
        garlic = new SmellGarlic();
        sage = new SmellSage();
        incense = new SmellIncense();
    }

    public static void registerSmells() {
        SmellRegistry.registerSmellType(spice);
        SmellRegistry.registerSmellType(garlic);
        SmellRegistry.registerSmellType(sage);
        SmellRegistry.registerSmellType(incense);
    }
}
