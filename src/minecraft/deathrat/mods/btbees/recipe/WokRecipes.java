package deathrat.mods.btbees.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import deathrat.mods.btbees.api.ICookingResult;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WokRecipes
{
	public static ArrayList<WokRecipe> recipes = new ArrayList<WokRecipe>();
	
	public static ICookingResult getResult(Item[] is)
	{
		for(int i=0; i < recipes.size(); i++)
		{
			if(recipes.get(i).isEqual(is))
			{
				return recipes.get(i).getResult();
			}
		}
		return null;
	}
	
	public static boolean isInRecipe(Item is)
	{
		for(int i=0; i < recipes.size(); i++)
		{
			if(recipes.get(i).getItems().contains(is))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isRecipe(Item[] is)
	{
		for(int i=0; i < recipes.size(); i++)
		{
			if(recipes.get(i).isEqual(is))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void addRecipe(Item[] is, ICookingResult result)
	{
		recipes.add(new WokRecipe(is, result));
	}

}
