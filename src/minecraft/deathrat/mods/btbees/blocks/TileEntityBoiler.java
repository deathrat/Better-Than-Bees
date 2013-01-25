package deathrat.mods.btbees.blocks;

import deathrat.mods.btbees.api.IMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoiler extends TileEntity implements IInventory, IMachine
{
	private ItemStack[] inv;
	public int fireLevel;
	public int waterLevel;
	public int energyLevel;
	public int waterLevelMax = 15;
	public int fireLevelMax = 100;
	public int energyLevelMax = 100;

	public boolean isReceivingPower;

	public TileEntityBoiler()
	{
		inv = new ItemStack[31];
		fireLevel = 61;
		waterLevel = 21;
		energyLevel = 61;
	}

	@Override
	public void updateEntity()
	{
	    super.updateEntity();

	    if(waterLevel > waterLevelMax)
	    	waterLevel = waterLevelMax;
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
        if (stack != null) {
                if (stack.stackSize <= amt) {
                        setInventorySlotContents(slot, null);
                } else {
                        stack = stack.splitStack(amt);
                        if (stack.stackSize == 0) {
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
		if(stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}


	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit())
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
		for(int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if(stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}

		tagCompound.setTag("Inventory", itemList);
		tagCompound.setInteger("energyLevel", energyLevel);
		tagCompound.setInteger("fireLevel", fireLevel);
		tagCompound.setInteger("waterLevel", waterLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }

		energyLevel = tagCompound.getInteger("energyLevel");
		fireLevel = tagCompound.getInteger("fireLevel");
		waterLevel = tagCompound.getInteger("waterLevel");
	}

	@Override
    public boolean hasOutput()
    {
	    return false;
    }

	@Override
    public boolean hasInput()
    {
	    return true;
    }

	@Override
    public int getPower()
    {
	    return energyLevel;
    }

	@Override
    public void setPower(int power)
    {
		energyLevel = power;
    }

	@Override
    public int getMaxPower()
    {
	    return energyLevelMax;
    }

	@Override
    public int getMachineId()
    {
	    return blockType.blockID;
    }

	@Override
    public boolean isReceivingPower()
    {
	    return isReceivingPower;
    }

	@Override
    public boolean isOutputtingPower()
    {
	    return false;
    }

	public boolean hasWater()
	{
		return waterLevel > 0;
	}

	public int getScaledWaterLevel(int par1)
	{
        if (this.waterLevel == 0)
            this.waterLevel = 200;
        return this.waterLevel * par1 / this.waterLevelMax;
	}

	public int getScaledEnergyLevel(int par1)
	{
		if(this.energyLevel == 0)
			this.energyLevel = 200;
		return this.energyLevel * par1 / this.energyLevelMax;
	}

	public int getScaledFireLevel(int par1)
	{
		if(this.fireLevel == 0)
			this.fireLevel = 200;
		return this.fireLevel * par1 / this.fireLevelMax;
	}


}
