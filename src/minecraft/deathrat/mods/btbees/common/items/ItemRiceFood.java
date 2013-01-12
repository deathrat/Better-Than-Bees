package deathrat.mods.btbees.common.items;

import net.minecraft.item.ItemFood;
import deathrat.mods.btbees.common.BetterThanBees;

public class ItemRiceFood extends ItemFood
{

	public ItemRiceFood(int par1, int par2, boolean par3)
	{
		super(par1, par2, par3);
	}

    public ItemRiceFood(int par1, int par2, int par3, boolean par4)
    {
    	super(par1, par2, par3, par4);
    }

	public String getTextureFile()
	{
		return BetterThanBees.getItemTextures();
	}

}
