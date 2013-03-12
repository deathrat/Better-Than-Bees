package deathrat.mods.btbees.buffs;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.minecraft.entity.player.EntityPlayer;
import deathrat.mods.btbees.api.ICookingBuff;

public class SpeedBuff implements ICookingBuff
{
	public String buffName;
	public boolean isActive;

	public SpeedBuff()
	{
	}

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
		player.landMovementFactor = 0.4F;
		isActive = true;
		updateBuff(player);
	}

	@Override
	public int getBuffDuration()
	{
		return 15;
	}

	@Override
	public void unBuffPlayer(EntityPlayer player)
	{
		player.landMovementFactor = 0.0F;
		isActive = false;
	}

	@Override
	public boolean isActive()
	{
		return isActive;
	}

	public void updateBuff(EntityPlayer entityPlayer)
	{
		long startingTime = System.currentTimeMillis();
		while (isActive())
		{
			if (startingTime < startingTime + TimeUnit.SECONDS.toNanos(getBuffDuration()))
			{
				continue;
			}
			else if (startingTime >= startingTime + TimeUnit.SECONDS.toNanos(getBuffDuration()))
			{
				updateBuff(entityPlayer);
			}
		}
		return;
	}

}
