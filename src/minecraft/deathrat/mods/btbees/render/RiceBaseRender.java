package deathrat.mods.btbees.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;

import deathrat.mods.btbees.tileentity.TileEntityRicePlant;

public class RiceBaseRender extends TileEntitySpecialRenderer
{
	private ModelRiceBase baseModel;

	public RiceBaseRender()
	{
		baseModel = new ModelRiceBase();
	}

//	@Override
//	public void renderTileEntityAt(TileEntity var1, double var2, double var3, double var4, float var5)
//	{
//		renderRiceModel((TileEntityRicePlant)var1, var2, var3, var4, var5);
//	}

	public void renderRiceModel(TileEntity tileEntity, double x, double y, double z, float var5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + -1.65F, (float)z + 0.5F);
		ForgeHooksClient.bindTexture("/deathrat/mods/btbees/btb_ricebase.png", 0);
		GL11.glPushMatrix();
		baseModel.render2(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

//	@Override
//	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
//	{
//		// TODO Auto-generated method stub
//
//	}


	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var3, double var4, float var5)
	{
		renderRiceModel((TileEntityRicePlant)var1, var2, var3, var4, var5);
	}
}
