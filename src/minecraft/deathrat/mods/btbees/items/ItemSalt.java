package deathrat.mods.btbees.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingSpice;

public class ItemSalt extends Item implements ICookingSpice
{
	public Icon saltIcon;

	public ItemSalt(int par1)
	{
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg)
	{
		saltIcon = iconReg.registerIcon("btbees:salt");
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass)
	{
		return saltIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return saltIcon;
	}

	@Override
	public ICookingBuff getCookingBuff()
	{
		return BuffRegistry.getBuff("Heal Buff");
	}

}
