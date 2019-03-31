package com.peatral.archinumbuilds.recipes;

import com.peatral.archinumbuilds.item.ABItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class ABRecipeHelper {

    public ABRecipeHelper() {
    }

    public static void addFractionatingRecipe(Block input, ItemStack[] output) {
        RecipesFractionatingColumn.fractionating().addFractionatingRecipe(input, output);
    }

    public static void addFractionatingRecipe(Item input, ItemStack[] output) {
        RecipesFractionatingColumn.fractionating().addFractionatingRecipe(input, output);
    }

    public static void addFractionatingRecipe(ItemStack input, ItemStack[] output) {
        RecipesFractionatingColumn.fractionating().addFractionatingRecipe(input, output);
    }

    public static void addStillRecipe(Block input, ItemStack output) {
        RecipesStill.destilling().addStillRecipe(input, output);
    }

    public static void addStillRecipe(Item input, ItemStack output) {
        RecipesStill.destilling().addStillRecipe(input, output);
    }

    public static void addStillRecipe(ItemStack input, ItemStack output) {
        RecipesStill.destilling().addStillRecipe(input, output);
    }

    public static void addStillRecipe(Block input, String smell) {

        RecipesStill.destilling().addStillRecipe(input, smell);
    }

    public static void addStillRecipe(Item input, String smell) {
        RecipesStill.destilling().addStillRecipe(input, smell);
    }

    public static void addStillRecipe(ItemStack input, String smell) {
        RecipesStill.destilling().addStillRecipe(input, smell);
    }
}
