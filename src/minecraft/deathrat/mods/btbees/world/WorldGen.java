package deathrat.mods.btbees.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenClay;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IWorldGenerator;
import deathrat.mods.btbees.BetterThanBees;

public class WorldGen implements IWorldGenerator
{
	public WorldGenClay saltGen = new WorldGenClay(BetterThanBees.saltID);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
//		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
//		String biomeName = biome.biomeName;
//		int x = chunkX + random.nextInt(16) + 8;
//		int y = random.nextInt(128);
//		int z = chunkZ + random.nextInt(16) + 8;


		if(world.provider.dimensionId == 0)
		{
			generateSalt(world, random, chunkX, chunkZ);

		}
	}

	private void generateSalt(World world, Random random, int chunkX, int chunkZ)
	{
		for(int i = 0; i < 30; i++)
		{
			int randomX = chunkX + random.nextInt(16) + 8;
			int randomZ = chunkZ + random.nextInt(16) + 8;
			int randomY = world.getTopSolidOrLiquidBlock(randomX, randomZ);

			int topBlock = world.getBlockId(randomX, randomY, randomZ);


			if(topBlock == Block.sand.blockID)
				saltGen.generate(world, random, randomX, world.getTopSolidOrLiquidBlock(randomX, randomZ), randomZ);
		}
	}



}
