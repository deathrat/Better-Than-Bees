package deathrat.mods.btbees.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemPepperSeeds extends Item implements IPlantable
{
	private int blockType;

	public ItemPepperSeeds(int par1, int par2)
    {
	    super(par1);
	    this.blockType = par2;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
	{
		list.add("");
	}

	@Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
	    return EnumPlantType.Crop;
    }

	@Override
    public int getPlantID(World world, int x, int y, int z)
    {
	    return BetterThanBees.pepperPlantID;
    }

	@Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
	    return 0;
    }
}
