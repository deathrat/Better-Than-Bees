package deathrat.mods.btbees.gui;

import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;
import deathrat.mods.btbees.api.ICookingSpice;
import deathrat.mods.btbees.tileentity.TileEntityWok;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot
{

	public SlotResult(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack is)
	{
		super.onPickupFromSlot(entityPlayer, is);
		
//		Item spice = inventory.getStackInSlot(2).getItem();
//		ItemStack spiceStack = inventory.getStackInSlot(2);
//		
//		if((spice != null) && (spice instanceof ICookingSpice))
//		{
//			ICookingBuff buff = ((ICookingSpice)spice).getCookingBuff();
//			ICookingResult cookingItem = (ICookingResult)is.getItem();
//			
//			cookingItem.setCookingBuff(buff);
//			--spiceStack.stackSize;
//			
//		}
//		--is.stackSize;
		if(inventory.getStackInSlot(3) != null)
		{
			((TileEntityWok) inventory).clearBuffer();
			((TileEntityWok) inventory).updateResult();
		}
		
		if(inventory.getStackInSlot(2) != null)
		{
			
		}
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}
}
