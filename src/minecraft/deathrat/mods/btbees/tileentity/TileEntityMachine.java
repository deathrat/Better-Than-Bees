package deathrat.mods.btbees.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.Player;
import deathrat.mods.btbees.power.PowerProviderBTB;

public abstract class TileEntityMachine extends TileEntity implements IPowerReceptor
{
	public PowerProviderBTB powerProvider;

	public boolean isActive;

	protected float minPower = 0.5F;
	protected int maxPower = 5;
	protected int maxEnergy = maxPower * 1200;
	protected int minPowerLevel = (2 * maxEnergy / 10);
	protected int maxPowerLevel = (8 * maxEnergy / 10);
	protected int energyRamp = (maxPowerLevel / maxPower);

	public TileEntityMachine()
	{
		powerProvider = new PowerProviderBTB();
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		float energy = this.powerProvider.getEnergyStored();
		tagCompound.setFloat("energyLevel", energy);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		try
		{
			float energy = tagCompound.getFloat("energyLevel");
			this.powerProvider.setEnergyStored(energy);
			if (Float.isNaN(powerProvider.getEnergyStored()))
				powerProvider.setEnergyStored(0.0F);
		}
		catch (Exception e)
		{
			powerProvider.setEnergyStored(0.0F);
		}
	}

	public float getEnergy()
	{
		return powerProvider.getEnergyStored();
	}

	public float getMaxEnergy()
	{
		return maxEnergy;
	}

	public float getPower()
	{
		if (!isActive)
			return 0.0F;
		if (powerProvider.getMaxEnergyStored() > maxPowerLevel)
			return maxPower;
		if (powerProvider.getMaxEnergyStored() < minPowerLevel)
			return minPower;

		int intPower = (int) (10.0F * powerProvider.getEnergyStored() / energyRamp);
		return intPower / 10.0F;
	}

	public float getMaxPower()
	{
		return maxPower;
	}

	@Override
	public void setPowerProvider(IPowerProvider provider)
	{
		powerProvider = (PowerProviderBTB) provider;
	}

	@Override
	public IPowerProvider getPowerProvider()
	{
		return powerProvider;
	}

	@Override
	public void doWork()
	{
	}

	@Override
	public int powerRequest()
	{
		if (powerProvider.getEnergyStored() == powerProvider.getMaxEnergyStored())
		{
			return 0;
		}
		return (int) Math.ceil(Math.min(powerProvider.getMaxEnergyReceived(), powerProvider.getMaxEnergyStored() - powerProvider.getEnergyStored()));
	}

	public int getScaledEnergyStored(int scale)
	{
		return Math.round(powerProvider.getEnergyStored() * scale / powerProvider.getMaxEnergyStored());
	}

	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, Player player, ByteArrayDataInput data, float energyLevel)
	{
		try
		{
			this.powerProvider.setEnergyStored(energyLevel);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
