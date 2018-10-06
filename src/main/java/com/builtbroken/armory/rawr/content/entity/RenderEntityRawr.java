package com.builtbroken.armory.rawr.content.entity;

import com.builtbroken.armory.rawr.RAWR;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class RenderEntityRawr extends RenderLiving<EntityRawr>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(RAWR.DOMAIN, RAWR.TEXTURE_DIRECTORY + "entity/rawr.minigun.png");

    public RenderEntityRawr(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelRawrMinigun(), 0.3f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRawr entity)
    {
        return TEXTURE;
    }
}
