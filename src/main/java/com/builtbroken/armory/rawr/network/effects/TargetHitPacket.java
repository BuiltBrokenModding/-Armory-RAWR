package com.builtbroken.armory.rawr.network.effects;

import com.builtbroken.armory.rawr.network.PositionPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/13/2018.
 */
public class TargetHitPacket extends PositionPacket
{
    public TargetHitPacket()
    {

    }

    public TargetHitPacket(World world, Vec3d position)
    {
        this(world.provider.getDimension(), position);
    }

    public TargetHitPacket(int dim, Vec3d position)
    {
        super(dim, position);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
    }
}
