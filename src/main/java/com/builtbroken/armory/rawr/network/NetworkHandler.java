package com.builtbroken.armory.rawr.network;

import com.builtbroken.armory.rawr.RAWR;
import com.builtbroken.armory.rawr.network.effects.TargetHitPacket;
import com.builtbroken.armory.rawr.network.effects.TargetHitPacketHandler;
import com.builtbroken.armory.rawr.network.effects.WeaponFirePacket;
import com.builtbroken.armory.rawr.network.effects.WeaponFirePacketHandler;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 7/1/2018.
 */
public class NetworkHandler
{
    public static SimpleNetworkWrapper NETWORK;

    public static void init()
    {
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(RAWR.DOMAIN);
        NETWORK.registerMessage(WeaponFirePacketHandler.class, WeaponFirePacket.class, 0, Side.CLIENT);
        NETWORK.registerMessage(TargetHitPacketHandler.class, TargetHitPacket.class, 1, Side.CLIENT);
    }

    public static void sendToAllAround(TileEntity tileEntity, IMessage message)
    {
        NETWORK.sendToAllAround(message,
                new NetworkRegistry.TargetPoint(
                        tileEntity.getWorld().provider.getDimension(),
                        tileEntity.getPos().getX(),
                        tileEntity.getPos().getY(),
                        tileEntity.getPos().getZ(),
                        64
                ));
    }

    public static void sendToAllAround(Entity entity, IMessage message)
    {
        NETWORK.sendToAllAround(message,
                new NetworkRegistry.TargetPoint(
                        entity.world.provider.getDimension(),
                        entity.posX,
                        entity.posY,
                        entity.posZ,
                        64
                ));
    }
}
