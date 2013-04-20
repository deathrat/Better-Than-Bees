package deathrat.mods.btbees.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICookingResult
{
	public void eatResult(ItemStack is, World world, EntityPlayer player);
}
