package deathrat.mods.btbees.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.network.ServerPacketHandler;
import deathrat.mods.btbees.render.RenderRice;
import deathrat.mods.btbees.tileentity.TileEntityRicePlant;

public class BlockRicePlant extends BlockContainer implements IPlantable
{
	boolean canDrop;
	public static int metaData;

	public BlockRicePlant(int par1, int par2)
	{
		super(par1, par2, Material.plants);
		setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.0F, -0.2F, 0.0F, 1.0F, 0.0F, 1.0F);
	}

	public String getTextureFile()
	{
		return BetterThanBees.terrainTextures;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == Block.waterStill.blockID;
	}

	@Override
	public int getRenderType()
	{
		return RenderRice.renderId;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z)
	{
		return EnumPlantType.Water;
	}

	@Override
	public int getPlantID(World world, int x, int y, int z)
	{
		return blockID;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	public int getBlockTextureFromSideAndMetadata(int side, int metaData)
	{
		switch (metaData)
		{
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		}
		this.metaData = metaData;

		return 0;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		updatePlant(world, x, y, z, random);
	}

	public void updatePlant(World world, int x, int y, int z, Random random)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (side == Side.SERVER)
		{
			if (world.getBlockLightValue(x, y + 1, z) >= 9)
			{
				int l = world.getBlockMetadata(x, y, z);
				if (l < 3)
				{
					float f = 1.0F;
					if (random.nextInt((int) (100F / f) + 1) == 0)
					{
						l++;
						setPlantMeta(world, x, y, z, l);
					}
				}
			}
		}
	}

	public static void setPlantMeta(World world, int x, int y, int z, int meta)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (side == Side.SERVER)
		{
			world.setBlockMetadataWithNotify(x, y, z, meta);
			ServerPacketHandler.sendRiceUpdate((TileEntityRicePlant) world.getBlockTileEntity(x, y, z), meta);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (metaData >= 3)
			canDrop = true;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return BetterThanBees.riceHusk.itemID;
	}

	@Override
	public int quantityDropped(Random random)
	{
		int randomInt = random.nextInt(4);
		if (canDrop)
		{
			if (randomInt > 1)
				return randomInt;
			else
				return 2;
		}
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * if the specified block is in the given AABB, add its collision bounding
	 * box to the given list
	 */
	public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		if (par7Entity == null || !(par7Entity instanceof EntityBoat))
		{
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double) par2 + this.minX, (double) par3 + this.minY, (double) par4 + this.minZ, (double) par2 + this.maxX, (double) par3 + this.maxY, (double) par4 + this.maxZ);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		Block soil = blocksList[world.getBlockId(x, y - 1, z)];
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && (soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) BetterThanBees.uncookedRice));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityRicePlant();
	}
}
