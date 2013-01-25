package deathrat.mods.btbees.render;

import org.lwjgl.opengl.GL11;

import com.overminddl1.minecraft.libs.NMT.NMTModelBase;

import deathrat.mods.btbees.blocks.TileEntityWok;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderWok extends TileEntitySpecialRenderer
{
	private ModelWokBase modelBase;

	public RenderWok()
    {
		modelBase = new ModelWokBase();
    }

	public void renderWokModel(TileEntity tileEntity, double x, double y, double z, float var5)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.30F, (float)z + 0.5F);
		GL11.glPushMatrix();
		GL11.glRotatef(-90, 1F, 0F, 0F);
		ForgeHooksClient.bindTexture("/deathrat/mods/btbees/checkertexture.png", 0);
		GL11.glPushMatrix();
		modelBase.render2(0.0425F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float idk)
	{
		renderWokModel((TileEntityWok)tileEntity, x, y, z, idk);
    }
}