package deathrat.mods.btbees.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.world.World;

public class BlockSoy extends BlockCrops
{
	protected BlockSoy(int par1, int par2)
	{
		super(par1, par2);

		setTickRandomly(true);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return super.canBlockStay(world, x, y, z);
	}
}
