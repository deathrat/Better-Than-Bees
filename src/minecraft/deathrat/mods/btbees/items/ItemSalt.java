package deathrat.mods.btbees.items;

import net.minecraft.item.Item;
import deathrat.mods.btbees.BetterThanBees;

public class ItemSalt extends Item
{

	public ItemSalt(int par1)
	{
		super(par1);
	}
	
	
	@Override
	public String getTextureFile()
	{
		return BetterThanBees.itemTextures;
	}
	
}
