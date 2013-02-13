package deathrat.mods.btbees.compat;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import deathrat.mods.btbees.BetterThanBees;

public class MFRSeedsPatch implements IFactoryPlantable
{

	@Override
	public int getSourceId()
	{
		return BetterThanBees.uncookedRiceID;
	}

	@Override
	public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack)
	{
		return BetterThanBees.ricePlantID;
	}

	@Override
	public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack)
	{
		return 0;
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack)
	{
		if(world.getBlockMaterial(x, y-1, z) == Material.water && world.getBlockMetadata(x, y-1, z) == 0 && world.isAirBlock(x, y, z))
		{
			return true;
		}
		return false;
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack)
	{
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack)
	{
	}

}
