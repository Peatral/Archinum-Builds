package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.item.ItemBlockOre;
import com.peatral.archinumbuilds.item.ItemBlockOreBlock;
import com.peatral.archinumbuilds.creativetabs.ABCreativeTabs;
import com.peatral.archinumbuilds.util.Resource;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ABBlocks {
	public static void mainRegistry() {
		initializeBlocks();
		registerBlocks();
	}
	
	public static Block ore;
	public static Block oreBlock;
	public static Block catalyst;
	public static Block catalystActive;

	public static Block fractionatingColumnBase;
	
	public static void initializeBlocks() {
		ore = new BlockOre(Material.iron, "BlockOre").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		oreBlock = new BlockOreBlock(Material.iron, "BlockOreBlock").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		
		catalyst = new BlockCatalyst(false).setBlockName("catalyst").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		catalystActive = new BlockCatalyst(true).setBlockName("catalystActive");

		fractionatingColumnBase = new BlockFractionatingColumnBase(Material.rock).setBlockName("fractionatingColumnBase").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(ore, ItemBlockOre.class, "BlockOre");
		GameRegistry.registerBlock(oreBlock, ItemBlockOreBlock.class, "BlockOreBlock");
		
		for(int i = 0; i < Resource.values().length; i++)
		{
			OreDictionary.registerOre("ore" + Resource.values()[i].getName(), new ItemStack(ore, 0, i));
			OreDictionary.registerOre("block" + Resource.values()[i].getName(), new ItemStack(oreBlock, 0, i));
		}
		
		GameRegistry.registerBlock(catalyst, catalyst.getUnlocalizedName());
		GameRegistry.registerBlock(catalystActive, catalystActive.getUnlocalizedName());

		GameRegistry.registerBlock(fractionatingColumnBase, fractionatingColumnBase.getUnlocalizedName());
	}
}
