package deathrat.mods.btbees.items;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockBoilerTank extends ItemBlock
{

	public ItemBlockBoilerTank(int id)
	{
		super(id);
	}
	
	
	@Override
	public String getTextureFile()
	{
		return BetterThanBees.terrainTextures;
	}
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		return super.onItemUse(itemStack, entityPlayer, world, par4, par5, par6, par7, par8, par9, par10);
	}
}
