package deathrat.mods.btbees.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import deathrat.mods.btbees.BetterThanBees;

public class BTBFuelHandler implements IFuelHandler
{

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if (fuel.itemID == BetterThanBees.riceHusk.itemID)
		{
			return 140;
		}
		int var1 = fuel.getItem().itemID;
		Item var2 = fuel.getItem();

		if (fuel.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
		{
			Block var3 = Block.blocksList[var1];

			if (var3 == Block.woodSingleSlab)
			{
				return 150;
			}

			if (var3.blockMaterial == Material.wood)
			{
				return 300;
			}
		}

		if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
			return 200;
		if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD"))
			return 200;
		if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD"))
			return 200;
		if (var1 == Item.stick.itemID)
			return 100;
		if (var1 == Item.coal.itemID)
			return 1600;
		if (var1 == Item.bucketLava.itemID)
			return 20000;
		if (var1 == Block.sapling.blockID)
			return 100;
		if (var1 == Item.blazeRod.itemID)
			return 2400;
		return 0;
	}

}
