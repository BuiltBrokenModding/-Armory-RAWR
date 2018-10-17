package com.builtbroken.armory.rawr.content.entity.ai;

import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.util.math.MathHelper;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/16/2018.
 */
public class EntityLookHelper2 extends EntityLookHelper
{
    public EntityLookHelper2(EntityRawr entitylivingIn)
    {
        super(entitylivingIn);
    }

    @Override
    public void onUpdateLook()
    {
        if (this.isLooking)
        {
            this.isLooking = false;
            double deltaX = this.posX - this.entity.posX;
            double deltaY = this.posY - (this.entity.posY + (double) this.entity.getEyeHeight());
            double deltaZ = this.posZ - this.entity.posZ;

            double distance = (double) MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);

            float yaw = (float) (MathHelper.atan2(deltaZ, deltaX) * (180D / Math.PI)) - 90.0F;
            float pitch = (float) (-(MathHelper.atan2(deltaY, distance) * (180D / Math.PI)));

            this.entity.rotationPitch = this.updateRotation(this.entity.rotationPitch, pitch, this.deltaLookPitch);
            this.entity.rotationYawHead = this.updateRotation(this.entity.rotationYawHead, yaw, this.deltaLookYaw);
        }
        else if(entity.getAttackTarget() == null)
        {
            this.entity.rotationYawHead = this.updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, 10.0F);
        }
    }
}
