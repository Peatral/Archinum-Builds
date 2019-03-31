package com.peatral.archinumbuilds.recipes;

import com.peatral.archinumbuilds.item.ABItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RecipesStill {
    private static final RecipesStill destillingBase = new RecipesStill();

    /**
     * The list of fractionating results.
     */
    private Map destillingList = new HashMap();

    private RecipesStill() {
    }

    /**
     * Used to call methods addFractionating and getFractionatingResult.
     */
    public static RecipesStill destilling() {
        return destillingBase;
    }

    public void addStillRecipe(Block input, String smell) {
        this.addStillRecipe(Item.getItemFromBlock(input), smell);
    }

    public void addStillRecipe(Item input, String smell) {
        this.addStillRecipe(new ItemStack(input), smell);
    }

    public void addStillRecipe(ItemStack input, String smell) {
        ItemStack stack = new ItemStack(ABItems.fragrancePure);
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("Smell", smell);
        stack.stackTagCompound = tagCompound;
        this.addStillRecipe(input, stack);
    }

    public void addStillRecipe(Block input, ItemStack result) {
        this.addStillRecipe(Item.getItemFromBlock(input), result);
    }

    public void addStillRecipe(Item input, ItemStack result) {
        this.addStillRecipe(new ItemStack(input, 1, 32767), result);
    }

    public void addStillRecipe(ItemStack input, ItemStack result) {
        this.destillingList.put(input, result);
    }

    /**
     * Returns the fractionating result of an item.
     */
    public ItemStack getStillResult(ItemStack itemStack) {
        Iterator iterator = this.destillingList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Entry) iterator.next();
        }
        while (!this.func_151397_a(itemStack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getDestillingList() {
        return this.destillingList;
    }
}
