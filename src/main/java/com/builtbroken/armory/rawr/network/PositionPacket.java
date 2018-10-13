package com.builtbroken.armory.rawr.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/13/2018.
 */
public class PositionPacket implements IMessage
{
    public int dim;
    public Vec3d position;

    public PositionPacket()
    {
    }

    public PositionPacket(int dim, Vec3d position)
    {
        this.dim = dim;
        this.position = position;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        dim = buf.readInt();
        position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(dim);
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
    }
}
