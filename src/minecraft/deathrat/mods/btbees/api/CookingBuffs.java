package deathrat.mods.btbees.api;

import java.util.ArrayList;
import java.util.HashMap;

public class CookingBuffs
{
	HashMap<String, ICookingBuff> cookingBuffs = new HashMap<String, ICookingBuff>();

	public CookingBuffs()
	{
	}

	public HashMap<String, ICookingBuff> getCookingBuffs()
	{
		return cookingBuffs;
	}

	public void addCookingBuff(ICookingBuff buff)
	{
		cookingBuffs.put(buff.getBuffName(), buff);
	}

	public ICookingBuff getCookingBuff(String s)
	{
		return cookingBuffs.get(s);
	}

	public void setCookingBuffs(HashMap<String, ICookingBuff> cookingBuffs)
	{
		this.cookingBuffs = cookingBuffs;
	}
}
