package deathrat.mods.btbees.blocks;

import java.util.ArrayList;
import java.util.Random;

import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.api.IBoilerModule;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
		//North = -z, West = -x
		ArrayList<IBoilerModule> arrayList = new ArrayList();
		if(world.getBlockTileEntity(x + 1, y, z) instanceof IBoilerModule)
		{
			arrayList.add((IBoilerModule)world.getBlockTileEntity(x + 1, y, z));
			System.out.println("BoilerModule placed to East");
		}
		else if(world.getBlockTileEntity(x - 1, y, z) instanceof IBoilerModule)
		{
			arrayList.add((IBoilerModule)world.getBlockTileEntity(x - 1, y, z));
			System.out.println("BoilerModule placed to West");
		}
		else if(world.getBlockTileEntity(x, y, z + 1) instanceof IBoilerModule)
		{
			arrayList.add((IBoilerModule)world.getBlockTileEntity(x, y, z + 1));
			System.out.println("BoilerModule placed to South");
		}
		else if(world.getBlockTileEntity(x, y, z - 1) instanceof IBoilerModule)
		{
			arrayList.add((IBoilerModule)world.getBlockTileEntity(x, y, z - 1));
			System.out.println("BoilerModule placed to North");
		}
		else if(world.getBlockTileEntity(x, y + 1, z) instanceof TileEntityBoilerTank)
		{
			TileEntityBoiler te = (TileEntityBoiler) world.getBlockTileEntity(x, y, z);
			
		}
		((TileEntityBoiler)world.getBlockTileEntity(x, y, z)).setBoilerModules(arrayList);
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
		player.openGui(BetterThanBees.instance, 1, world, x, y, z);
		return true;
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
		if (!(tileEntity instanceof IInventory)) {
				return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack item = inventory.getStackInSlot(i);

				if (item != null && item.stackSize > 0) {
						float rx = rand.nextFloat() * 0.8F + 0.1F;
						float ry = rand.nextFloat() * 0.8F + 0.1F;
						float rz = rand.nextFloat() * 0.8F + 0.1F;

						EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

						if (item.hasTagCompound())
						{
								entityItem.func_92014_d().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
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


	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityBoiler();
	}
}
