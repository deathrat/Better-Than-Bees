package deathrat.mods.btbees.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.render.RenderRice;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture(BetterThanBees.getTerrainTextures());
		MinecraftForgeClient.preloadTexture(BetterThanBees.getItemTextures());
		MinecraftForgeClient.preloadTexture(BetterThanBees.getResourcesPath() + "gui_wok.png");
		MinecraftForgeClient.preloadTexture(BetterThanBees.getResourcesPath() + "gui_boiler.png");
		MinecraftForgeClient.preloadTexture(BetterThanBees.getResourcesPath() + "Map__0_RGB Tint.png");
		MinecraftForgeClient.preloadTexture(BetterThanBees.getResourcesPath() + "endertanktex.png");
		MinecraftForgeClient.preloadTexture(BetterThanBees.getResourcesPath() + "boatHull.uvw.png");
		RenderingRegistry.registerBlockHandler(new RenderRice());
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRicePlant.class,
		// new RiceBaseRender());
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWok.class, new
		// RenderWok());
		// RenderingRegistry.registerEntityRenderingHandler(CheapBoat.class, new
		// RenderCheapBoat());
	}
}
