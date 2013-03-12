package deathrat.mods.btbees.recipe;

import java.util.HashSet;

import deathrat.mods.btbees.api.ICookingResult;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WokRecipe
{
	private HashSet<Item> items = new HashSet<Item>();
	private ICookingResult result;
	
	public WokRecipe(Item[] is, ICookingResult result)
	{
		for(int i=0; i < is.length; i++)
		{
			items.add(is[i]);
		}
		this.result = result;
	}
	
	public WokRecipe(Item is1, Item is2, Item is3, Item is4, ICookingResult result)
	{
		this(new Item[] {is1, is2, is3, is4}, result);
	}
	
	public HashSet<Item> getItems()
	{
		return items;
	}
	
	public ICookingResult getResult()
	{
		return result;
	}
	
	public boolean isEqual(Item[] is)
	{
		HashSet<Item> recipeItems = new HashSet<Item>();
		for(int i=0; i < is.length; i++)
		{
			if(is[i] != null)
				recipeItems.add(is[i]);
			else
				break;
		}
//		if(recipeItems.containsAll(this.getItems()))
//		{
//			return true;
//		}
		for(int i=0; i < getItems().size(); i++)
		{
			if(!getItems().contains(is[i]))
				return false;
		}
		return true;
	}
}
