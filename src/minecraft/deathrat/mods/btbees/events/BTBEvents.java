package deathrat.mods.btbees.events;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class BTBEvents
{

	@ForgeSubscribe
	public void mobDrops(LivingDeathEvent event)
	{
		if (!event.entityLiving.worldObj.isRemote)
		{
			if (event.entityLiving instanceof EntitySheep)
			{
				if (((EntitySheep) event.entityLiving).getGrowingAge() < 0)
				{
					event.entityLiving.entityDropItem(new ItemStack(BetterThanBees.sheepMeat, 1, 2), 0.0F);
				}
				else
				{
					event.entityLiving.entityDropItem(new ItemStack(BetterThanBees.sheepMeat, 1, 0), 0.0F);
				}
			}
		}
	}
}
