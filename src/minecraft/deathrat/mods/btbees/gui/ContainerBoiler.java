package deathrat.mods.btbees.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityWok;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBoiler extends Container
{
	protected TileEntityBoiler tileEntity;
	private int lastFireLevel;
	private float lastEnergyLevel;
	private int lastWaterLevel;

	public ContainerBoiler(InventoryPlayer invPlayer, TileEntityBoiler te)
    {
		tileEntity = te;


		for(int height = 0; height < 3; height++)
		{
			for(int width = 0; width < 9; width++)
			{
				addSlotToContainer(new Slot(tileEntity, width + height * 3, 8 + width * 18, 43 + height * 18));
			}
		}

		//Fire Slot
		addSlotToContainer(new Slot(tileEntity, this.inventorySlots.size() + 1, 80, 23));

		//Energy Slot
		addSlotToContainer(new Slot(tileEntity, this.inventorySlots.size() + 1, 98, 23));

		//Water Slot
		addSlotToContainer(new Slot(tileEntity, this.inventorySlots.size() + 1, 174, 96));

		bindPlayerInventory(invPlayer);
    }

	protected void bindPlayerInventory(InventoryPlayer invPlayer)
	{
		//Player Inventory
		for(int height = 0; height < 3; height++)
		{
			for(int width = 0; width < 9; width++)
			{
				addSlotToContainer(new Slot(invPlayer, width + height * 9 + 9, 8 + width * 18, 111 + height * 18));
			}
		}

		//Quickslots
		for(int width = 0; width < 9; width++)
		{
			addSlotToContainer(new Slot(invPlayer, width, 8 + width * 18, 169));
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

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.waterLevel);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileEntity.fireLevel);
        par1ICrafting.sendProgressBarUpdate(this, 2, Math.round(this.tileEntity.getEnergy()));
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); var1++)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastWaterLevel != this.tileEntity.waterLevel)
            {
                var2.sendProgressBarUpdate(this, 0, this.tileEntity.waterLevel);
            }

            if (this.lastFireLevel != this.tileEntity.fireLevel)
            {
                var2.sendProgressBarUpdate(this, 1, this.tileEntity.fireLevel);
            }

            if (this.lastEnergyLevel != this.tileEntity.getEnergy())
            {
                var2.sendProgressBarUpdate(this, 2, Math.round(this.tileEntity.getEnergy()));
            }
        }

        this.lastWaterLevel = this.tileEntity.waterLevel;
        this.lastFireLevel = this.tileEntity.fireLevel;
        this.lastEnergyLevel = this.tileEntity.getEnergy();


    }


	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int bar, int value)
	{
	    super.updateProgressBar(bar, value);

	    if(bar == 0)
	    {
	    	this.tileEntity.waterLevel = value;
	    }
	    if(bar == 1)
	    {
	    	this.tileEntity.fireLevel = value;
	    }
	    if(bar == 2)
	    {
	    	this.tileEntity.powerProvider.setEnergyStored(value);
	    }


	    tileEntity.receiveGuiNetworkData(bar, value);
	}

}
