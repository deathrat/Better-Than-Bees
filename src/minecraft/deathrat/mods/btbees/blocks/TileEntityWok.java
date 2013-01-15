package deathrat.mods.btbees.blocks;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import deathrat.mods.btbees.network.ServerPacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWok extends TileEntity implements IInventory
{
	private ItemStack[] inv;
	private int fireLevel;

	public TileEntityWok()
	{
		inv = new ItemStack[12];
	}

	@Override
	public void updateEntity()
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if(side == Side.SERVER)
		{
			ServerPacketHandler.sendWokUpdate(this, this.fireLevel);
		}
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
		return "deathrat.tileentitywok";
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
	}


	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, Player player, ByteArrayDataInput data, int fireLevel)
    {
		try
		{
			this.fireLevel = fireLevel;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
