package deathrat.mods.btbees.api;

import java.util.ArrayList;

public class BuffRegistry
{
	public static ArrayList<ICookingBuff> buffs = new ArrayList<ICookingBuff>();
	
	public static void addBuff(ICookingBuff buff)
	{
		buffs.add(buff);
	}
	
	public static ICookingBuff getBuff(String buffName)
	{
		for (ICookingBuff buff : buffs)
		{
			if(buff.getBuffName() == buffName)
			{
				return buff;
			}
		}
		return null;
	}

}
