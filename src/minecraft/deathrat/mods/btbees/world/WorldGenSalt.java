package deathrat.mods.btbees.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import deathrat.mods.btbees.BetterThanBees;

public class WorldGenSalt extends WorldGenerator
{
    /** The block ID for clay. */
    private int clayBlockId;

    /** The number of blocks to generate. */
    private int numberOfBlocks;

    public WorldGenSalt(int par1)
    {
        this.clayBlockId = BetterThanBees.salt.blockID;
        this.numberOfBlocks = par1;
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (world.getBlockId(x, y, z) != Block.sand.blockID )
        {
            return false;
        }
        else
        {
            int randomX = random.nextInt(this.numberOfBlocks - 2) + 2;
            byte var7 = 1;

            for (int tempX = x - randomX; tempX <= x + randomX; ++tempX)
            {
                for (int tempZ = z - randomX; tempZ <= z + randomX; ++tempZ)
                {
                    int newTempX = tempX - x;
                    int newTempZ = tempZ - z;

                    if (newTempX * newTempX + newTempZ * newTempZ <= randomX * randomX)
                    {
                        for (int var12 = y - 1; var12 <= y + 1; ++var12)
                        {
                            int var13 = world.getBlockId(tempX, var12, tempZ);

                            if (var13 == Block.dirt.blockID || var13 == BetterThanBees.salt.blockID)
                            {
                                world.setBlock(tempX, var12, tempZ, this.clayBlockId);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
