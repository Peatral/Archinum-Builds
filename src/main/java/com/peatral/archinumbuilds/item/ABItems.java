package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.creativetabs.ABCreativeTabs;
import com.peatral.archinumbuilds.util.Resource;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class ABItems {
	public static void mainRegistry() {
		initializeItems();
		registerItems();
	}
	
	public static Item dustArchinumRefined;
	public static Item ingotArchinumRefined;
	
	public static Item alloyArchinum;
	public static Item teslaTablet;
	public static Item thorsHammer;
	public static Item excalibur;
	public static Item bladeOlympus;
	
	public static Item thorsHelmet;
	public static Item threedmg;
	
	public static Item threedmgSword;
	
	public static Item gun;
	
	public static Item Dust;
	public static Item Ingot;
	public static Item Clump;
	public static Item DirtyDust;
	public static Item Shard;
	public static Item Crystal;
	public static Item Cluster;
	
	public static Item ControlCircuit;	

	public static void initializeItems() {
		ArmorMaterial armor = EnumHelper.addArmorMaterial("armor", 0, new int[]{3, 8, 6, 3}, 0);
		ToolMaterial legend = EnumHelper.addToolMaterial("archinum", 3, 5000, 15.0F, 7, 22);
		
		dustArchinumRefined = new Item().setUnlocalizedName("dustArchinumRefined").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":RefinedArchinumDust");
		ingotArchinumRefined = new Item().setUnlocalizedName("ingotArchinumRefined").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":RefinedArchinumIngot");
		alloyArchinum = new Item().setUnlocalizedName("alloyArchinum").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":ArchinumAlloy");
		teslaTablet = new ItemTeslaTablet(5000000).setUnlocalizedName("teslaTablet").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		thorsHammer = new ItemThorsHammer(100000000).setUnlocalizedName("thorsHammer").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		excalibur = new ItemExcalibur(legend).setUnlocalizedName("excalibur").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":Excalibur");
		bladeOlympus = new ItemBladeOfOlympus(legend, 2000000000).setUnlocalizedName("bladeofolympus").setCreativeTab(ABCreativeTabs.tabArchinumBuilds);
		
		thorsHelmet = new ItemArmorThorsHelmet(0).setUnlocalizedName("thorsHelmet").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":thorsHelmet");
		threedmg = new ItemArmor3DMG(2).setUnlocalizedName("3DMG").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID  + ":3DMG");
		
		threedmgSword = new Item3DMGSword(legend).setUnlocalizedName("3DMGSword").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setTextureName(ArchinumBuilds.MODID + ":3DMGSword");
		
		gun = new Item().setUnlocalizedName("gun").setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setFull3D().setTextureName(ArchinumBuilds.MODID + ":Gun").setMaxStackSize(1);
		
		Dust = new ItemDust().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Ingot = new ItemIngot().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Clump = new ItemClump().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		DirtyDust = new ItemDirtyDust().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Shard = new ItemShard().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Crystal = new ItemCrystal().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Cluster = new ItemCluster().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		
		ControlCircuit = new ItemCircuit().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
	}

	public static void registerItems() {
		
		GameRegistry.registerItem(Dust, "Dust");
		GameRegistry.registerItem(Ingot, "Ingot");
		GameRegistry.registerItem(Clump, "Clump");
		GameRegistry.registerItem(DirtyDust, "DirtyDust");
		GameRegistry.registerItem(Shard, "Shard");
		GameRegistry.registerItem(Crystal, "Crystal");
		
		GameRegistry.registerItem(Cluster, "Cluster");
		
			
		GameRegistry.registerItem(ingotArchinumRefined, "ingotArchinumRefined");
		OreDictionary.registerOre("ingotRefinedArchinum", new ItemStack(ingotArchinumRefined));
		GameRegistry.registerItem(dustArchinumRefined, "dustArchinumRefined");
		OreDictionary.registerOre("dustRefinedArchinum", new ItemStack(dustArchinumRefined));
			
		GameRegistry.registerItem(alloyArchinum, "alloyArchinum");
		OreDictionary.registerOre("alloyArchinum", new ItemStack(alloyArchinum));
			
		GameRegistry.registerItem(teslaTablet, "teslaTablet");
		GameRegistry.registerItem(thorsHammer, "thorsHammer");
		GameRegistry.registerItem(excalibur, "excalibur");
		GameRegistry.registerItem(bladeOlympus, "bladeofolympus");
		
		GameRegistry.registerItem(thorsHelmet, "thorsHelmet");
		GameRegistry.registerItem(threedmg, "3DMG");
		
		GameRegistry.registerItem(threedmgSword, "3DMGSword");
		
		GameRegistry.registerItem(gun, "gun");
		
		GameRegistry.registerItem(ControlCircuit, "ControlCircuit");
		OreDictionary.registerOre("archinumCircuit", new ItemStack(ControlCircuit, 0, 0));
		
		for(int i = 0; i < Resource.values().length; i++)
		{
			OreDictionary.registerOre("dust" + Resource.values()[i].getName(), new ItemStack(Dust, 0, i));
			OreDictionary.registerOre("ingot" + Resource.values()[i].getName(), new ItemStack(Ingot, 0, i));
			OreDictionary.registerOre("clump" + Resource.values()[i].getName(), new ItemStack(Clump, 0, i));
			OreDictionary.registerOre("dustDirty" + Resource.values()[i].getName(), new ItemStack(DirtyDust, 0, i));
			OreDictionary.registerOre("shard" + Resource.values()[i].getName(), new ItemStack(Shard, 0, i));
			OreDictionary.registerOre("crystal" + Resource.values()[i].getName(), new ItemStack(Crystal, 0, i));
			
			OreDictionary.registerOre("cluster" + Resource.values()[i].getName(), new ItemStack(Cluster, 0, i));	
		}
		
		
		
		
	}
}
