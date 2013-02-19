package deathrat.mods.btbees.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;

public class BlockBoilerTank extends BlockContainer
{

	public BlockBoilerTank(int id, Material material)
	{
		super(id, material);
	}
	
	public String getBlockName() 
	{
		return "Boiler Tank";
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y - 1, z);
		if(tileEntity instanceof TileEntityBoiler)
		{
			return true;
		}
		else
		{
			return false;
		}
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
		else
		{
			return 0;
		}
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		TileEntity boilerTE = world.getBlockTileEntity(x, y - 1 , z);
		if(boilerTE instanceof TileEntityBoiler)
			((TileEntityBoiler) boilerTE).setBoilerTank((TileEntityBoilerTank) null);
			
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoilerTank();
	}

}
