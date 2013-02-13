package deathrat.mods.btbees.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.render.RenderRice;
import deathrat.mods.btbees.render.RenderWok;
import deathrat.mods.btbees.render.RiceBaseRender;
import deathrat.mods.btbees.tileentity.TileEntityRicePlant;
import deathrat.mods.btbees.tileentity.TileEntityWok;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture(BetterThanBees.getTerrainTextures());
		MinecraftForgeClient.preloadTexture(BetterThanBees.getItemTextures());
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/gui_wok.png");
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/gui_boiler.png");
		RenderingRegistry.registerBlockHandler(new RenderRice());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRicePlant.class, new RiceBaseRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWok.class, new RenderWok());
	}
}
