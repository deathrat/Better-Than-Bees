package deathrat.mods.btbees.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;

public class ItemSheepMeat extends ItemFood implements ICookingResult
{
	public Icon[] lambIcons = new Icon[2];

	public ItemSheepMeat(int id, int healAmount, float saturation, boolean isWolfFood)
	{
		super(id, healAmount, saturation, isWolfFood);
		setHasSubtypes(true);
		setMaxDamage(0);
		// setCreativeTab(BetterThanBees.customTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg)
	{
		lambIcons[0] = iconReg.registerIcon("btbees:lamb" + "0");
		lambIcons[1] = iconReg.registerIcon("btbees:lamb" + "1");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i)
	{
		if (i >= 2)
			return lambIcons[1];

		return lambIcons[0];
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		switch (itemStack.getItemDamage())
		{
			default:
				return getUnlocalizedName() + ".rawMutton";
			case 1:
				return getUnlocalizedName() + ".rawLamb";
			case 2:
				return getUnlocalizedName() + ".cookedMutton";
			case 3:
				return getUnlocalizedName() + ".cookedLamb";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(itemID, 1, i));
		}
	}

	@Override
	public void eatResult(ItemStack is, World world, EntityPlayer player)
	{
		String buffName = is.getTagCompound().getString("cookingBuff");

		ICookingBuff buff = BuffRegistry.getBuff(buffName);
		if (buff != null)
		{
			buff.buffPlayer(player);
		}
	}
}