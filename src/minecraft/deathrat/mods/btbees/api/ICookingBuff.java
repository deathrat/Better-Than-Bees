package deathrat.mods.btbees.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ICookingBuff
{
	public String getBuffName();

	public void setBuffName(String buffName);

	public int getBuffDuration();

	public boolean isActive();

	public void buffPlayer(EntityPlayer player);

	public void unBuffPlayer(EntityPlayer player);

}
