package deathrat.mods.btbees.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityBoilerTank extends TileEntity implements ITankContainer
{
	public LiquidTank tank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME * 8);

	
	public TileEntityBoilerTank()
	{
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}
	
	public int getScaledWaterLevel(int i)
	{
		return this.tank.getLiquid().amount * i / this.tank.getCapacity();
	}

	public boolean hasWater()
	{
		return this.tank.getLiquid().amount > 0;
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
	{
		return tank.fill(resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill)
	{
		return tank.fill(resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
	{
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction)
	{
		return new ILiquidTank[] { tank };
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
	{
		return tank;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		
		if(tank != null)
			tagCompound.setTag("tank", tank.getLiquid().writeToNBT(new NBTTagCompound()));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		
		if(tagCompound.hasKey("tank"))
		{
			LiquidStack liquidStack = new LiquidStack(0, 0, 0);
			liquidStack.readFromNBT(tagCompound.getCompoundTag("tank"));
			tank.setLiquid(liquidStack);
		}
	}

}
