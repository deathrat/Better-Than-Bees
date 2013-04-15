package deathrat.mods.btbees.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.tileentity.TileEntitySteamer;

public class BlockSteamer extends BlockContainer
{
	public Icon steamerIcon;

	public BlockSteamer(int id, Material mat)
	{
		super(id, mat);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconReg)
	{
		steamerIcon = iconReg.registerIcon("btbees:steamer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		return steamerIcon;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySteamer();
	}

}
