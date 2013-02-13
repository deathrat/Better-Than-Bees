package deathrat.mods.btbees.items;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.item.Item;

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
