package deathrat.mods.btbees.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;

public class ItemSheepMeat extends ItemFood
{

	public ItemSheepMeat(int id, int healAmount, float saturation, boolean isWolfFood)
	{
		super(id, healAmount, saturation, isWolfFood);
		setHasSubtypes(true);
		setMaxDamage(0);
		setItemName("sheepMeat");
//		setCreativeTab(BetterThanBees.customTab);
	}
	
	@Override
	public String getTextureFile()
	{
		return BetterThanBees.itemTextures;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int i)
	{
		if(i > 1)
			return 5 + i;
		
		return 7 + i;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemStack)
	{
		switch(itemStack.getItemDamage())
		{
		case 1:
			return getItemName()+".cookedMutton";
		case 2:
			return getItemName()+".rawLamb";
		case 3:
			return getItemName()+".cookedLamb";
		default:
			return getItemName()+".rawMutton";
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List list)
	{
		for(int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}