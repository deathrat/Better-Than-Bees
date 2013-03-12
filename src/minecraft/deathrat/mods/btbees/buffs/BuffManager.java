package deathrat.mods.btbees.buffs;

import java.util.ArrayList;
import java.util.HashMap;

import deathrat.mods.btbees.api.ICookingBuff;

import net.minecraft.entity.player.EntityPlayer;

public class BuffManager
{
	public static HashMap<EntityPlayer, BuffManager> playerList = new HashMap<EntityPlayer, BuffManager>();
	// public ArrayList<ICookingBuff> buffs;

	public EntityPlayer entityPlayer;

	public BuffManager(EntityPlayer player)
	{
		entityPlayer = player;
		// buffs = new ArrayList<ICookingBuff>();
		BuffManager.playerList.put(player, this);
	}

	public void addBuff(ICookingBuff buff)
	{
		buff.buffPlayer(entityPlayer);
	}
}
