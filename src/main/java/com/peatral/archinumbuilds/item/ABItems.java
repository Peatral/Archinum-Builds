package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.creativetabs.ABCreativeTabs;
import com.peatral.archinumbuilds.util.ResourceOres;

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

	public static Item resign;
	
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

	public static Item fragrancePure;
	public static Item fragranceFlaskEmpty;
	public static Item fragranceFlask;

	public static Item ControlCircuit;	

	public static void initializeItems() {
		ArmorMaterial armor = EnumHelper.addArmorMaterial("armor", 0, new int[]{3, 8, 6, 3}, 0);
		ToolMaterial legend = EnumHelper.addToolMaterial("archinum", 3, 5000, 15.0F, 7, 22);
		
		dustArchinumRefined = new Item().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setUnlocalizedName("dustArchinumRefined").setTextureName(ArchinumBuilds.MODID + ":RefinedArchinumDust");
		ingotArchinumRefined = new Item().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setUnlocalizedName("ingotArchinumRefined").setTextureName(ArchinumBuilds.MODID + ":RefinedArchinumIngot");
		alloyArchinum = new Item().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setUnlocalizedName("alloyArchinum").setTextureName(ArchinumBuilds.MODID + ":ArchinumAlloy");
		teslaTablet = new ItemTeslaTablet(5000000).setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setUnlocalizedName("teslaTablet");
		thorsHammer = new ItemThorsHammer(100000000).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("thorsHammer");
		excalibur = new ItemExcalibur(legend).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("excalibur").setTextureName(ArchinumBuilds.MODID + ":Excalibur");
		bladeOlympus = new ItemBladeOfOlympus(legend, 2000000000).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("bladeofolympus");
		
		thorsHelmet = new ItemArmorThorsHelmet(0).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("thorsHelmet").setTextureName(ArchinumBuilds.MODID + ":thorsHelmet");
		threedmg = new ItemArmor3DMG(2).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("3DMG").setTextureName(ArchinumBuilds.MODID  + ":3DMG");
		
		threedmgSword = new Item3DMGSword(legend).setCreativeTab(ABCreativeTabs.tabCombat).setUnlocalizedName("3DMGSword").setTextureName(ArchinumBuilds.MODID + ":3DMGSword");
		
		gun = new Item().setUnlocalizedName("gun").setCreativeTab(ABCreativeTabs.tabCombat).setFull3D().setTextureName(ArchinumBuilds.MODID + ":Gun").setMaxStackSize(1);
		
		Dust = new ItemDust().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Ingot = new ItemIngot().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Clump = new ItemClump().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		DirtyDust = new ItemDirtyDust().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Shard = new ItemShard().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Crystal = new ItemCrystal().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		Cluster = new ItemCluster().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		
		ControlCircuit = new ItemCircuit().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);
		resign = new ItemResin().setCreativeTab(ABCreativeTabs.tabArchinumBuilds).setHasSubtypes(true);

		fragrancePure = new ItemFragrancePure().setCreativeTab(ABCreativeTabs.tabFragrance);
		fragranceFlaskEmpty = new ItemFragranceFlaskEmpty().setCreativeTab(ABCreativeTabs.tabFragrance).setUnlocalizedName("fragranceFlaskEmpty").setTextureName(ArchinumBuilds.MODID + ":fragrance_flask_flask");
		fragranceFlask = new ItemFragranceFlask().setCreativeTab(ABCreativeTabs.tabFragrance).setUnlocalizedName("fragranceFlask");

	}

	public static void registerItems() {
		
		GameRegistry.registerItem(Dust, "dust");
		GameRegistry.registerItem(Ingot, "ingot");
		GameRegistry.registerItem(Clump, "clump");
		GameRegistry.registerItem(DirtyDust, "dirtyDust");
		GameRegistry.registerItem(Shard, "shard");
		GameRegistry.registerItem(Crystal, "crystal");
		
		GameRegistry.registerItem(Cluster, "cluster");

		GameRegistry.registerItem(fragrancePure, "fragrancePure");
		GameRegistry.registerItem(fragranceFlaskEmpty, "fragranceFlaskEmpty");
		GameRegistry.registerItem(fragranceFlask, "fragranceFlask");

		GameRegistry.registerItem(resign, "resign");
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
		
		for(int i = 0; i < ResourceOres.values().length; i++)
		{
			OreDictionary.registerOre("dust" + ResourceOres.values()[i].getName(), new ItemStack(Dust, 0, i));
			OreDictionary.registerOre("ingot" + ResourceOres.values()[i].getName(), new ItemStack(Ingot, 0, i));
			OreDictionary.registerOre("clump" + ResourceOres.values()[i].getName(), new ItemStack(Clump, 0, i));
			OreDictionary.registerOre("dustDirty" + ResourceOres.values()[i].getName(), new ItemStack(DirtyDust, 0, i));
			OreDictionary.registerOre("shard" + ResourceOres.values()[i].getName(), new ItemStack(Shard, 0, i));
			OreDictionary.registerOre("crystal" + ResourceOres.values()[i].getName(), new ItemStack(Crystal, 0, i));
			
			OreDictionary.registerOre("cluster" + ResourceOres.values()[i].getName(), new ItemStack(Cluster, 0, i));
		}
	}
}
