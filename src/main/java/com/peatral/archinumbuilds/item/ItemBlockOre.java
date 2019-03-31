package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.util.ResourceOres;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockOre extends ItemBlockWithMetadata {

    public ItemBlockOre(Block block) {
            super(block, block);
    }
    
    @Override
	public String getUnlocalizedName(ItemStack item)
	{
		if(item.getItemDamage() <= ResourceOres.values().length)
		{
			return "tile." + ResourceOres.values()[item.getItemDamage()].getName().toLowerCase() + "Ore";
		}
		
		return "Invalid";
	}
    
}