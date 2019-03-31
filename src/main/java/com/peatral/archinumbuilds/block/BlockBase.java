package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.tileentity.TileEntityFractionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBase extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons = new IIcon[6];

    /*
        0 = bottom
        1 = top
        2 = north
        3 = south
        4 = west
        5 = east

         2
        4 5
         3

         meta = front
         */

    private static final int[][] rotIdxSides = new int[][]{
            new int[]{0,1,2,3,4,5},
            new int[]{0,1,3,2,5,4},
            new int[]{0,1,5,4,2,3},
            new int[]{0,1,4,5,3,2}
    };

    public BlockBase(Material material) {
        super(material);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setBlockRotationMetaData(world, x, y, z);
    }

    private void setBlockRotationMetaData(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block blockSouth = world.getBlock(x, y, z - 1);
            Block blockNorth = world.getBlock(x, y, z + 1);
            Block blockEast = world.getBlock(x - 1, y, z);
            Block blockWest = world.getBlock(x + 1, y, z);
            byte b0 = 3;


            if (blockSouth.func_149730_j() && !blockNorth.func_149730_j()) {
                b0 = 3;
            }

            if (blockNorth.func_149730_j() && !blockSouth.func_149730_j()) {
                b0 = 2;
            }

            if (blockWest.func_149730_j() && !blockEast.func_149730_j()) {
                b0 = 5;
            }

            if (blockEast.func_149730_j() && !blockWest.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName()) {
            ((TileEntityFractionatingColumn) world.getTileEntity(x, y, z)).setInventoryName(itemStack.getDisplayName());
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister) {
        this.blockIcon = iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactorySide");
        this.setIcon(0, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactoryBottom"));
        this.setIcon(1, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactoryTop"));
        this.setIcon(2, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactorySide"));
        this.setIcon(3, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactorySide"));
        this.setIcon(4, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactorySide"));
        this.setIcon(5, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":FactorySide"));
    }

    public void setIcon(int side, IIcon iicon){
        this.icons[side] = iicon;
    }

    public IIcon getSideIIcon(int side, int meta){
        return this.icons[rotIdxSides[meta >= 2 ? meta-2 : meta][side]];
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    public IIcon getIcon(int side, int meta){
        return getSideIIcon(side, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return null;
    }
}
