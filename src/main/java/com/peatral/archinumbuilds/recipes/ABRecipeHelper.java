package com.peatral.archinumbuilds.recipes;

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
}
