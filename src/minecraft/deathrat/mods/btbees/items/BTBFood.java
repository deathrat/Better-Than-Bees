package deathrat.mods.btbees.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;

public class BTBFood extends ItemFood implements ICookingResult
{
	public boolean hasBowl = false;

	public BTBFood(int id, int healAmount, float saturation, boolean isWolfFood)
	{
		super(id, healAmount, saturation, isWolfFood);
	}

	public BTBFood(int id, int healAmount, boolean isWolfFood)
	{
		this(id, healAmount, 0.6F, isWolfFood);
	}

	@Override
	public String getTextureFile()
	{
		return BetterThanBees.itemTextures;
	}

	public void setHasBowl(boolean hasBowl)
	{
		this.hasBowl = hasBowl;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (hasBowl)
		{
			super.onFoodEaten(itemStack, world, entityPlayer);
			return new ItemStack(Item.bowlEmpty);
		}

		String buffName = itemStack.getTagCompound().getString("cookingBuff");

		ICookingBuff buff = BuffRegistry.getBuff(buffName);
		if (buff != null)
		{
			buff.buffPlayer(entityPlayer);
		}

		return super.onFoodEaten(itemStack, world, entityPlayer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer entityPlayer, List list, boolean par4)
	{
		if(is.getTagCompound() != null)
		{
			String buffStr = is.getTagCompound().getString("cookingBuff");
			if (buffStr != null || buffStr != "null")
			{
				ICookingBuff buff = BuffRegistry.getBuff(buffStr);
				if (buff != null && buff.getBuffName() != "null")
					list.add("Buff: " + buff.getBuffName());
			}
		}
	}
}
