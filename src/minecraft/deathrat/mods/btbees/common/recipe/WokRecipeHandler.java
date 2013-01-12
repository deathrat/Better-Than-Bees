package deathrat.mods.btbees.common.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import deathrat.mods.btbees.common.BetterThanBees;

public class WokRecipeHandler
{
	public static final WokRecipeHandler instance = new WokRecipeHandler();

    private List recipes = new ArrayList();


	public WokRecipeHandler()
	{
		this.addShapelessRecipe(new ItemStack(BetterThanBees.cookedRiceBall), new Object[] {BetterThanBees.uncookedRice});
	}

	public void addShapelessRecipe(ItemStack itemStack, Object ... ingredientObject )
	{
        ArrayList var3 = new ArrayList();
        Object[] var4 = ingredientObject;
        int var5 = ingredientObject.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        this.recipes.add(new ShapelessRecipes(itemStack, var3));
	}

	public static final WokRecipeHandler getInstance()
	{
		return instance;
	}


}
