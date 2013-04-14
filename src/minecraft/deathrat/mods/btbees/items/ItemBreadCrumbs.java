package deathrat.mods.btbees.items;

import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingIngredient;
import deathrat.mods.btbees.api.ICookingResult;
import net.minecraft.item.Item;

public class ItemBreadCrumbs extends Item implements ICookingIngredient, ICookingResult
{

	public ItemBreadCrumbs(int id)
	{
		super(id);
	}

}
