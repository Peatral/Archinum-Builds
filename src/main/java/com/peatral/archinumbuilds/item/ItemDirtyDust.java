package com.peatral.archinumbuilds.item;

import java.util.List;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.ResourceOres;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDirtyDust extends Item{
	
	public IIcon[] icons = new IIcon[5];


	@Override
	public void registerIcons(IIconRegister register)
	{
		for(int i = 0; i < ResourceOres.values().length; i++)
		{
			icons[i] = register.registerIcon(ArchinumBuilds.MODID + ":Dirty" + ResourceOres.values()[i].getName() + "Dust");
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList)
	{
		for(int counter = 0; counter < ResourceOres.values().length; counter++)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		if(item.getItemDamage() <= ResourceOres.values().length)
		{
			return "item.dirty" + ResourceOres.values()[item.getItemDamage()].getName() + "Dust";
		}
		
		return "Invalid";
	}

}
