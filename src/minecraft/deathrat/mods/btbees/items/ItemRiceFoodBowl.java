package deathrat.mods.btbees.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import deathrat.mods.btbees.BetterThanBees;

public class ItemRiceFoodBowl extends ItemFood
{


    public ItemRiceFoodBowl(int par1, int par2, boolean par3)
    {
		super(par1, par2, par3);
	}

    public ItemRiceFoodBowl(int par1, int par2, int par3, boolean par4)
    {
    	super(par1, par2, par3, par4);
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Item.bowlEmpty);
    }

	public String getTextureFile()
	{
		return BetterThanBees.getItemTextures();
	}
}
