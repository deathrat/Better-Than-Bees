package deathrat.mods.btbees.gui;

import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;
import deathrat.mods.btbees.api.ICookingSpice;
import deathrat.mods.btbees.tileentity.TileEntityWok;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SlotResult extends Slot
{

	public SlotResult(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}
	
	@Override
	public void putStack(ItemStack is)
	{
		if(is != null && is.getTagCompound() == null)
		{
			is.setTagCompound(new NBTTagCompound());
		}
		super.putStack(is);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack is)
	{
		Item spice = inventory.getStackInSlot(2).getItem();
		ItemStack spiceStack = inventory.getStackInSlot(2);

		if ((spiceStack != null) && (spice instanceof ICookingSpice))
		{
			ICookingResult cookingItem = (ICookingResult) is.getItem();
			ICookingBuff buff = ((ICookingSpice) spice).getCookingBuff();
			
			if(buff != null)
			{
				NBTTagCompound tag = is.getTagCompound();
				
				tag.setString("cookingBuff", buff.getBuffName() );
				--spiceStack.stackSize;
			}

		}
		if (inventory.getStackInSlot(3) != null)
		{
			((TileEntityWok) inventory).clearBuffer();
			((TileEntityWok) inventory).updateResult();
		}
		super.onPickupFromSlot(entityPlayer, is);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}
}
