package deathrat.mods.btbees.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizer;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.blocks.BlockRicePlant;

public class ItemRiceHusk extends Item implements IFactoryFertilizer
{

	public ItemRiceHusk(int id)
    {
	    super(id);
    }

	@Override
	public String getTextureFile()
	{
	    return BetterThanBees.getItemTextures();
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int meta, float par8, float par9, float par10)
	{
        int block = world.getBlockId(x, y, z);

        BonemealEvent event = new BonemealEvent(entityPlayer, world, block, x, y, z);
        if (MinecraftForge.EVENT_BUS.post(event))
        {
            return false;
        }

        if (event.getResult() == Result.ALLOW)
        {
            if (!world.isRemote)
            {
                itemStack.stackSize--;
            }
            return true;
        }

        if (block == Block.sapling.blockID)
        {
            if (!world.isRemote)
            {
                ((BlockSapling)Block.sapling).growTree(world, x, y, z, world.rand);
                --itemStack.stackSize;
            }

            return true;
        }

        if (block == Block.mushroomBrown.blockID || block == Block.mushroomRed.blockID)
        {
            if (!world.isRemote && ((BlockMushroom)Block.blocksList[block]).fertilizeMushroom(world, x, y, z, world.rand))
            {
                --itemStack.stackSize;
            }

            return true;
        }

        if (block == Block.melonStem.blockID || block == Block.pumpkinStem.blockID)
        {
            if (world.getBlockMetadata(x, y, z) == 7)
            {
                return false;
            }

            if (!world.isRemote)
            {
                ((BlockStem)Block.blocksList[block]).fertilizeStem(world, x, y, z);
                --itemStack.stackSize;
            }

            return true;
        }

        if (block > 0 && Block.blocksList[block] instanceof BlockCrops)
        {
            if (world.getBlockMetadata(x, y, z) == 7)
            {
                return false;
            }

            if (!world.isRemote)
            {
                ((BlockCrops)Block.blocksList[block]).fertilize(world, x, y, z);
                --itemStack.stackSize;
            }

            return true;
        }

        if (block == Block.cocoaPlant.blockID)
        {
            if (!world.isRemote)
            {
                world.setBlockMetadataWithNotify(x, y, z, 8 | BlockDirectional.getDirection(world.getBlockMetadata(x, y, z)));
                --itemStack.stackSize;
            }

            return true;
        }

        if (block == Block.grass.blockID)
        {
            if (!world.isRemote)
            {
                --itemStack.stackSize;
                label133:

                for (int var12 = 0; var12 < 128; ++var12)
                {
                    int var13 = x;
                    int var14 = y + 1;
                    int var15 = z;

                    for (int var16 = 0; var16 < var12 / 16; ++var16)
                    {
                        var13 += itemRand.nextInt(3) - 1;
                        var14 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                        var15 += itemRand.nextInt(3) - 1;

                        if (world.getBlockId(var13, var14 - 1, var15) != Block.grass.blockID || world.isBlockNormalCube(var13, var14, var15))
                        {
                            continue label133;
                        }
                    }

                    if (world.getBlockId(var13, var14, var15) == 0)
                    {
                        if (itemRand.nextInt(10) != 0)
                        {
                            if (Block.tallGrass.canBlockStay(world, var13, var14, var15))
                            {
                                world.setBlockAndMetadataWithNotify(var13, var14, var15, Block.tallGrass.blockID, 1);
                            }
                        }
                        else
                        {
                            ForgeHooks.plantGrass(world, var13, var14, var15);
                        }
                    }
                }
            }

            return true;
        }

        if(block == BetterThanBees.ricePlantID)
        {
        	BlockRicePlant.setPlantMeta(world, x, y, z, 3);
        	--itemStack.stackSize;
        }

        return false;
	}

	@Override
    public int getFertilizerId()
    {
	    return itemID;
    }

	@Override
    public int getFertilizerMeta()
    {
	    return 0;
    }

	@Override
    public FertilizerType getFertilizerType()
    {
	    return FertilizerType.GrowPlant;
    }

	@Override
    public void consume(ItemStack fertilizer)
    {
		--fertilizer.stackSize;
    }



}
