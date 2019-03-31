package com.peatral.archinumbuilds.creativetabs;

import com.peatral.archinumbuilds.block.ABBlocks;
import com.peatral.archinumbuilds.item.ABItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ABCreativeTabs {
    public static CreativeTabs tabArchinumBuilds;
    public static CreativeTabs tabFragrance;
    public static CreativeTabs tabCombat;

    public static void mainRegistry() {
        initializeTabs();
        registerTabs();
    }

    public static void initializeTabs() {
        tabArchinumBuilds = new ABTabs("tabArchinumBuilds") {
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(ABBlocks.ore);
            }
        };
        tabFragrance = new ABTabs("tabABFragrance") {
            @Override
            public Item getTabIconItem() {
                return ABItems.fragranceFlask;
            }
        };
        tabCombat = new ABTabs("tabABCombat") {
            @Override
            public Item getTabIconItem() {
                return ABItems.thorsHelmet;
            }
        };
    }

    public static void registerTabs() {
    }
}
