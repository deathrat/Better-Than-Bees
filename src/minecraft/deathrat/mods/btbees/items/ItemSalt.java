package deathrat.mods.btbees.items;

import net.minecraft.item.Item;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.api.BuffRegistry;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingSpice;
import deathrat.mods.btbees.buffs.HealBuff;

public class ItemSalt extends Item implements ICookingSpice
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

	@Override
	public ICookingBuff getCookingBuff()
	{
		return BuffRegistry.getBuff("Heal Buff");
	}

}
