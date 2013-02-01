package deathrat.mods.btbees.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.tileentity.TileEntityWok;

public class ContainerWok extends Container
{
	protected TileEntityWok tileEntity;

	public ContainerWok(InventoryPlayer invPlayer, TileEntityWok te)
	{
		tileEntity = te;

		for(int height=0; height < 3; height++)
		{
			for(int width = 0; width < 3; width++)
			{
				addSlotToContainer(new Slot(tileEntity, width + height * 3, 8 + width * 18, 8 + height * 18));
			}
		}

		//Heat Slot
		addSlotToContainer(new Slot(tileEntity, this.inventorySlots.size() + 1, 80, 64));

		//Result Slot
		addSlotToContainer(new Slot(tileEntity, this.inventorySlots.size() + 1, 114, 27));

		bindPlayerInventory(invPlayer);
	}

	protected void bindPlayerInventory(InventoryPlayer invPlayer)
	{
		//Player Inventory
		for(int height = 0; height < 3; height++)
		{
			for(int width = 0; width < 9; width++)
			{
				addSlotToContainer(new Slot(invPlayer, width + height * 9 + 9, 8 + width * 18, 84 + height * 18));
			}
		}

		//Quickslots
		for(int width = 0; width < 9; width++)
		{
			addSlotToContainer(new Slot(invPlayer, width, 8 + width * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
	{
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		if(slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if(slot < 9)
			{
				if(!this.mergeItemStack(stackInSlot, 9, 45, false))
				{
					return null;
				}
			}

			else if (!this.mergeItemStack(stackInSlot, 0, 9, false))
			{
				return null;
			}

			if(stackInSlot.stackSize == 0)
			{
				slotObject.putStack(null);
			}
			else
			{
				slotObject.onSlotChanged();
			}

			if(stackInSlot.stackSize == stack.stackSize)
			{
				return null;
			}

			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.tileEntity.furnaceCookTime = par2;
		}

		if (par1 == 1)
		{
			this.tileEntity.furnaceBurnTime = par2;
		}

		if (par1 == 2)
		{
			this.tileEntity.currentItemBurnTime = par2;
		}
	}

}
