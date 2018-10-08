package com.builtbroken.armory.rawr.content.entity.ai;

import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class TaskAttackWhenHarmed extends EntityAITarget
{
    /** Store the previous revengeTimer value */
    private int revengeTimerOld;

    public TaskAttackWhenHarmed(EntityRawr creatureIn)
    {
        super(creatureIn, true);
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        int i = this.taskOwner.getRevengeTimer();
        EntityLivingBase entitylivingbase = this.taskOwner.getRevengeTarget();
        return i != this.revengeTimerOld && entitylivingbase != null && this.isSuitableTarget(entitylivingbase, false);
    }

    @Override
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.taskOwner.getRevengeTarget());
        this.target = this.taskOwner.getAttackTarget();
        this.revengeTimerOld = this.taskOwner.getRevengeTimer();
        this.unseenMemoryTicks = 300;

        super.startExecuting();
    }
}