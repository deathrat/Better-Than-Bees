package deathrat.mods.btbees.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import deathrat.mods.btbees.api.ICookingSpice;

public class SlotSpices extends Slot
{

	public SlotSpices(IInventory inv, int par2, int par3, int par4)
	{
		super(inv, par2, par3, par4);
	}

	@Override
	public void putStack(ItemStack is)
	{
		if (is != null && is.getTagCompound() == null)
		{
			is.setTagCompound(new NBTTagCompound());
		}

		super.putStack(is);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack is)
	{
		ItemStack resultStack = inventory.getStackInSlot(7);

		if (resultStack != null)
		{
			NBTTagCompound tempNBT = resultStack.getTagCompound();
			tempNBT.setString("cookingBuff", "");
		}

		super.onPickupFromSlot(entityPlayer, is);
	}

	@Override
	public boolean isItemValid(ItemStack is)
	{
		return (is.getItem() instanceof ICookingSpice);
	}

}
