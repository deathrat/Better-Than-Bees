package deathrat.mods.btbees.blocks;

import java.util.List;
import java.util.Map;
import java.util.Random;

import deathrat.mods.btbees.BetterThanBees;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class MFRPatch implements IFactoryHarvestable, IFactoryFertilizable
{

	@Override
	public int getFertilizableBlockId()
	{
		return BetterThanBees.ricePlantID;
	}

	@Override
	public boolean canFertilizeBlock(World world, int x, int y, int z, FertilizerType fertilizerType)
	{
		return fertilizerType == FertilizerType.GrowPlant && world.getBlockMetadata(x, y, z) < 3;
	}

	@Override
	public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType)
	{
		BlockRicePlant.setPlantMeta(world, x, y, z, 3);
		return true;
	}

	@Override
	public HarvestType getHarvestType()
	{
		return HarvestType.Normal;
	}

	@Override
	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
	{
		return true;
	}

	@Override
	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z)
	{
		return BetterThanBees.ricePlant.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	}

	@Override
	public void preHarvest(World world, int x, int y, int z)
	{
	}

	@Override
	public void postHarvest(World world, int x, int y, int z)
	{
	}

	@Override
	public int getSourceId()
	{
		return BetterThanBees.ricePlantID;
	}

}
