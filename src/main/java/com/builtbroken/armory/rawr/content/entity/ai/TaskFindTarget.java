package com.builtbroken.armory.rawr.content.entity.ai;

import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskFindTarget extends EntityAITarget
{
    protected final Sorter sorter;

    public TaskFindTarget(EntityRawr creature)
    {
        super(creature, true, false);
        sorter = new Sorter(creature);
    }

    @Override
    public boolean shouldExecute()
    {
        return taskOwner.getAttackTarget() == null; //TODO validate target
    }

    protected AxisAlignedBB getTargetableArea()
    {
        return this.taskOwner.getEntityBoundingBox().grow(getTargetDistance(), 4.0D, getTargetDistance());
    }

    @Override
    public void startExecuting()
    {
        List<EntityLivingBase> list = this.taskOwner.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getTargetableArea(), IMob.MOB_SELECTOR);

        if (!list.isEmpty())
        {
            Collections.sort(list, this.sorter);
            this.taskOwner.setAttackTarget(list.get(0));
        }

        super.startExecuting();
    }

    public static class Sorter implements Comparator<Entity>
    {
        private final Entity entity;

        public Sorter(Entity entityIn)
        {
            this.entity = entityIn;
        }

        public int compare(Entity entity_a, Entity entity_b)
        {
            double d0 = this.entity.getDistanceSq(entity_a);
            double d1 = this.entity.getDistanceSq(entity_b);

            if (d0 < d1)
            {
                return -1;
            }
            else
            {
                return d0 > d1 ? 1 : 0;
            }
        }
    }
}