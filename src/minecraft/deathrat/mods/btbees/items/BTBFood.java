package deathrat.mods.btbees.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;

public class BTBFood extends ItemFood implements ICookingResult
{
	public boolean hasBowl = false;
	public Icon foodIcon;
	private String name;

	public BTBFood(int id, int healAmount, float saturation, boolean isWolfFood)
	{
		super(id, healAmount, saturation, isWolfFood);
	}

	public BTBFood(int id, int healAmount, boolean isWolfFood)
	{
		this(id, healAmount, 0.6F, isWolfFood);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg)
	{
		foodIcon = iconReg.registerIcon("btbees:" + getName());
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass)
	{
		return foodIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return foodIcon;
	}

	public void setHasBowl(boolean hasBowl)
	{
		this.hasBowl = hasBowl;
	}

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (hasBowl)
		{
			super.onEaten(itemStack, world, entityPlayer);
			return new ItemStack(Item.bowlEmpty);
		}

		eatResult(itemStack, world, entityPlayer);
		return super.onEaten(itemStack, world, entityPlayer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer entityPlayer, List list, boolean par4)
	{
		if (is.getTagCompound() != null)
		{
			String buffStr = is.getTagCompound().getString("cookingBuff");
			if (buffStr != null || buffStr != "null" || buffStr != "")
			{
				ICookingBuff buff = BuffRegistry.getBuff(buffStr);
				if (buff != null && buff.getBuffName() != "null")
					list.add("Buff: " + buff.getBuffName());
			}
		}
	}

	public String getName()
	{
		return name;
	}

	public Item setName(String name)
	{
		this.name = name;
		return this;
	}

	@Override
	public void eatResult(ItemStack is, World world, EntityPlayer entityPlayer)
	{
		String buffName = is.getTagCompound().getString("cookingBuff");

		ICookingBuff buff = BuffRegistry.getBuff(buffName);
		if (buff != null)
		{
			buff.buffPlayer(entityPlayer);
		}
	}
}
