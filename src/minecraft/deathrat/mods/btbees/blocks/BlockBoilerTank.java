package deathrat.mods.btbees.blocks;

import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBoilerTank extends BlockContainer
{

	protected BlockBoilerTank(int id, Material material)
	{
		super(id, material);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		TileEntity boilerTE = world.getBlockTileEntity(x, y - 1 , z);
		TileEntity thisTE = world.getBlockTileEntity(x, y, z);
		if(boilerTE instanceof TileEntityBoiler)
		{
			((TileEntityBoiler) boilerTE).setBoilerTank((TileEntityBoilerTank) thisTE);
		}
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoilerTank();
	}

}
