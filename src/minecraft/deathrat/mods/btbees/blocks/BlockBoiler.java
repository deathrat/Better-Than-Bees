package deathrat.mods.btbees.blocks;

import java.util.Random;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBoiler extends BlockContainer
{
	private Class tileEntity;

	public BlockBoiler(int id, Material mat, Class te)
    {
		super(id, mat);

		this.tileEntity = te;

		setHardness(2.0F);
		setResistance(5.0F);
		setBlockName("blockBoiler");
    }

	@Override
	public String getTextureFile()
	{
	    return BetterThanBees.terrainTextures;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return 5;
	}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are)
    {
	    TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	    if (tileEntity == null || player.isSneaking())
	    {
	            return false;
	    }
	    player.openGui(BetterThanBees.instance, 1, world, x, y, z);
	    return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6)
    {
    	dropItems(world, x, y, z);
    	super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound())
                        {
                                entityItem.func_92014_d().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
    }


	@Override
    public TileEntity createNewTileEntity(World var1)
    {
	    try
	    {
	    	return (TileEntityBoiler)tileEntity.newInstance();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    return null;
    }
}
