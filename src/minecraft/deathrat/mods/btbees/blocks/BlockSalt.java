package deathrat.mods.btbees.blocks;

import java.util.Random;

import deathrat.mods.btbees.BetterThanBees;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockSalt extends Block
{

	public BlockSalt(int id, Material material)
	{
		super(id, material);
		setHardness(0.0F);
		setResistance(0.0F);
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData)
	{
		world.setBlock(x, y, z, Block.sand.blockID);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 6;
	}

	@Override
	public String getTextureFile()
	{
		return BetterThanBees.terrainTextures;
	}

}
