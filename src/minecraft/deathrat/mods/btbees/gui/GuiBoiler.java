package deathrat.mods.btbees.gui;

import org.lwjgl.opengl.GL11;

import deathrat.mods.btbees.tileentity.TileEntityBoiler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiBoiler extends GuiContainer
{
    public TileEntityBoiler tileEntity;

    public GuiBoiler(InventoryPlayer invPlayer, TileEntityBoiler tileEntity)
    {
        super(new ContainerBoiler(invPlayer, tileEntity));

        this.xSize = 199;
        this.ySize = 193;

        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        super.drawGuiContainerForegroundLayer(par1, par2);

        fontRenderer.drawString("Boiler", (this.xSize / 2) - (fontRenderer.getStringWidth("Boiler") / 2) - 3, 100, 4210752);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int texture = mc.renderEngine.getTexture("/deathrat/mods/btbees/gui_boiler.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        //Water meter
        if(tileEntity.hasWater())
        {
            int waterLevel = tileEntity.getScaledWaterLevel(46);
            this.drawTexturedModalRect(x + 176, y + 43 + 46 - waterLevel, 199, 73 - waterLevel, 12, waterLevel + 5);
        }

        //Middle bar
        this.drawTexturedModalRect(x + 180, y + 42, 199, 78, 4, 43);

        //Glass Sheen
        this.drawTexturedModalRect(x + 176, y + 43, 211, 27, 12, 42);

        //Energy bar
        int energyLevel = tileEntity.getScaledEnergyLevel(14);
        this.drawTexturedModalRect(x + 98, y + 6 + 14 - energyLevel, 199, 26 - energyLevel, 14, energyLevel);

        //Fire bar
        int fireLevel = tileEntity.getScaledFireLevel(12);
        this.drawTexturedModalRect(x + 81, y + 6 + 12 - fireLevel, 199, 12 - fireLevel, 14, fireLevel);

    }
}
