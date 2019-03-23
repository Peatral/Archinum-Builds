package com.peatral.archinumbuilds.block;

import net.minecraft.block.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPricklyPear extends BlockDirectional implements IGrowable
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockPricklyPear()
    {
        super(Material.plants);
        this.setTickRandomly(true);
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return this.icons[2];
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
        else if (world.rand.nextInt(5) == 0)
        {
            int l = world.getBlockMetadata(x, y, z);
            int i1 = func_149987_c(l);

            if (i1 < 2)
            {
                ++i1;
                world.setBlockMetadataWithNotify(x, y, z, i1 << 2 | getDirection(l), 2);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getCocoaIcon(int stage)
    {
        if (stage < 0 || stage >= this.icons.length)
        {
            stage = this.icons.length - 1;
        }

        return this.icons[stage];
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        int l = getDirection(world.getBlockMetadata(x, y, z));
        x += Direction.offsetX[l];
        z += Direction.offsetZ[l];
        Block block = world.getBlock(x, y, z);
        return block == Blocks.log && BlockLog.func_150165_c(world.getBlockMetadata(x, y, z)) == 3;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 28;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        int l = iBlockAccess.getBlockMetadata(x, y, z);
        int i1 = getDirection(l);
        int j1 = func_149987_c(l);
        int k1 = 4 + j1 * 2;
        int l1 = 5 + j1 * 2;
        float f = (float)k1 / 2.0F;

        switch (i1)
        {
            case 0:
                this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)l1) / 16.0F, (15.0F - (float)k1) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
                break;
            case 1:
                this.setBlockBounds(0.0625F, (12.0F - (float)l1) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float)k1) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
                break;
            case 2:
                this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)l1) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float)k1) / 16.0F);
                break;
            case 3:
                this.setBlockBounds((15.0F - (float)k1) / 16.0F, (12.0F - (float)l1) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
        int l = ((MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 0) % 4;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: world, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        if (side == 1 || side == 0)
        {
            side = 2;
        }

        return Direction.rotateOpposite[Direction.facingToDirection[side]];
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public static int func_149987_c(int p_149987_0_)
    {
        return (p_149987_0_ & 12) >> 2;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float dropChance, int fortune)
    {
        super.dropBlockAsItemWithChance(world, x, y, z, meta, dropChance, fortune);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> dropped = super.getDrops(world, x, y, z, meta, fortune);
        int j1 = func_149987_c(meta);
        byte b0 = 1;

        if (j1 >= 2)
        {
            b0 = 3;
        }

        for (int k1 = 0; k1 < b0; ++k1)
        {
            dropped.add(new ItemStack(Items.dye, 1, 3));
        }
        return dropped;
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Items.dye;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister)
    {
        this.icons = new IIcon[3];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iIconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote)
    {
        int l = world.getBlockMetadata(x, y, z);
        int i1 = func_149987_c(l);
        return i1 < 2;
    }

    public boolean func_149852_a(World world, Random random, int x, int y, int z)
    {
        return true;
    }

    public void func_149853_b(World world, Random random, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        int i1 = BlockDirectional.getDirection(l);
        int j1 = func_149987_c(l);
        ++j1;
        world.setBlockMetadataWithNotify(x, y, z, j1 << 2 | i1, 2);
    }

    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return null;
    }
}
