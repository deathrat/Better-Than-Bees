package deathrat.mods.btbees.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import deathrat.mods.btbees.api.ICookingResult;

import net.minecraft.item.ItemStack;

public class WokRecipes
{
	public static HashMap<ItemStack[], ICookingResult> recipes;
	
	public static ICookingResult getResult(ItemStack[] is)
	{
		if(recipes.containsKey(is))
		{
			return recipes.get(is);
		}
		return null;
	}
	
	
	public static void addRecipe(ItemStack[] is, ICookingResult result)
	{
		ItemStack[] tempIs = is;
		Arrays.sort(tempIs);
		recipes.put(tempIs, result);
	}

}
