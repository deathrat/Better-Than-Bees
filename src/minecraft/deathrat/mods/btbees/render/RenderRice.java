package deathrat.mods.btbees.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.tileentity.TileEntityRicePlant;

public class RenderRice implements ISimpleBlockRenderingHandler
{
	public RenderRice()
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
		if ((te != null) && (te instanceof TileEntityRicePlant))
		{
			TileEntityRicePlant teRice = (TileEntityRicePlant) te;
			// FMLClientHandler.instance().getClient().renderEngine.bindTexture("");
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

	public void renderBlockCropsImpl(Block block, IBlockAccess world, int x, int y, int z)
	{
		Tessellator tessellator = Tessellator.instance;
		Icon icon = block.getBlockTexture(world, x, y, z, 0);

		double d3 = icon.getMinU();
		double d4 = icon.getMinV();
		double d5 = icon.getMaxU();
		double d6 = icon.getMaxV();
		double d7 = x + 0.5D - 0.25D;
		double d8 = x + 0.5D + 0.25D;
		double d9 = z + 0.5D - 0.5D;
		double d10 = z + 0.5D + 0.5D;
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, d3, d4);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, d3, d6);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, d5, d6);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, d5, d4);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, d3, d4);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, d3, d6);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, d5, d6);
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, d5, d4);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, d3, d4);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, d3, d6);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, d5, d6);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, d5, d4);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, d3, d4);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, d3, d6);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, d5, d6);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, d5, d4);
		d7 = x + 0.5D - 0.5D;
		d8 = x + 0.5D + 0.5D;
		d9 = z + 0.5D - 0.25D;
		d10 = z + 0.5D + 0.25D;
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, d3, d4);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, d3, d6);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, d5, d6);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, d5, d4);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, d3, d4);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, d3, d6);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, d5, d6);
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, d5, d4);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, d3, d4);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, d3, d6);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, d5, d6);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, d5, d4);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, d3, d4);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, d3, d6);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, d5, d6);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, d5, d4);
	}

	public static int renderId;
}
