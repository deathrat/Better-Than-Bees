package deathrat.mods.btbees.buffs;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import deathrat.mods.btbees.api.ICookingBuff;

public class SpeedBuff implements ICookingBuff
{
	public String buffName;
	
	@Override
	public String getBuffName()
	{
		return buffName;
	}

	@Override
	public void setBuffName(String s)
	{
		buffName = s;
	}

	@Override
	public void buffPlayer(EntityPlayer player)
	{
		Random rand = new Random();
		player.landMovementFactor = 0.4F;
	}

	@Override
	public float getBuffDuration()
	{
		return 15.0F;
	}
	
}
