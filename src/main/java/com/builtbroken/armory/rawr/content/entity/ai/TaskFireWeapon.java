package com.builtbroken.armory.rawr.content.entity.ai;

import com.builtbroken.armory.rawr.config.ConfigDamage;
import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class TaskFireWeapon extends EntityAIBase
{
    /** The entity the AI instance has been applied to */
    private final EntityRawr host;

    /**
     * A decrementing tick that spawns a ranged attack once this value reaches 0. It is then set back to the
     * maxRangedAttackTime.
     */
    private int rangedAttackTime;
    private final double entityMoveSpeed;
    private int seeTime;
    private final float attackRadius;
    private final float maxAttackDistance;

    public TaskFireWeapon(EntityRawr host, double movespeed, float maxAttackDistanceIn)
    {
        this.rangedAttackTime = -1;
        this.host = host;
        this.entityMoveSpeed = movespeed;
        this.attackRadius = maxAttackDistanceIn;
        this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        return this.host.getAttackTarget() != null;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return this.shouldExecute();
    }

    @Override
    public void resetTask()
    {
        this.seeTime = 0;
        this.rangedAttackTime = -1;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        final EntityLivingBase attackTarget = host.getAttackTarget();
        if (attackTarget != null)
        {
            double distanceSq = this.host.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
            boolean canSeeTarget = this.host.getEntitySenses().canSee(attackTarget);

            if (canSeeTarget)
            {
                ++this.seeTime;
            }
            else
            {
                this.seeTime = 0;
            }

            //Prevent chasing targets, stay near owner if possible
            if(!host.shouldFollowOwner())
            {
                //If not set to follow owner, handle pathing
                if (distanceSq <= (double) this.maxAttackDistance && this.seeTime >= 20)
                {
                    this.host.getNavigator().clearPath();
                }
                else
                {
                    this.host.getNavigator().tryMoveToEntityLiving(attackTarget, this.entityMoveSpeed);
                }
            }

            if (canSeeTarget) //TODO make sure aimed at target before firing
            {
                if (!host.getNavigator().noPath()) //TODO replace with something else, as the drone will fidget on aim due to no path in EntityLookHelper
                {
                    this.host.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
                }

                if (--this.rangedAttackTime == 0) //TODO move weapon cool down to drone, replace with attack sight delay
                {
                    if (!canSeeTarget)
                    {
                        return;
                    }

                    float distanceScale = MathHelper.sqrt(distanceSq) / this.attackRadius;
                    float distanceFactor = MathHelper.clamp(distanceScale, 0.1F, 1.0F);
                    this.host.attackEntityWithRangedAttack(attackTarget, distanceFactor);
                    this.rangedAttackTime = ConfigDamage.MINIGUN_ATTACK_TIME;
                }
                else if (this.rangedAttackTime < 0)
                {
                    this.rangedAttackTime = ConfigDamage.MINIGUN_ATTACK_TIME;
                }
            }
        }
    }
}