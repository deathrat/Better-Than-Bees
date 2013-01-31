package deathrat.mods.btbees.tileentity;

import deathrat.mods.btbees.power.PowerProviderBTB;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoiler extends TileEntity implements IInventory, IPowerReceptor
{
	private ItemStack[] inv;
	public PowerProviderBTB powerProvider;
	public int fireLevel;
	public int waterLevel;
	public int waterLevelMax = 15;
	public int fireLevelMax = 100;

	public boolean isActive;

	private float minPower = 0.5F;
	private int maxPower = 2;
	private int maxEnergy = maxPower * 1200;
	private int minPowerLevel = (2 * maxEnergy / 10);
	private int maxPowerLevel = (8 * maxEnergy / 10);
	private int energyRamp = (maxPowerLevel / maxPower);

	public TileEntityBoiler()
	{
		inv = new ItemStack[31];
		fireLevel = 61;
		waterLevel = 21;
		powerProvider = new PowerProviderBTB();

		powerProvider.configure(10 * 2, 2 * 1200);

		powerProvider.setEnergyStored(600F);
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
		tagCompound.setFloat("energyLevel", this.powerProvider.getMaxEnergyStored());
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

        try
        {
    		this.powerProvider.setEnergyStored(tagCompound.getFloat("energyLevel"));
    		if(Float.isNaN(powerProvider.getEnergyStored()))
    			powerProvider.setEnergyStored(0.0F);
        }
        catch(Exception e)
        {
        	powerProvider.setEnergyStored(0.0F);
        }
		fireLevel = tagCompound.getInteger("fireLevel");
		waterLevel = tagCompound.getInteger("waterLevel");
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

	public int getScaledEnergyStored(int scale)
	{
		return Math.round(powerProvider.getEnergyStored() * scale / powerProvider.getMaxEnergyStored());
	}

	public int getScaledFireLevel(int par1)
	{
		if(this.fireLevel == 0)
			this.fireLevel = 200;
		return this.fireLevel * par1 / this.fireLevelMax;
	}

	public float getEnergy()
	{
		return powerProvider.getEnergyStored();
	}

	public float getMaxEnergy()
	{
		return maxEnergy;
	}

	public float getPower()
	{
		if(!isActive)
		{
			return 0.0F;
		}
		if(powerProvider.getMaxEnergyStored() > maxPowerLevel)
			return maxPower;
		if(powerProvider.getMaxEnergyStored() < minPowerLevel)
			return minPower;

		int intPower = (int)(10.0F * powerProvider.getEnergyStored() / energyRamp);
		return intPower / 10.0F;
	}

	public float getMaxPower()
	{
		return maxPower;
	}

	@Override
    public void setPowerProvider(IPowerProvider provider)
    {
    }

	@Override
    public IPowerProvider getPowerProvider()
    {
	    return powerProvider;
    }

	@Override
    public void doWork()
    {
    }

	@Override
    public int powerRequest()
    {
		if (powerProvider.getEnergyStored() == powerProvider.getMaxEnergyStored())
		{
	        return 0;
		}
		return (int)Math.ceil(Math.min(powerProvider.getMaxEnergyReceived(), powerProvider.getMaxEnergyStored() - powerProvider.getEnergyStored()));
    }
}
