package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.util.Resource;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockOreBlock extends ItemBlockWithMetadata {

    public ItemBlockOreBlock(Block block) {
            super(block, block);
    }
    
    @Override
	public String getUnlocalizedName(ItemStack item)
	{
		if(item.getItemDamage() <= Resource.values().length-1)
		{
			return "tile." + Resource.values()[item.getItemDamage()].getName().toLowerCase() + "block";
		}
		
		return "Invalid";
	}
    
}