package deathrat.mods.btbees.tileentity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import deathrat.mods.btbees.api.IBoilerModule;

public class TileEntityBoiler extends TileEntityMachine implements IInventory
{
	private ItemStack[] inv;
	public ArrayList<IBoilerModule> boilerModules;
	public int fireLevel;
	public int fireLevelMax = 100;

	public boolean isActive;

	public TileEntityBoilerTank boilerTank;

	public TileEntityBoiler()
	{
		inv = new ItemStack[31];
		fireLevel = 61;

		powerProvider.configure(10 * maxPower, 2 * 1200);
	}

	public void setBoilerTank(TileEntityBoilerTank boilerTank)
	{
		this.boilerTank = boilerTank;
	}

	public void setBoilerModules(ArrayList<IBoilerModule> list)
	{
		boilerModules = list;
	}

	public IBoilerModule getBoilerModule(World world, ForgeDirection side)
	{
		return (IBoilerModule) world.getBlockTileEntity(xCoord + side.offsetX, yCoord, zCoord + side.offsetZ);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize <= amt)
			{
				setInventorySlotContents(slot, null);
			} else
			{
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "Boiler";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}

		tagCompound.setTag("Inventory", itemList);
		tagCompound.setInteger("fireLevel", fireLevel);
		// tagCompound.setInteger("waterLevel", waterLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length)
			{
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}

		fireLevel = tagCompound.getInteger("fireLevel");
	}

	public int getFireLevel()
	{
		return this.fireLevel;
	}

	public int getScaledFireLevel(int par1)
	{
		return this.fireLevel * par1 / this.fireLevelMax;
	}

	public void receiveGuiNetworkData(int bar, int value)
	{
	}

	public int getMaxWater()
	{
		return this.getMaxWater();
	}

	@Override
	public boolean isInvNameLocalized()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
