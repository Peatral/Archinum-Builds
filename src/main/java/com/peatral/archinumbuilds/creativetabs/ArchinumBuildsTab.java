package com.peatral.archinumbuilds.creativetabs;

import com.peatral.archinumbuilds.block.ABBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ArchinumBuildsTab extends CreativeTabs {

	public ArchinumBuildsTab(String lable) {
		super(lable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(ABBlocks.ore);
	}
}
