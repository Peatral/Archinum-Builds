package com.peatral.archinumbuilds.block;

import java.util.List;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.ResourceOres;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockOreBlock extends Block {

	public IIcon[] icons = new IIcon[5];
	
	protected BlockOreBlock(Material material, String name) {
		super(material);
		
		this.setBlockName(name);		
		this.setHardness(3.0F); // stone: 1.5F // obsidian: 50.0F
		//this.setLightLevel(0.5F); // glowstone: 1.0F // stone: 0.0F
		this.setHarvestLevel("pickaxe", 2); // wood: 0 // stone: 1 // iron: 2 // diamond: 3
		this.setResistance(30.0F); // stone: 10.0F // obsidian: 2000.0F
		this.setStepSound(soundTypeStone);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		for(int i = 0; i < ResourceOres.values().length; i++)
		{
			icons[i] = register.registerIcon(ArchinumBuilds.MODID + ":" + ResourceOres.values()[i].getName() + "block");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return icons[meta];
	}
	
	@Override
	public int damageDropped( int meta ){
		return meta;
	}
	
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < ResourceOres.values().length; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}

}