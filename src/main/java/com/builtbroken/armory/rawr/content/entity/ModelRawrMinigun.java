package com.builtbroken.armory.rawr.content.entity;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * RAWR Minigun - West
 * Created using Tabula 7.0.0
 */
public class ModelRawrMinigun extends ModelBase
{
    public ModelRenderer Insides;
    public ModelRenderer WholePlatform;
    public ModelRenderer Wheels2;
    public ModelRenderer NeckPlatform;
    public ModelRenderer Battery1;
    public ModelRenderer Neck;
    public ModelRenderer MinigunBody;
    public ModelRenderer Belt1;
    public ModelRenderer Barrel1;
    public ModelRenderer Barrel2;
    public ModelRenderer Barrel3;
    public ModelRenderer Barrel4;
    public ModelRenderer MuzzleBreak;
    public ModelRenderer Belt2;
    public ModelRenderer Belt3;
    public ModelRenderer Mag;
    public ModelRenderer Battery2;
    public ModelRenderer Powerlight;
    public ModelRenderer Battery3;
    public ModelRenderer Atena;
    public ModelRenderer Wheels3;
    public ModelRenderer Wheels1;
    public ModelRenderer Tracks;
    public ModelRenderer Tracks_1;
    public ModelRenderer Tracks_2;
    public ModelRenderer Tracks_3;
    public ModelRenderer Tracks_4;
    public ModelRenderer Tracks_5;
    public ModelRenderer Tracks_6;
    public ModelRenderer Tracks_7;

