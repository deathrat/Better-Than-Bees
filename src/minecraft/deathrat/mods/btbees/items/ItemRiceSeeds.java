package deathrat.mods.btbees.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import deathrat.mods.btbees.BetterThanBees;

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


	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int var11 = world.getBlockId(x, y, z);

		if (itemStack.stackSize == 0)
		{
			return false;
		}
		else if (!entityPlayer.canPlayerEdit(x, y, z, side, itemStack))
		{
			return false;
		}
		else if (!entityPlayer.canPlayerEdit(x, y+1, z, side, itemStack))
		{
			return false;
		}
		else if (y == 255 && Block.blocksList[this.blockType].blockMaterial.isSolid())
		{
			return false;
		}
		else if (world.canPlaceEntityOnSide(this.blockType, x, y, z, false, side, entityPlayer))
		{
			Block var12 = Block.blocksList[this.blockType];
			int itemmeta = this.getMetadata(itemStack.getItemDamage());
			int meta = Block.blocksList[this.blockType].onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, itemmeta);

			if (placeBlockAt(itemStack, entityPlayer, world, x, y, z, side, hitX, hitY, hitZ, meta))
			{
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), var12.stepSound.getPlaceSound(), (var12.stepSound.getVolume() + 1.0F) / 2.0F, var12.stepSound.getPitch() * 0.8F);
				--itemStack.stackSize;
			}

			return true;
		}
		else
		{
			return false;
		}
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

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
	   if (!world.setBlockAndMetadataWithNotify(x, y+1, z, this.blockType, 0))
	   {
			   return false;
	   }

	   if (world.getBlockId(x, y, z) == this.blockType)
	   {
		   Block.blocksList[this.blockType].onBlockPlacedBy(world, x, y, z, player);
		   Block.blocksList[this.blockType].onPostBlockPlaced(world, x, y, z, 0);
	   }

	   return true;
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
