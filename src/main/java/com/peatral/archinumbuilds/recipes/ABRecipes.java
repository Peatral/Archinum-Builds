package com.peatral.archinumbuilds.recipes;

import com.peatral.archinumbuilds.block.ABBlocks;
import com.peatral.archinumbuilds.gases.ABGases;
import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.Resource;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import mekanism.api.gas.GasStack;
import mekanism.api.infuse.InfuseObject;
import mekanism.api.infuse.InfuseRegistry;
import mekanism.api.infuse.InfuseType;
import mekanism.api.recipe.RecipeHelper;
import mekanism.common.MekanismItems;
import mekanism.common.item.ItemIngot;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ABRecipes {
    public static InfuseType Diamond;
    public static InfuseType RefinedArchinum;
    public static InfuseType Iron;
    public static InfuseType Gold;

    public static void mainRegistry() {
        recipesCrafting();
        recipesSmelting();

        recipesFractionatingColumn();

        recipesMekanism();

        //if(core.thaumcraftInstalled == true){
        recipesThaumcraft();
        //}
    }

    public static void recipesFractionatingColumn() {
        ABRecipeHelper.addFractionatingRecipe(
                new ItemStack(Item.getItemFromBlock(ABBlocks.ore), 1, 0),
                new ItemStack[]{
                        new ItemStack(ABItems.Shard, 1, 0),
                        new ItemStack(ABItems.Shard, 1, 1),
                        new ItemStack(ABItems.Shard, 1, 2)
                }
        );

        ABRecipeHelper.addFractionatingRecipe(
                Blocks.gold_ore,
                new ItemStack[]{
                    new ItemStack(Items.gold_nugget),
                    new ItemStack(MekanismItems.Ingot, 1, ItemIngot.en_USNames.length - 2),
                    new ItemStack(Item.getItemFromBlock(Blocks.grass))
                }
        );
    }

    public static void recipesCrafting() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ABItems.ControlCircuit, 1, 0), "ABA", 'A', "alloyArchinum", 'B', "circuitUltimate"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ABItems.teslaTablet, 1, 0), "ABA", "CDC", "ABA", 'A', new ItemStack(ABItems.Dust, 1, 1), 'B', new ItemStack(ABItems.Ingot, 1, 0), 'C', new ItemStack(ABItems.alloyArchinum), 'D', "battery"));
        //GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dustArchinumRefined, 1), new Object[] {"dustRefinedObsidian","dustArchinum"}));

        GameRegistry.addRecipe(new ItemStack(ABItems.thorsHammer, 1), "ACA", "ABA", " D ", 'A', new ItemStack(ABItems.Ingot, 1, 2), 'B', new ItemStack(ABItems.teslaTablet), 'C', new ItemStack(ABItems.ControlCircuit, 1, 0), 'D', new ItemStack(Items.stick, 1));
        GameRegistry.addRecipe(new ItemStack(ABItems.thorsHelmet, 1), "ABA", "A A", 'A', new ItemStack(ABItems.Ingot, 1, 3), 'B', new ItemStack(ABItems.ControlCircuit, 1, 0));
        GameRegistry.addRecipe(new ItemStack(ABItems.excalibur, 1), " A ", " C ", " D ", 'A', new ItemStack(ABItems.ingotArchinumRefined), 'C', new ItemStack(ABItems.ControlCircuit, 1, 0), 'D', new ItemStack(Items.stick, 1));

        for (int i = 0; i < Resource.values().length; i++) {
            GameRegistry.addRecipe(new ItemStack(ABBlocks.oreBlock, 1, i), "AAA", "AAA", "AAA", 'A', new ItemStack(ABItems.Ingot, 1, i));
        }
    }

    public static void recipesSmelting() {
        for (int i = 0; i < Resource.values().length; i++) {
            GameRegistry.addSmelting(new ItemStack(ABBlocks.ore, 1, i), new ItemStack(ABItems.Ingot, 1, i), 20.0F);
            GameRegistry.addSmelting(new ItemStack(ABItems.Dust, 1, i), new ItemStack(ABItems.Ingot, 1, i), 20.0F);
            GameRegistry.addSmelting(new ItemStack(ABItems.Cluster, 1, i), new ItemStack(ABItems.Ingot, 2, i), 20.0F);
        }

        GameRegistry.addSmelting(ABItems.dustArchinumRefined, new ItemStack(ABItems.ingotArchinumRefined), 0.0F);

        //for(ItemStack osmium : OreDictionary.getOres("ingotOsmium")){GameRegistry.addSmelting(osmium, new ItemStack(ModItems.alloyUltimateReinforced), 0);}
        //for(ItemStack ore : OreDictionary.getOres("ingotArchinum")){GameRegistry.addSmelting(ore, new ItemStack(ModItems.dustKorosium), 0);}

    }

    public static void recipesMekanism() {
        recipesInfuser();
        recipesCrusher();
        recipesOsmiumCompressor();
        recipesCombiner();
        recipesEnrichmentChamber();
        recipesPurificationChamber();
        recipesChemicalInjectionChamber();

    }

    public static void recipesInfuser() {
        Diamond = InfuseRegistry.get("Diamond");
        //InfuseTypes
        RefinedArchinum = new InfuseType("RefinedArchinum", ArchinumBuilds.MODID + ":infuse/RefinedArchinum").setUnlocalizedName("archinumRefined");
        InfuseRegistry.registerInfuseType(RefinedArchinum);
        InfuseRegistry.registerInfuseObject(new ItemStack(ABItems.dustArchinumRefined), new InfuseObject(RefinedArchinum, 10));

        Iron = new InfuseType("Iron", ArchinumBuilds.MODID + ":infuse/Iron").setUnlocalizedName("iron");
        InfuseRegistry.registerInfuseType(Iron);
        for (ItemStack dustIron : OreDictionary.getOres("dustIron")) {
            InfuseRegistry.registerInfuseObject(dustIron, new InfuseObject(Iron, 10));
        }

        Gold = new InfuseType("Gold", ArchinumBuilds.MODID + ":infuse/Gold").setUnlocalizedName("gold");
        InfuseRegistry.registerInfuseType(Gold);
        for (ItemStack dustGold : OreDictionary.getOres("dustGold")) {
            InfuseRegistry.registerInfuseObject(dustGold, new InfuseObject(Gold, 10));
        }

        //Recipes
        NBTTagCompound alloyUltimateArchinumInfuser = new NBTTagCompound();
        for (ItemStack circuit : OreDictionary.getOres("circuitUltimate")) {
            alloyUltimateArchinumInfuser.setTag("input", circuit.writeToNBT(new NBTTagCompound()));
        }
        alloyUltimateArchinumInfuser.setString("infuseType", "RefinedArchinum");
        alloyUltimateArchinumInfuser.setInteger("infuseAmount", 10);
        alloyUltimateArchinumInfuser.setTag("output", new ItemStack(ABItems.alloyArchinum).writeToNBT(new NBTTagCompound()));
        FMLInterModComms.sendMessage("Mekanism", "MetallurgicInfuserRecipe", alloyUltimateArchinumInfuser);

        RecipeHelper.addMetallurgicInfuserRecipe(Diamond, 10, new ItemStack(ABItems.Dust, 1, 0), new ItemStack(ABItems.dustArchinumRefined));

        NBTTagCompound dustKorosiumArchinumInfuser = new NBTTagCompound();
        dustKorosiumArchinumInfuser.setTag("input", new ItemStack(ABItems.Dust, 1, 0).writeToNBT(new NBTTagCompound()));
        dustKorosiumArchinumInfuser.setString("infuseType", "Iron");
        dustKorosiumArchinumInfuser.setInteger("infuseAmount", 10);
        dustKorosiumArchinumInfuser.setTag("output", new ItemStack(ABItems.Dust, 1, 1).writeToNBT(new NBTTagCompound()));
        FMLInterModComms.sendMessage("Mekanism", "MetallurgicInfuserRecipe", dustKorosiumArchinumInfuser);

        NBTTagCompound dustArchinumObsidianInfuser = new NBTTagCompound();
        dustArchinumObsidianInfuser.setTag("input", new ItemStack(ABItems.Dust, 1, 0).writeToNBT(new NBTTagCompound()));
        dustArchinumObsidianInfuser.setString("infuseType", "DIAMOND");
        dustArchinumObsidianInfuser.setInteger("infuseAmount", 10);
        dustArchinumObsidianInfuser.setTag("output", new ItemStack(ABItems.dustArchinumRefined).writeToNBT(new NBTTagCompound()));
        FMLInterModComms.sendMessage("Mekanism", "MetallurgicInfuserRecipe", dustArchinumObsidianInfuser);
    }

    public static void recipesCrusher() {
        for (int i = 0; i < Resource.values().length; i++) {
            NBTTagCompound ingotCrusher = new NBTTagCompound();
            ingotCrusher.setTag("input", new ItemStack(ABItems.Ingot, 1, i).writeToNBT(new NBTTagCompound()));
            ingotCrusher.setTag("output", new ItemStack(ABItems.Dust, 1, i).writeToNBT(new NBTTagCompound()));
            FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", ingotCrusher);

            NBTTagCompound clumpCrusher = new NBTTagCompound();
            clumpCrusher.setTag("input", new ItemStack(ABItems.Clump, 1, i).writeToNBT(new NBTTagCompound()));
            clumpCrusher.setTag("output", new ItemStack(ABItems.DirtyDust, 1, i).writeToNBT(new NBTTagCompound()));
            FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", clumpCrusher);
        }

        NBTTagCompound refArchinumIngotCrusher = new NBTTagCompound();
        refArchinumIngotCrusher.setTag("input", new ItemStack(ABItems.ingotArchinumRefined).writeToNBT(new NBTTagCompound()));
        refArchinumIngotCrusher.setTag("output", new ItemStack(ABItems.dustArchinumRefined).writeToNBT(new NBTTagCompound()));
        FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", refArchinumIngotCrusher);

    }

    public static void recipesOsmiumCompressor() {

    }

    public static void recipesCombiner() {
        for (int i = 0; i < Resource.values().length; i++) {
            RecipeHelper.addCombinerRecipe(new ItemStack(ABItems.Dust, 1, i), new ItemStack(ABBlocks.ore, 1, i));
        }
    }

    public static void recipesEnrichmentChamber() {
        for (int i = 0; i < Resource.values().length; i++) {
            RecipeHelper.addEnrichmentChamberRecipe(new ItemStack(ABBlocks.ore, 1, i), new ItemStack(ABItems.Dust, 2, i));
            RecipeHelper.addEnrichmentChamberRecipe(new ItemStack(ABItems.DirtyDust, 1, i), new ItemStack(ABItems.Dust, 1, i));
        }
    }

    public static void recipesPurificationChamber() {
        for (int i = 0; i < Resource.values().length; i++) {
            RecipeHelper.addPurificationChamberRecipe(new ItemStack(ABBlocks.ore, 1, i), new ItemStack(ABItems.Clump, 3, i));
            RecipeHelper.addPurificationChamberRecipe(new ItemStack(ABItems.Shard, 1, i), new ItemStack(ABItems.Clump, 1, i));
        }
    }

    public static void recipesChemicalInjectionChamber() {
        for (int i = 0; i < Resource.values().length; i++) {
            RecipeHelper.addChemicalInjectionChamberRecipe(new ItemStack(ABBlocks.ore, 1, i), "hydrogenChloride", new ItemStack(ABItems.Shard, 4, i));
            RecipeHelper.addChemicalInjectionChamberRecipe(new ItemStack(ABItems.Crystal, 1, i), "hydrogenChloride", new ItemStack(ABItems.Shard, 1, i));
        }
    }

    public static void recipesChemicalCrystallizer() {
        RecipeHelper.addChemicalCrystallizerRecipe(new GasStack(ABGases.Archinum, 10), new ItemStack(ABItems.Crystal, 1, 0));
    }

    //THAUMCRAFT
    public static void recipesThaumcraft() {
        for (int i = 0; i < 4; i++) {
            ThaumcraftApi.addCrucibleRecipe("ab" + Resource.values()[0].getName() + "Cluster", new ItemStack(ABItems.Cluster, 1, 0), new ItemStack(ABBlocks.ore, 1, 0), new AspectList().add(Aspect.METAL, 2).add(Aspect.ORDER, 2));
        }

    }
}
