package deathrat.mods.btbees.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import deathrat.mods.btbees.api.ICookingIngredient;
import deathrat.mods.btbees.api.ICookingResult;

public class ItemBreadCrumbs extends Item implements ICookingIngredient, ICookingResult
{

	public ItemBreadCrumbs(int id)
	{
		super(id);
	}

	@Override
	public void eatResult(ItemStack is, World world, EntityPlayer player)
	{

	}

}
