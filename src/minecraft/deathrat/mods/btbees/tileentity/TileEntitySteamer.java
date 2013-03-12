package deathrat.mods.btbees.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import deathrat.mods.btbees.api.IBoilerModule;

public class TileEntitySteamer extends TileEntityMachine implements IBoilerModule, IInventory
{
	private ItemStack[] inv;
	public TileEntityBoiler connectedBoiler;

	public TileEntitySteamer()
	{
		powerProvider.configure(10 * maxPower, 2 * 1200);
	}

	@Override
	public int getXCoord()
	{
		return xCoord;
	}

	@Override
	public int getYCoord()
	{
		return yCoord;
	}

	@Override
	public int getZCoord()
	{
		return zCoord;
	}

	@Override
	public int getSizeInventory()
	{
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount)
	{
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack is)
	{

	}

	@Override
	public String getInvName()
	{
		return "Steamer";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return false;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

}
