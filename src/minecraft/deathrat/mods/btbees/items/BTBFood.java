package deathrat.mods.btbees.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.api.ICookingBuff;
import deathrat.mods.btbees.api.ICookingResult;

public class BTBFood extends ItemFood implements ICookingResult
{
	public boolean hasBowl = false;
	public ICookingBuff buff = null;
	
	public BTBFood(int id, int healAmount, float saturation, boolean isWolfFood)
	{
		super(id, healAmount, saturation, isWolfFood);
	}
	
    public BTBFood(int id, int healAmount, boolean isWolfFood)
    {
        this(id, healAmount, 0.6F, isWolfFood);
    }
    
    public void setCookingBuff(ICookingBuff buff)
    {
    	this.buff = buff;
    }
    
    public ICookingBuff getCookingBuff()
    {
    	return buff;
    }
    
	@Override
	public String getTextureFile()
	{
		return BetterThanBees.itemTextures;
	}
	
	public void setHasBowl(boolean hasBowl)
	{
		this.hasBowl = hasBowl;
	}
	
	@Override
	public ItemStack onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if(hasBowl)
		{
			super.onFoodEaten(itemStack, world, entityPlayer);
			return new ItemStack(Item.bowlEmpty);
		}
		buff.buffPlayer(entityPlayer);
		
		return super.onFoodEaten(itemStack, world, entityPlayer);
	}
}
