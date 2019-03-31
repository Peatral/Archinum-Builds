package com.peatral.archinumbuilds.item;

import java.util.List;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.ResourceOres;

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
		//for(int i = 0; i < ResourceOres.values().length-2; i++){
			icons[0] = register.registerIcon(ArchinumBuilds.MODID + ":" + ResourceOres.values()[0].getName() + "ControlCircuit");
		//}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[0];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List itemList)
	{
		//for(int counter = 0; counter < ResourceOres.values().length-3; counter++) {
			itemList.add(new ItemStack(this, 1, 0));
		//}
	}

	@Override
	public String getUnlocalizedName(ItemStack item)
	{
		//if(item.getItemDamage() <= ResourceOres.values().length) {
			//return "item." + ResourceOres.values()[item.getItemDamage()].getName().toLowerCase() + "ControlCircuit";
		//}

        return "item." + ResourceOres.ARCHINUM.getName().toLowerCase() + "ControlCircuit";

        //return "Invalid";
	}

}
