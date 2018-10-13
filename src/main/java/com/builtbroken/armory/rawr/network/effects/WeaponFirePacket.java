package com.builtbroken.armory.rawr.network.effects;

import com.builtbroken.armory.rawr.network.PositionPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/13/2018.
 */
public class WeaponFirePacket extends PositionPacket
{
    public Vec3d heading;

    public WeaponFirePacket()
    {

    }

    public WeaponFirePacket(World world, Vec3d position, Vec3d heading)
    {
        this(world.provider.getDimension(), position, heading);
    }

    public WeaponFirePacket(int dim, Vec3d position, Vec3d heading)
    {
        super(dim, position);
        this.heading = heading;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);
        heading = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);
        buf.writeDouble(heading.x);
        buf.writeDouble(heading.y);
        buf.writeDouble(heading.z);
    }

}
