package deathrat.mods.btbees.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

import com.overminddl1.minecraft.libs.NMT.NMTModelRenderer;

import deathrat.mods.btbees.BetterThanBees;

public class ModelWokBase extends ModelBase
{
	NMTModelRenderer Wok;

	public ModelWokBase()
	{
		Wok = new NMTModelRenderer(this);
		Wok.addModelOBJ(BetterThanBees.class.getResource(BetterThanBees.getResourcesPath()+"back.obj").toString());
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	public void render2(Float f)
	{
		Wok.render(f);
	}

}
