package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.util.ResinWoodTypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockResinWood extends ItemBlockWithMetadata {

    public ItemBlockResinWood(Block block) {
            super(block, block);
    }
    
    @Override
	public String getUnlocalizedName(ItemStack item)
	{
		if(item.getItemDamage() <= ResinWoodTypes.values().length)
		{
			return "tile." + ResinWoodTypes.values()[item.getItemDamage()].getName().toLowerCase() + "Wood";
		}
		
		return "Invalid";
	}
    
}