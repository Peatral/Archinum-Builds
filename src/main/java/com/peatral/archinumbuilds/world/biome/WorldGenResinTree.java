package com.peatral.archinumbuilds.world.biome;

import com.peatral.archinumbuilds.block.ABBlocks;
import com.peatral.archinumbuilds.util.ResinWoodTypes;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenResinTree extends WorldGenAbstractTree {
    // of course, in super this identical flag is private
    //protected boolean doBlockNotify;

    /** The minimum height of a generated tree. */
    private final int minTreeHeight;
    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;
    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;
    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;
    private static final String __OBFID = "CL_00000438";

    public WorldGenResinTree(boolean doNotify, int metaw) {
        this(doNotify, 2, metaw, 0, false);
    }

    public WorldGenResinTree(boolean doNotify) {
        this(doNotify, 2, 0, 0, false);
    }

    public WorldGenResinTree(boolean doNotify, int minHeight, int metaw, int metal, boolean vines)
    {
        super(doNotify);
        this.minTreeHeight = minHeight;
        this.metaWood = metaw;
        this.metaLeaves = metal;
        this.vinesGrow = vines;
    }

    @Override
    public boolean generate(World worldIn, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(2) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int height;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (height = z - b0; height <= z + b0 && flag; ++height)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = worldIn.getBlock(j1, i1, height);

                            if (!this.isReplaceable(worldIn, j1, i1, height))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = worldIn.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(worldIn, x, y - 1, z, ForgeDirection.UP, (BlockSapling) Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block2.onPlantGrow(worldIn, x, y - 1, z, x, y, z);
                    //b0 = 2;
                    //byte b1 = 0;

                    /*for (int foliageY  = y - b0 + l; foliageY  <= y + l; ++foliageY )
                    {
                        int i3 = foliageY  - (y + l);
                        int foliageLayerRadius  = b1 + 1 - i3 / 4;

                        for (int foliageX  = x - foliageLayerRadius ; foliageX  <= x + foliageLayerRadius ; ++foliageX )
                        {
                            int j2 = foliageX  - x;

                            for (int foliageZ  = z - foliageLayerRadius ; foliageZ  <= z + foliageLayerRadius ; ++foliageZ )
                            {
                                int l2 = foliageZ  - z;

                                if (Math.abs(j2) != foliageLayerRadius  || Math.abs(l2) != foliageLayerRadius  || rand.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block blocheight = worldIn.getBlock(foliageX , foliageY , foliageZ );

                                    if (blocheight.isAir(worldIn, foliageX , foliageY , foliageZ ) || blocheight.isLeaves(worldIn, foliageX , foliageY , foliageZ ))
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, foliageX , foliageY , foliageZ , Blocks.leaves, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }*/
                    Block blocheight = worldIn.getBlock(x, y + l - 1, z + 1);
                    if (blocheight.isAir(worldIn, x, y + l - 1, z + 1 ) || blocheight.isLeaves(worldIn,x , y + l - 1, z + 1))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, x, y + l - 1, z + 1, Blocks.leaves, this.metaLeaves);
                    }
                    blocheight = worldIn.getBlock( x, y + l - 1, z - 1);
                    if (blocheight.isAir(worldIn, x, y + l - 1, z - 1) || blocheight.isLeaves(worldIn, x, y + l - 1, z - 1))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, x, y + l - 1, z - 1, Blocks.leaves, this.metaLeaves);
                    }
                    blocheight = worldIn.getBlock( x + 1, y + l - 1, z);
                    if (blocheight.isAir(worldIn, x + 1, y + l - 1, z) || blocheight.isLeaves(worldIn,  x + 1, y + l - 1, z))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, x + 1, y + l - 1, z, Blocks.leaves, this.metaLeaves);
                    }
                    blocheight = worldIn.getBlock( x - 1, y + l - 1, z);
                    if (blocheight.isAir(worldIn, x - 1, y + l - 1, z) || blocheight.isLeaves(worldIn, x - 1, y + l - 1, z))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, x - 1, y + l - 1, z, Blocks.leaves, this.metaLeaves);
                    }
                    blocheight = worldIn.getBlock( x , y + l, z );
                    if (blocheight.isAir(worldIn, x , y + l, z ) || blocheight.isLeaves(worldIn, x , y + l, z ))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, x , y + l, z , Blocks.leaves, this.metaLeaves);
                    }

                    for (height = 0; height < l; ++height)
                    {
                        block = worldIn.getBlock(x, y + height, z);

                        if (block.isAir(worldIn, x, y + height, z) || block.isLeaves(worldIn, x, y + height, z))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, x, y + height, z, ABBlocks.resinWood, this.metaWood & 7);

                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public void func_150524_b(World world, Random random, int i, int j, int k) {
        {
            //for (int l = 0; l < 64; ++l) {
                //int i1 = i + random.nextInt(8) - random.nextInt(8);
                //int j1 = j + random.nextInt(4) - random.nextInt(4);
                //int height = k + random.nextInt(8) - random.nextInt(8);


	            if (world.isAirBlock(i, j, k) && world.getBlock(i, j - 1, k) == Blocks.grass && ABBlocks.resinSapling.canPlaceBlockAt(world, i, j, k))
	            {
	                world.setBlock(i, j, k, ABBlocks.resinSapling, random.nextInt(ResinWoodTypes.values().length), 2);
	            }

            //}
        }
    }
}