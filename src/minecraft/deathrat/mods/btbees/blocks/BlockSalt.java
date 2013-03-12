package deathrat.mods.btbees.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import deathrat.mods.btbees.BetterThanBees;

public class BlockSalt extends Block
{

	public BlockSalt(int id)
	{
		super(id, Material.rock);
		setHardness(0.5F);
		setResistance(0.0F);
		setBlockName("salt");
	}

	@Override
	public int getBlockTextureFromSide(int par1)
	{
		return 10;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, 8);
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer entityPlayer)
	{
		dropBlockAsItem_do(world, x, y, z, new ItemStack(BetterThanBees.saltItem, 1));

		if ((meta - 1) > 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, meta - 1);
		}
		else
		{
			world.setBlock(x, y, z, 0);
		}

		if (meta == 0)
		{
			world.setBlock(x, y, z, 0);
		}
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) > 0)
		{
			return false;
		}
		return world.setBlockWithNotify(x, y, z, 0);
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

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if (metadata == 8)
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
		else
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.125F * metadata, z + 1);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F * metadata, 1.0F);
	}
}
