package deathrat.mods.btbees.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import deathrat.mods.btbees.api.ICookingResult;

public class WokRecipes
{
	public static List<WokRecipe> recipes = new ArrayList<WokRecipe>();

	public static boolean isRecipe(Object[] stacks)
	{
		for (WokRecipe recipe : recipes)
		{
			if (recipe.matches(stacks))
			{
				return true;
			}
		}
		return false;
	}

	public static WokRecipe getRecipe(Object[] stacks)
	{
		for (WokRecipe recipe : recipes)
		{
			if (recipe.matches(stacks))
			{
				return recipe;
			}
		}
		return null;
	}

	private static ArrayList addRecipe(Object... is)
	{
		ArrayList objList = new ArrayList();
		Object[] array = is;

		for (int i = 0; i < array.length; i++)
		{
			Object obj = array[i];

			if (obj instanceof ItemStack)
			{
				objList.add(((ItemStack) obj).copy());
			}
			else if (obj instanceof Item)
			{
				objList.add(new ItemStack((Item) obj));
			}
			else
			{
				if (!(obj instanceof Block))
					throw new RuntimeException("Invalid recipe for Wok");
				objList.add(new ItemStack((Block) obj));
			}
		}
		return objList;
	}

	public static void addRecipe(ICookingResult result, Object... is)
	{
		ArrayList objList = WokRecipes.addRecipe(is);
		recipes.add(new WokRecipe(objList, result));
	}

	public static void addRecipe(Item result, int resultMeta, Object... is)
	{
		ArrayList objList = WokRecipes.addRecipe(is);
		recipes.add(new WokRecipe(objList, result, resultMeta));
	}

	public static void addRecipe(Item result, Object... is)
	{
		addRecipe(result, 0, is);
	}

	public static void addRecipe(ItemStack result, Object... is)
	{
		ArrayList objList = WokRecipes.addRecipe(is);
		recipes.add(new WokRecipe(objList, result.getItem(), result.getItemDamage()));
	}

	public List getRecipeList()
	{
		return this.recipes;
	}
}