    public ModelRawrMinigun()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Belt2 = new ModelRenderer(this, 0, 28);
        this.Belt2.setRotationPoint(1.0F, 0.0F, 1.5F);
        this.Belt2.addBox(0.0F, 0.0F, -1.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Belt2, 0.0F, 0.0F, 0.7853981633974483F);
        this.Battery2 = new ModelRenderer(this, 4, 53);
        this.Battery2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.Battery2.addBox(0.0F, 0.0F, 0.0F, 2, 3, 7, 0.0F);
        this.setRotateAngle(Battery2, -1.2217304763960306F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 52, 8);
        this.Neck.setRotationPoint(0.5F, -0.5F, 0.0F);
        this.Neck.addBox(-1.5F, -2.0F, -1.5F, 3, 2, 3, 0.0F);
        this.Insides = new ModelRenderer(this, 40, 53);
        this.Insides.setRotationPoint(0.0F, 19.5F, 0.0F);
        this.Insides.addBox(-2.5F, 0.0F, -3.5F, 5, 4, 7, 0.0F);
        this.Belt1 = new ModelRenderer(this, 0, 28);
        this.Belt1.setRotationPoint(2.0F, -3.0F, -1.5F);
        this.Belt1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.Tracks = new ModelRenderer(this, 40, 38);
        this.Tracks.setRotationPoint(-1.0F, -1.0F, -3.5F);
        this.Tracks.addBox(0.0F, 0.0F, 0.0F, 3, 1, 9, 0.0F);
        this.Battery3 = new ModelRenderer(this, 0, 59);
        this.Battery3.setRotationPoint(0.0F, 0.0F, 6.96F);
        this.Battery3.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
        this.Tracks_5 = new ModelRenderer(this, 50, 30);
        this.Tracks_5.setRotationPoint(0.0F, 0.5F, -0.5F);
        this.Tracks_5.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.Tracks_4 = new ModelRenderer(this, 40, 38);
        this.Tracks_4.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.Tracks_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 9, 0.0F);
        this.Belt3 = new ModelRenderer(this, 0, 28);
        this.Belt3.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.Belt3.addBox(-1.0F, 0.0F, -1.5F, 1, 2, 3, 0.0F);
        this.setRotateAngle(Belt3, 0.0F, 0.0F, -0.7853981633974483F);
        this.Tracks_7 = new ModelRenderer(this, 40, 38);
        this.Tracks_7.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.Tracks_7.addBox(0.0F, 0.0F, 0.0F, 3, 1, 9, 0.0F);
        this.Wheels2 = new ModelRenderer(this, 42, 0);
        this.Wheels2.setRotationPoint(-4.5F, 1.5F, -1.0F);
        this.Wheels2.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.Battery1 = new ModelRenderer(this, 8, 58);
        this.Battery1.setRotationPoint(-3.75F, 0.0F, -4.5F);
        this.Battery1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(Battery1, 1.2217304763960306F, 0.0F, 0.0F);
        this.Barrel3 = new ModelRenderer(this, 0, 15);
        this.Barrel3.setRotationPoint(0.5F, -3.0F, -2.0F);
        this.Barrel3.addBox(0.0F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
        this.Powerlight = new ModelRenderer(this, 0, 35);
        this.Powerlight.setRotationPoint(0.5F, -0.2F, 0.5F);
        this.Powerlight.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.WholePlatform = new ModelRenderer(this, 0, 53);
        this.WholePlatform.setRotationPoint(-0.5F, -2.0F, 0.0F);
        this.WholePlatform.addBox(-4.0F, 0.0F, -4.5F, 9, 2, 9, 0.0F);
        this.Wheels1 = new ModelRenderer(this, 42, 0);
        this.Wheels1.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.Wheels1.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.Atena = new ModelRenderer(this, 0, 40);
        this.Atena.setRotationPoint(0.5F, 0.0F, 0.5F);
        this.Atena.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.setRotateAngle(Atena, 3.141592653589793F, 0.0F, 0.0F);
        this.Barrel2 = new ModelRenderer(this, 0, 15);
        this.Barrel2.setRotationPoint(-1.0F, -1.0F, -2.0F);
        this.Barrel2.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
        this.NeckPlatform = new ModelRenderer(this, 25, 0);
        this.NeckPlatform.setRotationPoint(0.0F, -0.5F, 2.0F);
        this.NeckPlatform.addBox(-1.5F, -0.5F, -2.0F, 4, 1, 4, 0.0F);
        this.MinigunBody = new ModelRenderer(this, 0, 0);
        this.MinigunBody.setRotationPoint(0.5F, -2.5F, 0.0F);
        this.MinigunBody.addBox(-2.0F, -4.0F, -3.0F, 4, 4, 6, 0.0F);
        this.Mag = new ModelRenderer(this, 10, 40);
        this.Mag.setRotationPoint(-1.7F, 1.0F, -2.0F);
        this.Mag.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4, 0.0F);
        this.MuzzleBreak = new ModelRenderer(this, 26, 20);
        this.MuzzleBreak.setRotationPoint(0.0F, -2.0F, -7.5F);
        this.MuzzleBreak.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 1, 0.0F);
        this.Tracks_3 = new ModelRenderer(this, 50, 30);
        this.Tracks_3.setRotationPoint(0.0F, 0.5F, 8.5F);
        this.Tracks_3.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.Tracks_2 = new ModelRenderer(this, 50, 30);
        this.Tracks_2.setRotationPoint(0.0F, 0.5F, -0.5F);
        this.Tracks_2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.Barrel4 = new ModelRenderer(this, 0, 15);
        this.Barrel4.setRotationPoint(1.0F, -1.0F, -2.0F);
        this.Barrel4.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
        this.Barrel1 = new ModelRenderer(this, 0, 15);
        this.Barrel1.setRotationPoint(-1.0F, -3.0F, -2.0F);
        this.Barrel1.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
        this.Wheels3 = new ModelRenderer(this, 42, 0);
        this.Wheels3.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.Wheels3.addBox(0.0F, 0.0F, 0.0F, 9, 2, 2, 0.0F);
        this.Tracks_1 = new ModelRenderer(this, 40, 38);
        this.Tracks_1.setRotationPoint(7.0F, -1.0F, -3.5F);
        this.Tracks_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 9, 0.0F);
        this.Tracks_6 = new ModelRenderer(this, 50, 30);
        this.Tracks_6.setRotationPoint(0.0F, 0.5F, 8.5F);
        this.Tracks_6.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1, 0.0F);
        this.Belt1.addChild(this.Belt2);
        this.Battery1.addChild(this.Battery2);
        this.NeckPlatform.addChild(this.Neck);
        this.MinigunBody.addChild(this.Belt1);
        this.Wheels2.addChild(this.Tracks);
        this.Battery2.addChild(this.Battery3);
        this.Tracks_1.addChild(this.Tracks_5);
        this.Tracks.addChild(this.Tracks_4);
        this.Belt2.addChild(this.Belt3);
        this.Tracks_1.addChild(this.Tracks_7);
        this.Insides.addChild(this.Wheels2);
        this.WholePlatform.addChild(this.Battery1);
        this.MinigunBody.addChild(this.Barrel3);
        this.Battery1.addChild(this.Powerlight);
        this.Insides.addChild(this.WholePlatform);
        this.Wheels2.addChild(this.Wheels1);
        this.Battery3.addChild(this.Atena);
        this.MinigunBody.addChild(this.Barrel2);
        this.WholePlatform.addChild(this.NeckPlatform);
        this.NeckPlatform.addChild(this.MinigunBody);
        this.Belt3.addChild(this.Mag);
        this.MinigunBody.addChild(this.MuzzleBreak);
        this.Tracks.addChild(this.Tracks_3);
        this.Tracks.addChild(this.Tracks_2);
        this.MinigunBody.addChild(this.Barrel4);
        this.MinigunBody.addChild(this.Barrel1);
        this.Wheels2.addChild(this.Wheels3);
        this.Wheels2.addChild(this.Tracks_1);
        this.Tracks_1.addChild(this.Tracks_6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.Insides.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
