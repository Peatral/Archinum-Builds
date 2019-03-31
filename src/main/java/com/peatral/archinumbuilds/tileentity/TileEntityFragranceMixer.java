package com.peatral.archinumbuilds.tileentity;

import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.item.ItemFragranceFlask;
import net.minecraft.item.ItemStack;

public class TileEntityFragranceMixer extends TileEntityBasic {

    private static final int[] inputSlots = new int[]{0, 1};
    private static final int[] outputSlots = new int[]{2, 3};

    public TileEntityFragranceMixer() {
        super("fragranceMixer", 4);
    }

    @Override
    public int getInventoryStackLimit() {
        return 8;
    }

    //TODO: improve automation access

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        //return (slot == fuelSlot ? isItemFuel(itemStack) : (slot == inputSlot ? RecipesFractionatingColumn.fractionating().getFractionatingResult(itemStack) != null : true));
        return (slot == inputSlots[0] || slot == inputSlots[1]) && (itemStack.getItem() == ABItems.fragranceFlask || itemStack.getItem() == ABItems.fragrancePure);
    }

    //TODO: improve automation access

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ?
                outputSlots :
                (side == 1 ?
                        new int[]{inputSlots[0]} :
                        new int[]{inputSlots[1]}
                );
    }

    //TODO: improve automation access

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    //TODO: improve automation access

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return slot != inputSlots[0] && slot != inputSlots[1];
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        ItemStack result = getResult();
        if (result != null) {
            boolean out1null = (inventory[outputSlots[0]] == null);
            boolean out2null = (inventory[outputSlots[1]] == null);
            boolean out1ok = out1null || (inventory[outputSlots[0]].stackSize < getInventoryStackLimit() && result.stackTagCompound.equals(inventory[outputSlots[0]].stackTagCompound));
            boolean out2ok = out2null || inventory[outputSlots[1]].stackSize < getInventoryStackLimit();
            boolean doable = out1ok && out2ok;

            boolean inOnlyFlasks = (inventory[inputSlots[0]].getItem() == ABItems.fragranceFlask && inventory[inputSlots[1]].getItem() == ABItems.fragranceFlask);

            if (doable) {
                if (out1null) {
                    inventory[outputSlots[0]] = result.copy();
                } else {
                    ++inventory[outputSlots[0]].stackSize;
                }

                if (inOnlyFlasks) {
                    if (out2null) {
                        inventory[outputSlots[1]] = new ItemStack(ABItems.fragranceFlaskEmpty, 1);
                    } else {
                        ++inventory[outputSlots[1]].stackSize;
                    }
                }

                decrStackSize(inputSlots[0], 1);
                decrStackSize(inputSlots[1], 1);

                markDirty();
            }
        }
    }


    public ItemStack getResult() {
        if (inventory[inputSlots[0]] != null && inventory[inputSlots[1]] != null) {
            return ItemFragranceFlask.getMixed(inventory[inputSlots[0]], inventory[inputSlots[1]]);
        } else {
            return null;
        }
    }
}
