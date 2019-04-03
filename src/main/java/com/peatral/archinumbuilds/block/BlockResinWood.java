package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.item.ABItems;
import com.peatral.archinumbuilds.util.ResinWoodTypes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockResinWood extends BlockLog

{
    private IIcon[] _iconLogTop = new IIcon[ResinWoodTypes.values().length];
    private IIcon[] _iconLogSide = new IIcon[ResinWoodTypes.values().length];


    protected BlockResinWood(String name) {
        super();

        this.setBlockName(name);
        //this.setHardness(3.0F); // stone: 1.5F // obsidian: 50.0F
        this.setHarvestLevel("axe", 0);
        //this.setResistance(30.0F); // stone: 10.0F // obsidian: 2000.0F
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        for(int i = 0; i < ResinWoodTypes.values().length; i++)
        {
            _iconLogSide[i] = register.registerIcon(ArchinumBuilds.MODID + ":" + ResinWoodTypes.values()[i].getName() + "_side");
            _iconLogTop[i] = register.registerIcon(ArchinumBuilds.MODID + ":" + ResinWoodTypes.values()[i].getName() + "_top");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta)
    {
        return _iconLogSide[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int meta)
    {
        return _iconLogTop[meta];
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        switch(metadata & 7)
        {
            case 0:
                drops.add(new ItemStack(this, 1, 0));
                //if((metadata & 3) == 1)
                drops.add(new ItemStack(ABItems.resign, fortune <= 0 ? 1 : 1 + world.rand.nextInt(fortune), 0));
                return drops;
            case 1:
                drops.add(new ItemStack(this, 1, 1));
                //if((metadata & 3) == 1)
                drops.add(new ItemStack(ABItems.resign, fortune <= 0 ? 1 : 1 + world.rand.nextInt(fortune), 1));
                return drops;
        }
        return drops;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return super.getFireSpreadSpeed(world, x, y, z, face) * ((world.getBlockMetadata(x, y, z) & 3) + 1);
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return super.getFlammability(world, x, y, z, face) * ((world.getBlockMetadata(x, y, z) & 3) + 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ResinWoodTypes.values().length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return false;
    }
}