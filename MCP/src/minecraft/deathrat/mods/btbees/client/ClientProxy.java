package deathrat.mods.btbees.client;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import deathrat.mods.btbees.common.CommonProxy;
import deathrat.mods.btbees.common.blocks.TileEntityRicePlant;

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
