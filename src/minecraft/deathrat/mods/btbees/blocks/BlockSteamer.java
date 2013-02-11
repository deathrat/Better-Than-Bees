package deathrat.mods.btbees.blocks;

import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.tileentity.TileEntitySteamer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSteamer extends BlockContainer
{
	public BlockSteamer(int id, Material mat)
	{
		super(id, mat);
		setBlockName("blockSteamer");
	}
	
	@Override
	public String getTextureFile()
	{
		return BetterThanBees.terrainTextures;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		return 6;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySteamer();
	}

}
