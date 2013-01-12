package deathrat.mods.btbees.common;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BTBTab extends CreativeTabs
{
	public BTBTab(int l, String uniqueID)
	{
		super(l, uniqueID);
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return BetterThanBees.cookedRiceBowl.shiftedIndex;
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
		return ""+this.getTabLabel();
	}
}
