package com.peatral.archinumbuilds.item;

import java.util.List;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.Resource;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCircuit extends Item{
	
	public IIcon[] icons = new IIcon[1];


	@Override
	public void registerIcons(IIconRegister register)
	{
		for(int i = 0; i < Resource.values().length-3; i++)
		{
			icons[i] = register.registerIcon(ArchinumBuilds.MODID + ":" + Resource.values()[i].getName() + "ControlCircuit");
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
		for(int counter = 0; counter < Resource.values().length-3; counter++)
		{
			itemList.add(new ItemStack(this, 1, counter));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		if(item.getItemDamage() <= Resource.values().length-1)
		{
			return "item." + Resource.values()[item.getItemDamage()].getName().toLowerCase() + "ControlCircuit";
		}
		
		return "Invalid";
	}

}
