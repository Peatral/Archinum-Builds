package com.peatral.archinumbuilds.smells;

import java.util.HashMap;
import java.util.Map;

public class SmellRegistry {
    private static Map<String, SmellType> smellTypes = new HashMap();

    public SmellRegistry() {
    }

    public static void registerSmellType(SmellType smell) {
        if (!smellTypes.containsKey(smell.name)) {
            smellTypes.put(smell.name, smell);
        }
    }

    public static SmellType get(String name) {
        return name.equals("null") ? null : (SmellType) smellTypes.get(name);
    }

    public static boolean contains(String name) {
        return get(name) != null;
    }


    public static final Map<String, SmellType> getSmellMap() {
        return smellTypes;
    }

}
