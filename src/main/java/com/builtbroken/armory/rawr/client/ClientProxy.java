package com.builtbroken.armory.rawr.client;

import com.builtbroken.armory.rawr.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class ClientProxy extends CommonProxy
{
    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public void spawnWeaponFireEffect(World world, Vec3d position, Vec3d heading)
    {
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, position.x, position.y, position.z, heading.x, heading.y, heading.z);
    }

    @Override
    public void spawnWeaponHitEffect(World world, Vec3d position)
    {
        world.spawnParticle(EnumParticleTypes.CRIT, position.x, position.y, position.z, 0, 0, 0);
    }
}
