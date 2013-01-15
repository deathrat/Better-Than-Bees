package deathrat.mods.btbees.items;

import deathrat.mods.btbees.BetterThanBees;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemRiceSeeds extends Item implements IPlantable
{
    private int blockType;

	public ItemRiceSeeds(int par1, int par2)
	{
		super(par1);
		blockType = par2;
	}


	public String getTextureFile()
	{
		return BetterThanBees.getItemTextures();
	}


    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		return true;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        MovingObjectPosition movingObjPos = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);

        if (movingObjPos == null)
        {
            return itemStack;
        }
        else
        {
            if (movingObjPos.typeOfHit == EnumMovingObjectType.TILE)
            {
                int movingObjPosX = movingObjPos.blockX;
                int movingObjPosY = movingObjPos.blockY;
                int movingObjPosZ = movingObjPos.blockZ;

                if (!world.canMineBlock(entityPlayer, movingObjPosX, movingObjPosY + 1, movingObjPosZ))
                {
                    return itemStack;
                }

                if ((world.getBlockMaterial(movingObjPosX, movingObjPosY, movingObjPosZ) == Material.water) && (world.getBlockMetadata(movingObjPosX, movingObjPosY, movingObjPosZ) == 0) && (world.isAirBlock(movingObjPosX, movingObjPosY + 1, movingObjPosZ)))
                {
                    world.setBlockWithNotify(movingObjPosX, movingObjPosY + 1, movingObjPosZ, this.blockType);

                    if (!entityPlayer.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                }
            }

            return itemStack;
        }
    }


    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Water;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockType;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
    	return world.getBlockMetadata(x, y, z);
    }
}
