package com.builtbroken.armory.rawr.network.effects;

import com.builtbroken.armory.rawr.RAWR;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/13/2018.
 */
public class WeaponFirePacketHandler implements IMessageHandler<WeaponFirePacket, IMessage>
{
    @Override
    public IMessage onMessage(WeaponFirePacket message, MessageContext ctx)
    {
        if (Minecraft.getMinecraft() != null
                && Minecraft.getMinecraft().world != null
                && Minecraft.getMinecraft().world.provider.getDimension() == message.dim)
        {
            Minecraft.getMinecraft().addScheduledTask(() ->
                    RAWR.sideProxy.spawnWeaponFireEffect(Minecraft.getMinecraft().world, message.position, message.heading)
            ); //TODO consider not using Vec3d to avoid memory churn
        }

        return null;
    }
}
