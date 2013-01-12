package deathrat.mods.btbees.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderRice implements IItemRenderer
{

	public ItemRenderRice()
	{

	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{

	}

}
