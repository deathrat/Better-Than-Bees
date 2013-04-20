package deathrat.mods.btbees.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.tileentity.TileEntityWok;

public class GuiWok extends GuiContainer
{
	TileEntityWok tileEntity;

	public GuiWok(InventoryPlayer invPlayer, TileEntityWok tileEntity)
	{
		super(new ContainerWok(invPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);

		fontRenderer.drawString("Wok", (this.xSize / 2) - (fontRenderer.getStringWidth("Wok") / 2), 6, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(BetterThanBees.getResourcesPath() + "gui/gui_wok.png");
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (tileEntity.isBurning())
		{
			int burnTime = tileEntity.getBurnTimeRemainingScaled(14);
			drawTexturedModalRect(x + 8, y + 47, 176, 0, 14, 14 - burnTime);
		}

		// Arrow
		int cookTime = tileEntity.getCookProgressScaled(24);
		if (cookTime > 0)
			drawTexturedModalRect(x + 31, y + 28, 176 + cookTime - cookTime, 14, cookTime - 1, 16);
	}
}
