package deathrat.mods.btbees.render;

import com.overminddl1.minecraft.libs.NMT.NMTModelRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelWokBase extends ModelBase
{
	NMTModelRenderer Wok;

	public ModelWokBase()
    {
		Wok = new NMTModelRenderer(this);
		try
		{
			Wok.addModelOBJ("/deathrat/mods/btbees/pan_obj.obj");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
	    super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
//		Wok.render(f5);
	}

	public void render2(Float f)
	{
		Wok.render(f);
	}

}
