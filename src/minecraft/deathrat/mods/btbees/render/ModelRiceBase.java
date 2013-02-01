package deathrat.mods.btbees.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRiceBase extends ModelBase
{
//fields
	ModelRenderer Base;
	ModelRenderer PosX;
	ModelRenderer PosZ;
	ModelRenderer NegX;
	ModelRenderer NegZ;

public ModelRiceBase()
{
	textureWidth = 64;
	textureHeight = 128;

	Base = new ModelRenderer(this, 0, 0);
	Base.addBox(-8F, 0F, -8F, 16, 1, 16);
	Base.setRotationPoint(0F, 23F, 0F);
	Base.setTextureSize(64, 128);
	Base.mirror = true;
	setRotation(Base, 0F, 0F, 0F);
	PosX = new ModelRenderer(this, 0, 36);
	PosX.addBox(0F, -1F, -8F, 1, 2, 16);
	PosX.setRotationPoint(7F, 24F, 0F);
	PosX.setTextureSize(64, 128);
	PosX.mirror = true;
	setRotation(PosX, 0F, 0F, 0F);
	PosZ = new ModelRenderer(this, 0, 55);
	PosZ.addBox(-7F, -1F, 0F, 14, 2, 1);
	PosZ.setRotationPoint(0F, 24F, 7F);
	PosZ.setTextureSize(64, 128);
	PosZ.mirror = true;
	setRotation(PosZ, 0F, 0F, 0F);
	NegX = new ModelRenderer(this, 0, 18);
	NegX.addBox(0F, -1F, -8F, 1, 2, 16);
	NegX.setRotationPoint(-8F, 24F, 0F);
	NegX.setTextureSize(64, 128);
	NegX.mirror = true;
	setRotation(NegX, 0F, 0F, 0F);
	NegZ = new ModelRenderer(this, 0, 61);
	NegZ.addBox(-7F, -1F, 0F, 14, 2, 1);
	NegZ.setRotationPoint(0F, 24F, -8F);
	NegZ.setTextureSize(64, 128);
	NegZ.mirror = true;
	setRotation(NegZ, 0F, 0F, 0F);
}

public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
{
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	Base.render(f5);
	PosX.render(f5);
	PosZ.render(f5);
	NegX.render(f5);
	NegZ.render(f5);
}

public void render2(float f)
{
	Base.render(f);
	PosX.render(f);
	PosZ.render(f);
	NegX.render(f);
	NegZ.render(f);
}

private void setRotation(ModelRenderer model, float x, float y, float z)
{
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
}

public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
{
	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
}

}
