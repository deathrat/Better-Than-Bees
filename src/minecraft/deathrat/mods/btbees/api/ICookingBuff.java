package deathrat.mods.btbees.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ICookingBuff
{
	public String getBuffName();
	public void setBuffName(String buffName);
	
	public float getBuffDuration();
	
	public void buffPlayer(EntityPlayer player);
	
}
