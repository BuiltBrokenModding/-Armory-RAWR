package com.builtbroken.armory.rawr.client;

import com.builtbroken.armory.rawr.RAWR;
import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import com.builtbroken.armory.rawr.content.entity.RenderEntityRawr;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = RAWR.DOMAIN)
public class ClientReg
{
    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(RAWR.itemRAWR, 0, new ModelResourceLocation(RAWR.itemRAWR.getRegistryName(), "inventory"));
        RenderingRegistry.registerEntityRenderingHandler(EntityRawr.class, manager -> new RenderEntityRawr(manager));
    }
}
