package com.peatral.archinumbuilds.world;

import java.util.Random;

import com.peatral.archinumbuilds.block.ABBlocks;
import com.peatral.archinumbuilds.handler.ConfigHandler;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class ABOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
			case -1: generateNether(random, chunkX* 16, chunkZ *16, world);
				break;
			case 0: generateOverworld(random, chunkX* 16, chunkZ *16, world);
				break;
			case 1: generateEnd(random, chunkX* 16, chunkZ *16, world);
				break;
		}

	}
	
	/**
	 * Add a new WorldGenMinable with all the important stuff calculated
	 * @param block - block you want to spawn
	 * @param blockMeta - Meta-Data of the block you want to spawn
	 * @param blockSpawn - The BlockSpawn like Blocks.stone (what you normally use)
	 * @param random - Just a random
	 * @param world - The world it should be spawned in
	 * @param posX - Normally chunkX 
	 * @param posZ - Normally chunkY
	 * @param minY - The minimum height it should be spawned 
	 * @param maxY - The maximum height it should be spawned
	 * @param minVein - The minimum vein size
	 * @param maxVein - The maximum vein size
	 * @param spawnChance - The spawnchance
	 */
	private void addOre(Block block, int blockMeta, Block blockSpawn, Random random, World world, int posX, int posZ, int minY, int maxY, int minVein, int maxVein, int spawnChance){
		for(int i = 0; i < spawnChance; i++){
			int defaultChunkSize = 16;
			
			int xPos = posX + random.nextInt(defaultChunkSize);
			int yPos = minY + random.nextInt(maxY - minY);
			int zPos = posZ + random.nextInt(defaultChunkSize);
			
			new WorldGenMinable(block, blockMeta, (minVein + random.nextInt(maxVein - minVein)), blockSpawn).generate(world, random, xPos, yPos, zPos);
		}
	}
	
	private void generateEnd(Random random, int chunkX, int chunkZ, World world) {
		
	}

	private void generateOverworld(Random random, int chunkX, int chunkZ, World world) {
		addOre(ABBlocks.ore, 0, Blocks.stone, random, world, chunkX, chunkZ, ConfigHandler.archinumMinY, ConfigHandler.archinumMaxY, ConfigHandler.archinumMinVein, ConfigHandler.archinumMaxVein, ConfigHandler.archinumSpawnChance);
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world) {
		
	}

}
