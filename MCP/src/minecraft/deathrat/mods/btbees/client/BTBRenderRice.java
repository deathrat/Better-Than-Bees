package deathrat.mods.btbees.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.common.blocks.TileEntityRicePlant;

public class BTBRenderRice implements ISimpleBlockRenderingHandler
{
	public BTBRenderRice()
	{
		renderId = RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
        Tessellator var5 = Tessellator.instance;
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if((te != null) && (te instanceof TileEntityRicePlant))
        {
        	TileEntityRicePlant teRice = (TileEntityRicePlant)te;
        	ForgeHooksClient.bindTexture("/deathrat/mods/btbees/btb_terrain2.png", 0);
        }

        var5.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        renderBlockCropsImpl(block, world, x, y, z);
        return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}


    public void renderBlockCropsImpl(Block block, IBlockAccess world, int par3, int par5, int par7)
    {
        Tessellator var9 = Tessellator.instance;
        int var10 = block.getBlockTexture(world, par3, par5, par7, 0);


        int var11 = (var10 & 0xF) << 4;
        int var12 = var10 & 0xF0;
        double var13 = (double)((float)var11 / 256.0F);
        double var15 = (double)(((float)var11 + 15.99F) / 256.0F);
        double var17 = (double)((float)var12 / 256.0F);
        double var19 = (double)(((float)var12 + 15.99F) / 256.0F);
        double var21 = par3 + 0.5D - 0.25D;
        double var23 = par3 + 0.5D + 0.25D;
        double var25 = par7 + 0.5D - 0.5D;
        double var27 = par7 + 0.5D + 0.5D;
        var9.addVertexWithUV(var21, par5 + 0.8D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + -0.2D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + -0.2D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.8D, var27, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.8D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + -0.2D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + -0.2D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.8D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.8D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + -0.2D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + -0.2D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.8D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.8D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + -0.2D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + -0.2D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.8D, var27, var15, var17);
        var21 = par3 + 0.5D - 0.5D;
        var23 = par3 + 0.5D + 0.5D;
        var25 = par7 + 0.5D - 0.25D;
        var27 = par7 + 0.5D + 0.25D;
        var9.addVertexWithUV(var21, par5 + 0.8D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + -0.2D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + -0.2D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.8D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.8D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + -0.2D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + -0.2D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.8D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.8D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + -0.2D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + -0.2D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.8D, var27, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.8D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + -0.2D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + -0.2D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.8D, var27, var15, var17);
    }

    public static int renderId;
}
