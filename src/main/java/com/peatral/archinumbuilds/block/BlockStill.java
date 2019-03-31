package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.tileentity.TileEntityFragranceMixer;
import com.peatral.archinumbuilds.tileentity.TileEntityStill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockStill extends BlockBase {
    private final Random random = new Random();

    public BlockStill() {
        super(Material.rock);
    }

    public Item getItemDropped(int p_149650_1_, Random random, int p_149650_3_) {
        return Item.getItemFromBlock(ABBlocks.still);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntityStill tileEntity = (TileEntityStill) world.getTileEntity(x, y, z);

            if (tileEntity != null) {
                entityPlayer.openGui(ArchinumBuilds.instance, 3, world, x, y, z); //TODO: Change id
            }

            return true;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityStill();
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntityStill tileEntity = (TileEntityStill) world.getTileEntity(x, y, z);

        if (tileEntity != null) {
            for (int i1 = 0; i1 < tileEntity.getSizeInventory(); ++i1) {
                ItemStack itemstack = tileEntity.getStackInSlot(i1);

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
}
