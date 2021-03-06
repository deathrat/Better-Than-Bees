package deathrat.mods.btbees.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.recipe.IngredientRegistry;
import deathrat.mods.btbees.tileentity.TileEntityWok;

public class ContainerWok extends Container
{
	protected TileEntityWok tileEntity;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;

	public ContainerWok(InventoryPlayer invPlayer, TileEntityWok te)
	{
		tileEntity = te;

		// Active Ingredient (0)
		addSlotToContainer(new Slot(tileEntity, 0, 8, 29));

		// Fuel Slot (1)
		addSlotToContainer(new Slot(tileEntity, 1, 8, 64));

		// Spices Slot (2)
		addSlotToContainer(new SlotSpices(tileEntity, 2, 115, 51));

		// Ingredients (3-6)
		addSlotToContainer(new SlotIngredient(tileEntity, 3, 61 + 0 * 18, 29));
		addSlotToContainer(new SlotIngredient(tileEntity, 4, 61 + 1 * 18, 29));
		addSlotToContainer(new SlotIngredient(tileEntity, 5, 61 + 2 * 18, 29));
		addSlotToContainer(new SlotIngredient(tileEntity, 6, 61 + 3 * 18, 29));

		// Result Slot (7)
		addSlotToContainer(new SlotResult(tileEntity, 7, 150, 29));

		bindPlayerInventory(invPlayer);
	}

	protected void bindPlayerInventory(InventoryPlayer invPlayer)
	{
		// Player Inventory
		for (int height = 0; height < 3; height++)
		{
			for (int width = 0; width < 9; width++)
			{
				addSlotToContainer(new Slot(invPlayer, width + height * 9 + 9, 8 + width * 18, 84 + height * 18));
			}
		}

		// Quickslots
		for (int width = 0; width < 9; width++)
		{
			addSlotToContainer(new Slot(invPlayer, width, 8 + width * 18, 142));
		}
	}

	@Override
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

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return tileEntity.isUseableByPlayer(player);
	}

	@Override
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileEntity.furnaceCookTime);
		crafting.sendProgressBarUpdate(this, 1, this.tileEntity.furnaceBurnTime);
		crafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int var1 = 0; var1 < this.crafters.size(); ++var1)
		{
			ICrafting var2 = (ICrafting) this.crafters.get(var1);

			if (this.lastCookTime != this.tileEntity.furnaceCookTime)
			{
				var2.sendProgressBarUpdate(this, 0, this.tileEntity.furnaceCookTime);
			}

			if (this.lastBurnTime != this.tileEntity.furnaceBurnTime)
			{
				var2.sendProgressBarUpdate(this, 1, this.tileEntity.furnaceBurnTime);
			}

			if (this.lastItemBurnTime != this.tileEntity.currentItemBurnTime)
			{
				var2.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.tileEntity.furnaceCookTime;
		this.lastBurnTime = this.tileEntity.furnaceBurnTime;
		this.lastItemBurnTime = this.tileEntity.currentItemBurnTime;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slotIndex);

		if (slotObject != null && slotObject.getHasStack())
		{
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			if (slotIndex < 9)
			{
				if (!this.mergeItemStack(stackInSlot, 9, 44, true))
				{
					return null;
				}
			}
			else if (slotIndex <= 44 && slotIndex >= 9)
			{
				if (GameRegistry.getFuelValue(stackInSlot) > 0)
				{
					if (!this.mergeItemStack(stackInSlot, 1, 2, false))
					{
						return null;
					}
				}
				else if (IngredientRegistry.isItemSpice(stackInSlot))
				{
					if (!this.mergeItemStack(stackInSlot, 2, 3, false))
					{
						return null;
					}
				}
				else
				{
					if (!this.mergeItemStack(stackInSlot, 0, 2, false))
					{
						return null;
					}
				}
			}

			if (stackInSlot.stackSize == 0)
			{
				slotObject.putStack(null);
			}
			else
			{
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize)
			{
				return null;
			}

			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}
}
