package com.peatral.archinumbuilds.item;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.ResinWoodTypes;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemResin extends Item{

    public IIcon[] icons = new IIcon[ResinWoodTypes.values().length];


    @Override
    public void registerIcons(IIconRegister register)
    {
        for(int i = 0; i < ResinWoodTypes.values().length; i++)
        {
            icons[i] = register.registerIcon(ArchinumBuilds.MODID + ":" + ResinWoodTypes.values()[i].getName() + "Resin");
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
        for(int counter = 0; counter < ResinWoodTypes.values().length; counter++)
        {
            itemList.add(new ItemStack(this, 1, counter));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack item)
    {
        if(item.getItemDamage() <= ResinWoodTypes.values().length)
        {
            return "item." + ResinWoodTypes.values()[item.getItemDamage()].getName().toLowerCase() + "Resin";
        }

        return "Invalid";
    }

}