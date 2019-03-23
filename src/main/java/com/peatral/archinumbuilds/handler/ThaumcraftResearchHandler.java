package com.peatral.archinumbuilds.handler;

import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.ArchinumBuilds;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ThaumcraftResearchHandler {
	public static ResearchCategories archinumCategory;
	public static ResearchItem archinumArchinumCluster;
	public static ResearchItem archinumKorosiumCluster;
	public static ResearchItem archinumStarCluster;
	public static ResearchItem archinumDragonCluster;
	
	public static ResearchPage archinumArchinumClusterPage1;
	public static ResearchPage archinumArchinumClusterPage2;
	
	public static ResearchPage archinumKorosiumClusterPage1;
	public static ResearchPage archinumKorosiumClusterPage2;
	
	public static ResearchPage archinumStarClusterPage1;
	public static ResearchPage archinumStarClusterPage2;
	
	public static ResearchPage archinumDragonClusterPage1;
	public static ResearchPage archinumDragonClusterPage2;
	
	public static void mainRegistry(){
		researchCategories();
		researchPages();
		researchItems();
		
	}
	
	public static void researchCategories(){
		ResearchCategories.registerCategory("abCategory", new ResourceLocation(ArchinumBuilds.MODID, "textures/items/ArchinumIngot.png"), new ResourceLocation(ArchinumBuilds.MODID, "textures/gui/researchBack.png"));
	}
	
	public static void researchPages(){
		archinumArchinumClusterPage1 = new ResearchPage(StatCollector.translateToLocal("tc.research_page.abArchinumClusters.text1"));
		archinumArchinumClusterPage2 = new ResearchPage(ThaumcraftApi.getCrucibleRecipe(new ItemStack(ABItems.Cluster, 2, 0)));
		
		/*archinumKorosiumClusterPage1 = new ResearchPage(StatCollector.translateToLocal("tc.research_page.abKorosiumClusters.text1"));
		archinumKorosiumClusterPage1 = new ResearchPage(ThaumcraftApi.getCrucibleRecipe(new ItemStack(ModItems.Cluster, 2, 1)));
		
		archinumStarClusterPage1 = new ResearchPage(StatCollector.translateToLocal("tc.research_page.abStarClusters.text1"));
		archinumStarClusterPage2 = new ResearchPage(ThaumcraftApi.getCrucibleRecipe(new ItemStack(ModItems.Cluster, 2, 2)));
		
		archinumDragonClusterPage1 = new ResearchPage(StatCollector.translateToLocal("tc.research_page.abDragonClusters.text1"));
		archinumDragonClusterPage2 = new ResearchPage(ThaumcraftApi.getCrucibleRecipe(new ItemStack(ModItems.Cluster, 2, 3)));
		*/
	}
	
	public static void researchItems(){
		archinumArchinumCluster = new ResearchItem("abArchinumClusters", "abCategory", new AspectList(), -3, 0, 4, new ItemStack(ABItems.Cluster, 1, 0)).registerResearchItem().setParents("TRANSIRON").setPages(new ResearchPage[]{archinumArchinumClusterPage1, archinumArchinumClusterPage2}).setConcealed().setHidden().setSecondary();
		/*archinumKorosiumCluster = new ResearchItem("abKorosiumClusters", "abCategory", new AspectList(), 3, 0, 4, new ItemStack(ModItems.Cluster, 1, 1)).registerResearchItem().setParents("TRANSIRON").setPages(new ResearchPage[]{archinumKorosiumClusterPage1, archinumKorosiumClusterPage2}).setConcealed().setHidden().setSecondary();
		archinumStarCluster = new ResearchItem("abStarClusters", "abCategory", new AspectList(), 0, -3, 4, new ItemStack(ModItems.Cluster, 1, 2)).registerResearchItem().setParents("abArchinumClusters","abKorosiumClusters").setPages(new ResearchPage[]{archinumStarClusterPage1, archinumStarClusterPage2}).setConcealed().setHidden().setSecondary();
		archinumDragonCluster = new ResearchItem("abDragonClusters", "abCategory", new AspectList(), 0, 3, 4, new ItemStack(ModItems.Cluster, 1, 3)).registerResearchItem().setParents("abArchinumClusters","abKorosiumClusters").setPages(new ResearchPage[]{archinumDragonClusterPage1, archinumDragonClusterPage2}).setConcealed().setHidden().setSecondary();
		*/
	}
}
