package com.peatral.archinumbuilds.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RecipesFractionatingColumn {
    private static final RecipesFractionatingColumn fractionatingBase = new RecipesFractionatingColumn();

    /**
     * The list of fractionating results.
     */
    private Map fractionatingList = new HashMap();

    private RecipesFractionatingColumn() {
    }

    /**
     * Used to call methods addFractionating and getFractionatingResult.
     */
    public static RecipesFractionatingColumn fractionating() {
        return fractionatingBase;
    }

    public void addFractionatingRecipe(Block input, ItemStack[] result) {
        this.addFractionatingRecipe(Item.getItemFromBlock(input), result);
    }

    public void addFractionatingRecipe(Item input, ItemStack[] result) {
        this.addFractionatingRecipe(new ItemStack(input, 1, 32767), result);
    }

    public void addFractionatingRecipe(ItemStack input, ItemStack[] result) {
        this.fractionatingList.put(input, result);
    }

    /**
     * Returns the fractionating result of an item.
     */
    public ItemStack[] getFractionatingResult(ItemStack itemStack) {
        Iterator iterator = this.fractionatingList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Entry) iterator.next();
        }
        while (!this.func_151397_a(itemStack, (ItemStack) entry.getKey()));

        return (ItemStack[]) entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getFractionatingList() {
        return this.fractionatingList;
    }
}
