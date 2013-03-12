package deathrat.mods.btbees.blocks;

import java.util.ArrayList;
import java.util.Random;

import powercrystals.core.position.BlockPosition;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.api.IBoilerModule;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;

public class BlockBoiler extends BlockContainer
{

	public BlockBoiler(int id, Material mat)
	{
		super(id, mat);

		setHardness(2.0F);
		setResistance(5.0F);
		setBlockName("blockBoiler");
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
	{
		super.onNeighborBlockChange(world, x, y, z, blockID);

		addNeighborModule(world, x, y, z);
	}

	public void addNeighborModule(World world, int x, int y, int z)
	{
		ForgeDirection[] directions = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
		ArrayList<IBoilerModule> arrayList = new ArrayList();
		TileEntity thisTE = getTileEntity(world, x, y, z);
		for (int i = 0; i < 4; i++)
		{
			ForgeDirection blockDirection = directions[i];
			TileEntity te = BlockPosition.getAdjacentTileEntity(thisTE, directions[i]);
			if (te instanceof IBoilerModule)
			{
				arrayList.add((IBoilerModule) te);
			}
			else if (te instanceof TileEntityBoilerTank) // Not used heh
			{
			}
		}
		((TileEntityBoiler) thisTE).setBoilerModules(arrayList);
	}

	@Override
	public String getTextureFile()
	{
		return BetterThanBees.terrainTextures;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 5;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		else if (player.getHeldItem() != null && player.getHeldItem().itemID == BetterThanBees.boilerTank.blockID && world.getBlockId(x, y + 1, z) == 0)
		{
			if (!player.capabilities.isCreativeMode)
				--player.getHeldItem().stackSize;
			world.setBlockWithNotify(x, y + 1, z, BetterThanBees.boilerTank.blockID);
			return true;
		}
		else
		{
			player.openGui(BetterThanBees.instance, 1, world, x, y, z);
			return true;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory))
		{
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0)
			{
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound())
				{
					entityItem.func_92014_d().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	public TileEntity getTileEntity(World world, int x, int y, int z)
	{
		return world.getBlockTileEntity(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityBoiler();
	}
}
