package deathrat.mods.btbees.buffs;

import net.minecraft.entity.player.EntityPlayer;
import deathrat.mods.btbees.api.ICookingBuff;

public class HealBuff implements ICookingBuff
{
	public String buffName;
	public int healAmount;
	
	public HealBuff()
	{
		this.healAmount = 2;
		this.buffName = "Heal Buff";
	}
	
	public HealBuff(int i)
	{
		this.buffName = "Heal Buff";
		this.healAmount = i;
	}

	@Override
	public String getBuffName()
	{
		return buffName;
	}

	@Override
	public void setBuffName(String buffName)
	{
		this.buffName = buffName;
	}

	@Override
	public int getBuffDuration()
	{
		return 0;
	}

	@Override
	public boolean isActive()
	{
		return false;
	}

	@Override
	public void buffPlayer(EntityPlayer player)
	{
		player.heal(healAmount);
	}

	@Override
	public void unBuffPlayer(EntityPlayer player)
	{
	}

}
