package com.peatral.archinumbuilds;

import com.peatral.archinumbuilds.core.Proxy;
import com.peatral.archinumbuilds.block.ABBlocks;
import com.peatral.archinumbuilds.entity.ABEntities;
import com.peatral.archinumbuilds.gases.ABGases;
import com.peatral.archinumbuilds.handler.*;
import com.peatral.archinumbuilds.recipes.ABRecipes;
import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.tileentity.ABTileEntities;
import com.peatral.archinumbuilds.world.ABWorld;
import com.peatral.archinumbuilds.world.biome.ABBiomeRegistry;
import com.peatral.archinumbuilds.creativetabs.ABCreativeTabs;
import com.peatral.archinumbuilds.util.KeyBindings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ArchinumBuilds.MODID, name = ArchinumBuilds.MODNAME, version = ArchinumBuilds.VERSION, dependencies = ArchinumBuilds.DEPENDENCIES, guiFactory = ArchinumBuilds.GUI_FACTORY_CLASS, canBeDeactivated = ArchinumBuilds.CANBEDEACTIVATED)
public class ArchinumBuilds {

    public static final String MODID = "archinumbuilds";
    public static final String MODNAME = "Archinum Builds";
    public static final String VERSION = "0.0.1";
    public static final String DEPENDENCIES = "required-after:Mekanism";
    public static final String GUI_FACTORY_CLASS = "com.peatral.archinumbuilds.client.GUI.GuiFactory";
    public static final boolean CANBEDEACTIVATED = true;

    public static boolean thaumcraftInstalled = false;

    @Mod.Instance
    public static ArchinumBuilds instance;

    @SidedProxy(clientSide = "com.peatral.archinumbuilds.core.ProxyClient", serverSide = "com.peatral.archinumbuilds.core.Proxy")
    public static Proxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        String configDir = e.getModConfigurationDirectory().toString();
        ConfigurationHandler.mainRegistry(configDir);
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());


        ABCreativeTabs.mainRegistry();
        ABTileEntities.mainRegistry();
        ABEntities.mainRegistry();
        ABItems.mainRegistry();
        ABBlocks.mainRegistry();
        ABGases.mainRegistry();
        ABEntities.mainRegistry();

        ABBiomeRegistry.mainRegistry();

        ABWorld.mainRegistry();

        proxy.registerTileEntity();

    }

    @EventHandler
    public static void init(FMLInitializationEvent e) {

        proxy.registerNetworkStuff();
        proxy.registerRenderers();
        ABRecipes.mainRegistry();
        KeyBindings.mainRegistry();

        FMLCommonHandler.instance().bus().register(new KeyHandler());

        EventHandlerCommon handler = new EventHandlerCommon();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);

    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent e) {
        BiomeDictionary.registerAllBiomes();


        thaumcraftInstalled = Loader.isModLoaded("Thaumcraft");

        MinecraftForge.EVENT_BUS.register(new GUIHandler());
        if (thaumcraftInstalled == true) {
            ThaumcraftResearchHandler.mainRegistry();
        }
    }
}