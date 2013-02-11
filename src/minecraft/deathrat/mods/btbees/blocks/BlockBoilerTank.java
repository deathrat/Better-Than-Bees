package deathrat.mods.btbees.blocks;

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
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoilerTank();
	}

}
