package deathrat.mods.btbees.buffs;


import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class BuffManager
{
	public static HashMap<EntityPlayer, BuffManager> playerList = new HashMap<EntityPlayer, BuffManager>();
	
	public EntityPlayer entityPlayer;
	
	public BuffManager(EntityPlayer player)
	{
		entityPlayer = player;
		BuffManager.playerList.put(player, this);
	}
	
}
