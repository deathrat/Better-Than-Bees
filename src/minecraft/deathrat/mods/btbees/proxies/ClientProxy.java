package deathrat.mods.btbees.proxies;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.BetterThanBees;
import deathrat.mods.btbees.blocks.TileEntityRicePlant;
import deathrat.mods.btbees.render.BTBRenderRice;
import deathrat.mods.btbees.render.RiceBaseRender;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture(BetterThanBees.getTerrainTextures());
		MinecraftForgeClient.preloadTexture(BetterThanBees.getItemTextures());
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/gui_wok.png");
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/gui_boiler.png");
//		MinecraftForgeClient.registerItemRenderer(BetterThanBees.uncookedRice.itemID, new ItemRenderRice());
		RenderingRegistry.registerBlockHandler(new BTBRenderRice());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRicePlant.class, new RiceBaseRender());
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoiler.class, new BoilerRender());
	}
}