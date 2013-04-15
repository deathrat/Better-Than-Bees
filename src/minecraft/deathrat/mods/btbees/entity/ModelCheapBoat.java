package deathrat.mods.btbees.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import deathrat.mods.btbees.BetterThanBees;

@SideOnly(Side.CLIENT)
public class ModelCheapBoat extends ModelBase
{
	private IModelCustom cheapBoat;

	public ModelCheapBoat()
	{
		cheapBoat = AdvancedModelLoader.loadModel(BetterThanBees.getResourcesPath() + "models/" + "boatHull.obj");
	}

	public void render()
	{
		cheapBoat.renderAll();
	}
}
