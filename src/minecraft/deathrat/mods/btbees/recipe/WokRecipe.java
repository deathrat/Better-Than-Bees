package deathrat.mods.btbees.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import deathrat.mods.btbees.api.ICookingResult;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WokRecipe
{
	private List items;
	private ICookingResult result;

	public WokRecipe(List is)
	{
		this.items = is;
	}

	public WokRecipe(List is, ICookingResult result)
	{
		this(is);
		this.result = result;
	}

	public List getItems()
	{
		return items;
	}

	public ICookingResult getResult()
	{
		return result;
	}

	public ItemStack getResultStack()
	{
		return new ItemStack((Item) result, 1).copy();
	}

	public boolean matches(Object[] itemStacks)
	{
		ArrayList stackList = new ArrayList(items);
		for (int i = 0; i < 4; i++)
		{
			ItemStack is = (ItemStack) itemStacks[i];

			if (is != null)
			{
				boolean var1 = false;
				Iterator it = stackList.iterator();

				while (it.hasNext())
				{
					ItemStack is2 = (ItemStack) it.next();

					if (is.itemID == is2.itemID && (is2.getItemDamage() == -1 || is.getItemDamage() == is2.getItemDamage()))
					{
						var1 = true;
						stackList.remove(is2);
						break;
					}
				}

				if (!var1)
					return false;
			}
		}
		return stackList.isEmpty();
	}

	public int getRecipeSize()
	{
		return this.items.size();
	}
}
