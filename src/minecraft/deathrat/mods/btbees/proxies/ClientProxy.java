package deathrat.mods.btbees.proxies;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.blocks.TileEntityRicePlant;
import deathrat.mods.btbees.render.BTBRenderRice;
import deathrat.mods.btbees.render.RiceBaseRender;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/btb_terrain2.png");
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/btb_items.png");
		MinecraftForgeClient.preloadTexture("/deathrat/mods/btbees/gui_wok.png");
		RenderingRegistry.registerBlockHandler(new BTBRenderRice());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRicePlant.class, new RiceBaseRender());
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoiler.class, new BoilerRender());
	}
}
