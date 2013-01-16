package deathrat.mods.btbees.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

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
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0F, 0F, 0F, 0.5F);

		GL11.glVertex3d(0D, 0D, 1D);
		GL11.glVertex3d(0D, 0D, 1D);
		GL11.glVertex3d(0D, 8D, 1D);
		GL11.glVertex3d(8D, 8D, 1D);
		GL11.glVertex3d(8D, 0D, 1D);

		GL11.glEnd();

		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
