package deathrat.mods.btbees.power;

import net.minecraftforge.common.ForgeDirection;
import thermalexpansion.api.core.IPowerProviderAdv;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerProvider;

public class PowerProviderBTB extends PowerProvider implements IPowerProviderAdv
{

	public PowerProviderBTB()
	{
		powerLoss = 0;
		powerLossRegularity = 72000;

		configure(0, 0);
	}

	public void configure(int maxEnergyReceived, int maxStoredEnergy)
	{
		super.configure(0, 0, maxEnergyReceived, 0, maxStoredEnergy);
	}

	@Override
	public boolean update(IPowerReceptor receptor)
	{
		return false;
	}

	@Override
	public void receiveEnergy(float quantity, ForgeDirection from)
	{
		energyStored += quantity;

		if (energyStored > maxEnergyStored)
			energyStored = maxEnergyStored;
	}

	@Override
	public void addEnergy(float quantity)
	{
		energyStored += quantity;

		if (energyStored > maxEnergyStored)
			energyStored = maxEnergyStored;
	}

	@Override
	public void subtractEnergy(float quantity)
	{
		energyStored -= quantity;

		if (energyStored < 0.0F)
			energyStored = 0.0F;
	}

	@Override
	public void setEnergyStored(float quantity)
	{
		energyStored = quantity;

		if (energyStored > maxEnergyStored)
			energyStored = maxEnergyStored;
		else if (energyStored < 0.0F)
			energyStored = 0.0F;
	}

	@Override
	public void roundEnergyStored()
	{
		energyStored = Math.round(energyStored);
	}

	@Override
	public float getEnergyStored()
	{
		return energyStored;
	}

}
