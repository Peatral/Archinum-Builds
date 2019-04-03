package com.peatral.archinumbuilds.block;

import com.peatral.archinumbuilds.ArchinumBuilds;
import com.peatral.archinumbuilds.util.ResinWoodTypes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.peatral.archinumbuilds.world.biome.WorldGenResinTree;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.Random;

public class BlockResinSapling extends BlockSapling {// implements IGrowable {

    private IIcon[] icons = new IIcon[ResinWoodTypes.values().length];

    protected BlockResinSapling(String name) {
        super();

        this.setBlockName(name);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random rand)
    {
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, x, y, z, rand);

            if (worldIn.getBlockLightValue(x, y + 1, z) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, x, y, z, rand);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        metadata &= 7;
        return icons[MathHelper.clamp_int(metadata, 0, ResinWoodTypes.values().length)];
    }

    public void grow(World worldIn, int x, int y, int z, Random rand)
    {
        int l = worldIn.getBlockMetadata(x, y, z);

        if ((l & 8) == 0)
        {
            worldIn.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
        }
        else
        {
            this.generateTree(worldIn, x, y, z, rand);
        }
    }

    public void generateTree(World worldIn, int x, int y, int z, Random rand)
    {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, x, y, z)) return;
        int l = worldIn.getBlockMetadata(x, y, z) & 7;
        //Object object = rand.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        Object object = new WorldGenResinTree(true, worldIn.getBlockMetadata(x, y, z));
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        /*switch (l)
        {
            case 0:
            default:
                break;
            case 1:
                label78:

                for (i1 = 0; i1 >= -1; --i1)
                {
                    for (j1 = 0; j1 >= -1; --j1)
                    {
                        if (this.func_149880_a(worldIn, x + i1, y, z + j1, 1) && this.func_149880_a(worldIn, x + i1 + 1, y, z + j1, 1) && this.func_149880_a(worldIn, x + i1, y, z + j1 + 1, 1) && this.func_149880_a(worldIn, x + i1 + 1, y, z + j1 + 1, 1))
                        {
                            object = new WorldGenMegaPineTree(false, rand.nextBoolean());
                            flag = true;
                            break label78;
                        }
                    }
                }

                if (!flag)
                {
                    j1 = 0;
                    i1 = 0;
                    object = new WorldGenTaiga2(true);
                }

                break;
            case 2:
                object = new WorldGenForest(true, false);
                break;
        }*/

        Block block = Blocks.air;

        if (flag)
        {
            worldIn.setBlock(x + i1, y, z + j1, block, 0, 4);
            worldIn.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            worldIn.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            worldIn.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        }
        else
        {
            worldIn.setBlock(x, y, z, block, 0, 4);
        }

        if (!((WorldGenerator)object).generate(worldIn, rand, x + i1, y, z + j1))
        {
            if (flag)
            {
                worldIn.setBlock(x + i1, y, z + j1, this, l, 4);
                worldIn.setBlock(x + i1 + 1, y, z + j1, this, l, 4);
                worldIn.setBlock(x + i1, y, z + j1 + 1, this, l, 4);
                worldIn.setBlock(x + i1 + 1, y, z + j1 + 1, this, l, 4);
            }
            else
            {
                worldIn.setBlock(x, y, z, this, l, 4);
            }
        }
    }

    @Override
    public boolean func_149880_a(World worldIn, int x, int y, int z, int para)
    {
        return worldIn.getBlock(x, y, z) == this && (worldIn.getBlockMetadata(x, y, z) & 7) == para;
    }

    public boolean canGrow(World worldIn, int x, int y, int z, boolean isClient)
    {

        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, int x, int y, int z)
    {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void func_149853_b(World worldIn, Random rand, int x, int y, int z)
    {

        this.grow(worldIn, x, y, z, rand);
    }

    @Override
    public int damageDropped(int par1) {

        return MathHelper.clamp_int(par1 & 7, 0, 5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ResinWoodTypes.values().length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iIconRegister) {
        for(int i = 0; i < ResinWoodTypes.values().length; i++)
        {
            icons[i] = iIconRegister.registerIcon(ArchinumBuilds.MODID + ":" + ResinWoodTypes.values()[i].getName() + "Sapling");
        }
    }
}