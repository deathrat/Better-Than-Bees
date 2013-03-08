package deathrat.mods.btbees.events;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;
import deathrat.mods.btbees.buffs.BuffManager;

public class PlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		new BuffManager(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		BuffManager.playerList.remove(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		
	}

}
