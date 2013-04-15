package deathrat.mods.btbees.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;
import powercrystals.core.position.BlockPosition;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import deathrat.mods.btbees.tileentity.TileEntityBoilerTank;

public class BlockBoilerTank extends BlockContainer
{
	public Icon boilerTank;

	public BlockBoilerTank(int id, Material material)
	{
		super(id, material);
	}

	public String getBlockName()
	{
		return "Boiler Tank";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg)
	{
		boilerTank = iconReg.registerIcon("btbees:boilertank");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return boilerTank;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y - 1, z);
		if (tileEntity instanceof TileEntityBoiler)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int meta, float hitX, float hitY, float hitZ)
	{
		TileEntityBoilerTank thisTE = (TileEntityBoilerTank) world.getBlockTileEntity(x, y, z);
		thisTE.fill(0, new LiquidStack(Block.waterStill.blockID, 1000, 0), true);
		System.out.println(thisTE.tank.getLiquid().amount);

		return true;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		TileEntity thisTE = world.getBlockTileEntity(x, y, z);
		TileEntity boilerTE = BlockPosition.getAdjacentTileEntity(thisTE, ForgeDirection.DOWN);
		if (boilerTE instanceof TileEntityBoiler)
		{
			((TileEntityBoiler) boilerTE).setBoilerTank((TileEntityBoilerTank) thisTE);
		} else
		{
			return 0;
		}
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		TileEntity boilerTE = world.getBlockTileEntity(x, y - 1, z);
		if (boilerTE instanceof TileEntityBoiler)
			((TileEntityBoiler) boilerTE).setBoilerTank((TileEntityBoilerTank) null);

		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoilerTank();
	}

}
