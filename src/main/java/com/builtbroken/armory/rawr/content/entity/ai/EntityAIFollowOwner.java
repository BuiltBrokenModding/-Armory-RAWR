package com.builtbroken.armory.rawr.content.entity.ai;

import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowOwner extends EntityAIBase
{
    private final EntityRawr host;
    private final PathNavigate petPathfinder;

    private final double followSpeed;
    private final float maxDist;
    private final float minDist;

    private World world;
    private int timeToRecalcPath;

    public EntityAIFollowOwner(EntityRawr host, double followSpeedIn, float minDistIn, float maxDistIn)
    {
        this.host = host;
        this.world = host.world;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = host.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer entitylivingbase = getOwner();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (entitylivingbase.isSpectator())
        {
            return false;
        }
        else if (!this.host.shouldFollowOwner())
        {
            return false;
        }
        else if (this.host.getDistanceSq(entitylivingbase) < (double) (this.minDist * this.minDist))
        {
            return false;
        }
        return true;
    }

    protected EntityPlayer getOwner()
    {
        return this.host.getOwner();
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return !this.petPathfinder.noPath()
                && getOwner() != null
                && this.host.getDistanceSq(getOwner()) > (double) (this.maxDist * this.maxDist)
                && this.host.shouldFollowOwner();
    }

    @Override
    public void startExecuting()
    {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void resetTask()
    {
        this.petPathfinder.clearPath();
    }

    @Override
    public void updateTask()
    {
        //this.host.getLookHelper().setLookPositionWithEntity(getOwner(), 10.0F, (float)this.host.getVerticalFaceSpeed());

        if (this.host.shouldFollowOwner())
        {
            if (--this.timeToRecalcPath <= 0)
            {
                this.timeToRecalcPath = 10;

                //triggers pathing logic
                if (!this.petPathfinder.tryMoveToEntityLiving(getOwner(), this.followSpeed))
                {
                    teleportToPlayer();
                }
            }
        }
    }

    protected void teleportToPlayer()
    {
        if (!this.host.getLeashed() && !this.host.isRiding())
        {
            //If entity is too far away teleport
            if (this.host.getDistanceSq(getOwner()) >= 144.0D)
            {
                int x = MathHelper.floor(getOwner().posX) - 2;
                int y = MathHelper.floor(getOwner().getEntityBoundingBox().minY);
                int z = MathHelper.floor(getOwner().posZ) - 2;

                //Find safe teleport location
                for (int dx = 0; dx <= 4; ++dx)
                {
                    for (int dz = 0; dz <= 4; ++dz)
                    {
                        if ((dx < 1 || dz < 1 || dx > 3 || dz > 3)
                                //Do teleport
                                && this.isTeleportFriendlyBlock(x, z, y, dx, dz))
                        {
                            //Aim towards player
                            this.host.setLocationAndAngles((double) ((float) (x + dx) + 0.5F), (double) y, (double) ((float) (z + dz) + 0.5F), this.host.rotationYaw, this.host.rotationPitch);

                            //Reset pathfinder
                            this.petPathfinder.clearPath();
                            return;
                        }
                    }
                }
            }
        }
    }

    protected boolean isTeleportFriendlyBlock(int x, int z, int y, int xOffset, int zOffset)
    {
        BlockPos blockpos = new BlockPos(x + xOffset, y - 1, z + zOffset);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(this.host) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
    }
}