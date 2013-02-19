package deathrat.mods.btbees.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.tileentity.TileEntityWok;

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
			GL11.glTranslatef((float)x + 0.5F, (float)y + 0.25F, (float)z + 0.5F);
			GL11.glPushMatrix();
				GL11.glRotatef(-90, 1F, 0F, 0F);
				ForgeHooksClient.bindTexture(BetterThanBees.getResourcesPath() + "Map__0_RGB Tint.png", 0);
				GL11.glPushMatrix();
					modelBase.render2(0.025025F);
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
