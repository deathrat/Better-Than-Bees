package deathrat.mods.btbees.gui;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;

public class BTBCreativeTab extends CreativeTabs
{
	public BTBCreativeTab(int l, String uniqueID)
	{
		super(l, uniqueID);
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return BetterThanBees.cookedRiceBowl.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "Better Than Bees";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "" + this.getTabLabel();
	}
}
