package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.tileentity.TileEntityFracionatingColumn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFractionatingColumnBase extends BlockBase {

    private final Random random = new Random();

    public BlockFractionatingColumnBase(Material material) {
        super(material);
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return Item.getItemFromBlock(ABBlocks.catalyst);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister) {
        super.registerBlockIcons(iIconRegister);
        this.blockIcon = iIconRegister.registerIcon(ArchinumBuilds.MODID + ":fractionatingColumnBaseFront");
        this.setIcon(1, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":fractionatingColumnBaseTop"));
        this.setIcon(2, iIconRegister.registerIcon(ArchinumBuilds.MODID + ":fractionatingColumnBaseFront"));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntityFracionatingColumn tileEntityFracionatingColumn = (TileEntityFracionatingColumn) world.getTileEntity(x, y, z);

            if (tileEntityFracionatingColumn != null) {
                entityPlayer.openGui(ArchinumBuilds.instance, 1, world, x, y, z); //TODO: Change id
            }

            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityFracionatingColumn();
    }


    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntityFracionatingColumn tileEntityFracionatingColumn = (TileEntityFracionatingColumn) world.getTileEntity(x, y, z);

        if (tileEntityFracionatingColumn != null) {
            for (int i1 = 0; i1 < tileEntityFracionatingColumn.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileEntityFracionatingColumn.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j1 = this.random.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }


        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ABBlocks.fractionatingColumnBase);
    }
}
