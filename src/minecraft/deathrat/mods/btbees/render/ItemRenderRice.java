package deathrat.mods.btbees.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.TextureFXManager;

public class ItemRenderRice implements IItemRenderer
{
	private static RenderItem renderItem = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();

        Tessellator var5 = Tessellator.instance;
        int var6 = item.getIconIndex();
        float var7 = ((float)(var6 % 16 * 16) + 0.0F) / 256.0F;
        float var8 = ((float)(var6 % 16 * 16) + 15.99F) / 256.0F;
        float var9 = ((float)(var6 / 16 * 16) + 0.0F) / 256.0F;
        float var10 = ((float)(var6 / 16 * 16) + 15.99F) / 256.0F;
        float var11 = 0.0F;
        float var12 = 0.3F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glTranslatef(-var11, -var12, 0.0F);
        float var13 = 1.5F;
        GL11.glScalef(var13, var13, var13);
        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        renderItemIn2D(var5, var8, var9, var7, var10, 0.0625F);

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);

        GL11.glPopMatrix();
	}

    public static void renderItemIn2D(Tessellator par0Tessellator, float par1, float par2, float par3, float par4, float par5)
    {
        float var6 = 1.0F;
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)par1, (double)par4);
        par0Tessellator.addVertexWithUV((double)var6, 0.0D, 0.0D, (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV((double)var6, 1.0D, 0.0D, (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double)par1, (double)par2);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, (double)(0.0F - par5), (double)par1, (double)par2);
        par0Tessellator.addVertexWithUV((double)var6, 1.0D, (double)(0.0F - par5), (double)par3, (double)par2);
        par0Tessellator.addVertexWithUV((double)var6, 0.0D, (double)(0.0F - par5), (double)par3, (double)par4);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, (double)(0.0F - par5), (double)par1, (double)par4);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        int var7;
        float var8;
        float var9;
        float var10;

        /* Gets the width/16 of the currently bound texture, used
         * to fix the side rendering issues on textures != 16 */
        int tileSize = TextureFXManager.instance().getTextureDimensions(GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D)).width / 16;

        float tx = 1.0f / (32 * tileSize);
        float tz = 1.0f /  tileSize;

        for (var7 = 0; var7 < tileSize; ++var7)
        {
            var8 = (float)var7 / tileSize;
            var9 = par1 + (par3 - par1) * var8 - tx;
            var10 = var6 * var8;
            par0Tessellator.addVertexWithUV((double)var10, 0.0D, (double)(0.0F - par5), (double)var9, (double)par4);
            par0Tessellator.addVertexWithUV((double)var10, 0.0D, 0.0D, (double)var9, (double)par4);
            par0Tessellator.addVertexWithUV((double)var10, 1.0D, 0.0D, (double)var9, (double)par2);
            par0Tessellator.addVertexWithUV((double)var10, 1.0D, (double)(0.0F - par5), (double)var9, (double)par2);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);

        for (var7 = 0; var7 < tileSize; ++var7)
        {
            var8 = (float)var7 / tileSize;
            var9 = par1 + (par3 - par1) * var8 - tx;
            var10 = var6 * var8 + tz;
            par0Tessellator.addVertexWithUV((double)var10, 1.0D, (double)(0.0F - par5), (double)var9, (double)par2);
            par0Tessellator.addVertexWithUV((double)var10, 1.0D, 0.0D, (double)var9, (double)par2);
            par0Tessellator.addVertexWithUV((double)var10, 0.0D, 0.0D, (double)var9, (double)par4);
            par0Tessellator.addVertexWithUV((double)var10, 0.0D, (double)(0.0F - par5), (double)var9, (double)par4);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);

        for (var7 = 0; var7 < tileSize; ++var7)
        {
            var8 = (float)var7 / tileSize;
            var9 = par4 + (par2 - par4) * var8 - tx;
            var10 = var6 * var8 + tz;
            par0Tessellator.addVertexWithUV(0.0D, (double)var10, 0.0D, (double)par1, (double)var9);
            par0Tessellator.addVertexWithUV((double)var6, (double)var10, 0.0D, (double)par3, (double)var9);
            par0Tessellator.addVertexWithUV((double)var6, (double)var10, (double)(0.0F - par5), (double)par3, (double)var9);
            par0Tessellator.addVertexWithUV(0.0D, (double)var10, (double)(0.0F - par5), (double)par1, (double)var9);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);

        for (var7 = 0; var7 < tileSize; ++var7)
        {
            var8 = (float)var7 / tileSize;
            var9 = par4 + (par2 - par4) * var8 - tx;
            var10 = var6 * var8;
            par0Tessellator.addVertexWithUV((double)var6, (double)var10, 0.0D, (double)par3, (double)var9);
            par0Tessellator.addVertexWithUV(0.0D, (double)var10, 0.0D, (double)par1, (double)var9);
            par0Tessellator.addVertexWithUV(0.0D, (double)var10, (double)(0.0F - par5), (double)par1, (double)var9);
            par0Tessellator.addVertexWithUV((double)var6, (double)var10, (double)(0.0F - par5), (double)par3, (double)var9);
        }

        par0Tessellator.draw();
    }

}
