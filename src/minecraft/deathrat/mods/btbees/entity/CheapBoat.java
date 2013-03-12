package deathrat.mods.btbees.entity;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CheapBoat extends EntityBoat
{

	public CheapBoat(World world)
	{
		super(world);
	}

	public CheapBoat(World world, double x, double y, double z)
	{
		this(world);
		setPosition(x, y, z);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2 * 10);
			this.setBeenAttacked();

			if (par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode)
			{
				this.setDamageTaken(100);
			}

			if (this.getDamageTaken() > 40)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				this.dropItemWithOffset(BetterThanBees.boatItem.itemID, 1, 0.0F);
				this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}

}
