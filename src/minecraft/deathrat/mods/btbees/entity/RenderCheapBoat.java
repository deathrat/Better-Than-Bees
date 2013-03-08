package deathrat.mods.btbees.entity;

import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.vec.SwapYZ;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;

@SideOnly(Side.CLIENT)
public class RenderCheapBoat extends Render
{
	private Map<String, CCModel> renderWok = CCModel.parseObjModels(BetterThanBees.getResourcesPath()+"boatHull.obj", new SwapYZ());
	private CCModel wok = (renderWok.get("Box001")).scale(0.05);

	public RenderCheapBoat()
	{
		wok = wok.computeNormals();
	}
	
	public void renderCheapBoat(CheapBoat boat, double x, double y, double z, float f, float f2)
	{
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y - 0.25F, (float)z);
			GL11.glRotatef(-90, 1F, 0F, 0F);
			GL11.glRotatef(90.0F - f, 0F, 0F, 1F);
			GL11.glPushMatrix();
				CCRenderState.changeTexture(BetterThanBees.getResourcesPath() + "boatHull.uvw.png");
				CCRenderState.startDrawing(4);
				wok.render(null, 0, 0);
				CCRenderState.draw();
				
		        float var10 = (float)boat.getTimeSinceHit() - f2;
		        float var11 = (float)boat.getDamageTaken() - f2;

		        if (var11 < 0.0F)
		        {
		            var11 = 0.0F;
		        }

		        if (var10 > 0.0F)
		        {
		            GL11.glRotatef(MathHelper.sin(var10) * var10 * var11 / 10.0F * (float)boat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		        }

			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		CCRenderState.reset();
		CCRenderState.pullLightmap();
		CCRenderState.useNormals(true);
		
		renderCheapBoat((CheapBoat) var1, var2, var4, var6, var8, var9);
	}
}
