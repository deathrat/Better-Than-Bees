package deathrat.mods.btbees.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;

@SideOnly(Side.CLIENT)
public class RenderRice implements ISimpleBlockRenderingHandler
{
	public ModelRiceBase riceBase;

	public RenderRice()
	{
		renderId = RenderingRegistry.getNextAvailableRenderId();
		riceBase = new ModelRiceBase();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		Tessellator var5 = Tessellator.instance;
		var5.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		renderBlockCropsImpl(block, world, x, y, z);
		// renderBlockBase(block, world, x, y, z);
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
		Icon icon = block.getBlockTexture(world, x, y, z, 0);

		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getMaxU();
		double maxV = icon.getMaxV();
		double d7 = x + 0.5D - 0.25D;
		double d8 = x + 0.5D + 0.25D;
		double d9 = z + 0.5D - 0.5D;
		double d10 = z + 0.5D + 0.5D;

		Tessellator tessellator = Tessellator.instance;

		tessellator.addVertexWithUV(d7, y + 0.8D, d9, minU, minV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, minU, maxV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, maxU, maxV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, maxU, minV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, minU, minV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, minU, maxV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, maxU, maxV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, maxU, minV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, minU, minV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, minU, maxV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, maxU, maxV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, maxU, minV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, minU, minV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, minU, maxV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, maxU, maxV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, maxU, minV);
		d7 = x + 0.5D - 0.5D;
		d8 = x + 0.5D + 0.5D;
		d9 = z + 0.5D - 0.25D;
		d10 = z + 0.5D + 0.25D;
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, minU, minV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, minU, maxV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, maxU, maxV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, maxU, minV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d9, minU, minV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d9, minU, maxV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d9, maxU, maxV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d9, maxU, minV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, minU, minV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, minU, maxV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, maxU, maxV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, maxU, minV);
		tessellator.addVertexWithUV(d7, y + 0.8D, d10, minU, minV);
		tessellator.addVertexWithUV(d7, y + -0.2D, d10, minU, maxV);
		tessellator.addVertexWithUV(d8, y + -0.2D, d10, maxU, maxV);
		tessellator.addVertexWithUV(d8, y + 0.8D, d10, maxU, minV);
	}

	public void renderBlockBase(Block block, IBlockAccess world, int x, int y, int z)
	{
		Tessellator tess = Tessellator.instance;

		GL11.glPushMatrix();
		GL11.glTranslatef(x + 0.5F, y + -1.65F, z + 0.5F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(BetterThanBees.getResourcesPath() + "btb_ricebase.png");
		GL11.glPushMatrix();
		riceBase.render2(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		FMLClientHandler.instance().getClient().renderEngine.resetBoundTexture();
	}

	public static int renderId;
}
