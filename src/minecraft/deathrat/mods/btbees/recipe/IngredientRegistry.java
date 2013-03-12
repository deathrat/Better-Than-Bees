package deathrat.mods.btbees.recipe;

import deathrat.mods.btbees.api.ICookingIngredient;
import deathrat.mods.btbees.api.ICookingSpice;
import net.minecraft.item.ItemStack;

public class IngredientRegistry
{

	public static boolean isItemIngredient(ItemStack is)
	{
		return (is.getItem() instanceof ICookingIngredient);
	}

	public static boolean isItemSpice(ItemStack is)
	{
		return (is.getItem() instanceof ICookingSpice);
	}

}
