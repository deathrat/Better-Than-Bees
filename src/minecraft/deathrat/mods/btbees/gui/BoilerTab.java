package deathrat.mods.btbees.gui;

import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import net.minecraft.client.gui.Gui;
import thermalexpansion.core.gui.Tab;

public class BoilerTab extends Tab
{

	TileEntityBoiler myTile;
	int headerColor = 14797103;
	int subheaderColor = 11186104;
	int textColor = 0;

	public BoilerTab(Gui gui, TileEntityBoiler te)
    {
	    super(gui);

	    myTile = te;
	    maxHeight = 92;
	    maxWidth = 100;
	    overlayColor = 685776;
    }

	@Override
    public void draw(int x, int y)
    {
		drawBackground(x, y);

		drawIcon("/deathrat/mods/btbees/icons.png", 3, x + 2, y + 2);

		if(!isFullyOpened())
			return;

	    tabFontRenderer.drawStringWithShadow("Energy", x + 22, y + 6, headerColor);
	    tabFontRenderer.drawStringWithShadow("Power Usage" + ":", x + 8, y + 18, subheaderColor);
	    tabFontRenderer.drawString(String.format("%.1f", new Object[] { Float.valueOf(myTile.getPower()) }) + " MJ/t", x + 16, y + 30, textColor);
	    tabFontRenderer.drawStringWithShadow("Maximum Power" + ":", x + 8, y + 42, subheaderColor);
	    tabFontRenderer.drawString(String.format("%.1f", new Object[] { Float.valueOf(myTile.getMaxPower()) }) + " MJ/t", x + 16, y + 54, textColor);
	    tabFontRenderer.drawStringWithShadow("Energy Stored" + ":", x + 8, y + 66, subheaderColor);
	    tabFontRenderer.drawString(String.format("%.1f", new Object[] { Float.valueOf(myTile.getEnergy()) }) + " MJ", x + 16, y + 78, textColor);
    }

	@Override
    public String getTooltip()
    {
	    return null;
    }

}
