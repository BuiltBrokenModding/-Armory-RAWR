package com.builtbroken.armory.rawr.content.entity;

import com.builtbroken.armory.rawr.config.ConfigDamage;
import com.builtbroken.armory.rawr.content.entity.ai.TaskAttackWhenHarmed;
import com.builtbroken.armory.rawr.content.entity.ai.TaskFindTarget;
import com.builtbroken.armory.rawr.content.entity.ai.TaskFireWeapon;
import com.builtbroken.armory.rawr.content.entity.ai.TaskFollowOwner;
import com.builtbroken.armory.rawr.network.NetworkHandler;
import com.builtbroken.armory.rawr.network.effects.TargetHitPacket;
import com.builtbroken.armory.rawr.network.effects.WeaponFirePacket;
import com.google.common.base.Optional;
import javafx.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class EntityRawr extends EntityCreature implements IRangedAttackMob, IEntityOwnable
{
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityRawr.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Float> WEAPON_YAW_DP = EntityDataManager.createKey(EntityRawr.class, DataSerializers.FLOAT);
    protected static final DataParameter<Float> WEAPON_PITCH_DP = EntityDataManager.createKey(EntityRawr.class, DataSerializers.FLOAT);

    public final String NBT_UUID = "uuid";

    private EntityPlayer owner;

    public float preWeaponYaw = 0f;
    public float preWeaponPitch = 0f;

    private final float rotationSpeed = 30f;

    public EntityRawr(World worldIn)
    {
        super(worldIn);
        setSize(0.8f, 0.8f);
        setPathPriority(PathNodeType.WATER, -10F);
    }

    @Override
    public void onEntityUpdate()
    {
        super.onEntityUpdate();

        //Store previous
        preWeaponPitch = getWeaponPitch();
        preWeaponYaw = getWeaponYaw();

        //Update rotation
        if (getAttackTarget() != null) //TODO can see target
        {
            double d0 = getAttackTarget().posX - posX;
            double d1 = getAttackTarget().posY - (posY + (double) getEyeHeight());
            double d2 = getAttackTarget().posZ - posZ;
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
            float f1 = (float) (-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
            setWeaponPitch(this.updateRotation(getWeaponPitch(), f1, rotationSpeed));
            setWeaponYaw(this.updateRotation(getWeaponYaw(), f, rotationSpeed));
        }
        else
        {
            setWeaponYaw(rotateToZero(getWeaponYaw(), rotationSpeed));
            setWeaponPitch(rotateToZero(getWeaponPitch(), rotationSpeed));
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        //Override entity rotation with weapon rotation
        rotationYawHead = getWeaponYaw();
        rotationPitch = getWeaponPitch();
    }

    private float rotateToZero(float angle, float speed)
    {
        if (angle > speed)
        {
            angle -= speed;
        }
        else if (angle < -speed)
        {
            angle += speed;
        }
        else
        {
            angle = 0;
        }
        return angle;
    }

    private float updateRotation(float angle, float targetAngle, float maxIncrease)
    {
        float f = MathHelper.wrapDegrees(targetAngle - angle);

        if (f > maxIncrease)
        {
            f = maxIncrease;
        }

        if (f < -maxIncrease)
        {
            f = -maxIncrease;
        }

        return angle + f;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(6, new TaskFollowOwner(this, 1.0D, 4, 15.0F));
        this.targetTasks.addTask(1, new TaskAttackWhenHarmed(this));
        this.targetTasks.addTask(2, new TaskFindTarget(this));
        this.targetTasks.addTask(3, new TaskFireWeapon(this, 1.0D, 20.0F));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D); //Also used for pathfinder range
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
        this.dataManager.register(WEAPON_YAW_DP, 0f);
        this.dataManager.register(WEAPON_PITCH_DP, 0f);
    }

    public EntityPlayer getOwner()
    {
        UUID owner_uuid = getOwnerId();
        if (owner_uuid != null)
        {
            if (owner == null || owner.isDead || owner.getGameProfile() == null || owner.getGameProfile().getId() != owner_uuid)
            {
                owner = world.getPlayerEntityByUUID(owner_uuid);
            }
            return owner;
        }
        return null;
    }

    @Nullable
    public UUID getOwnerId()
    {
        return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
    }

    public void setOwnerId(@Nullable UUID id)
    {
        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(id));
    }

    public void setOwner(EntityPlayer player)
    {
        setOwnerId(player.getGameProfile().getId());
    }

    public float getWeaponYaw()
    {
        return this.dataManager.get(WEAPON_YAW_DP);
    }

    public void setWeaponYaw(float yaw)
    {
        this.dataManager.set(WEAPON_YAW_DP, yaw);
    }

    public float getWeaponPitch()
    {
        return this.dataManager.get(WEAPON_PITCH_DP);
    }

    public void setWeaponPitch(float pitch)
    {
        this.dataManager.set(WEAPON_PITCH_DP, pitch);
    }

    public boolean shouldFollowOwner()
    {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        UUID owner_uuid = getOwnerId();
        if (owner_uuid != null)
        {
            compound.setTag(NBT_UUID, NBTUtil.createUUIDTag(owner_uuid));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (compound.hasKey(NBT_UUID))
        {
            setOwnerId(NBTUtil.getUUIDFromTag(compound.getCompoundTag(NBT_UUID)));
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        Vec3d start = new Vec3d(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ);
        Vec3d end = new Vec3d(target.posX, target.posY + (double) target.getEyeHeight(), target.posZ);

        //Check if we don't hit a block between us and the target
        RayTraceResult blockTrace = this.world.rayTraceBlocks(start, end, false, true, false);
        if (blockTrace == null)
        {
            Pair<Entity, Vec3d> result = findEntityOnPath(start, end);
            Entity entity = result.getKey();
            Vec3d hit = result.getValue();
            if (entity != null)
            {
                entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, this), ConfigDamage.MINIGUN_DAMAGE); //TODO make custom damage source and create a method for getting damage
                if (ConfigDamage.BYPASS_DAMAGE_RESISTANCE)
                {
                    entity.hurtResistantTime = 0;
                }
                if (hit != null)
                {
                    NetworkHandler.sendToAllAround(this, new TargetHitPacket(world, hit)); //TODO offset a little
                }
            }
        }

        Vec3d heading = start.subtract(end).normalize();
        Vec3d velocity = heading.scale(-1);


        Vec3d offset = new Vec3d(world.rand.nextFloat() * 0.1f, world.rand.nextFloat() * 0.1f, 0.6D);
        offset = offset.rotatePitch(-this.rotationPitch * 0.017453292F);
        offset = offset.rotateYaw(-this.rotationYawHead * 0.017453292F);

        Vec3d barrel = start.add(offset);
        NetworkHandler.sendToAllAround(this, new WeaponFirePacket(world, barrel, velocity.scale(0.1))); //TODO move start to barrel

        //TODO send packet to client to spawn effects
        //TODO send packet on hit to spawn effects
        //TODO play audio
    }

    @Nullable
    protected Pair<Entity, Vec3d> findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity result = null;
        Vec3d result_hit = null;

        //Get all entities in zone of hit
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, new AxisAlignedBB(start, end).grow(1.0D), null); //TODO fine tune attack area to ensure its in front of the gun
        double d0 = 0.0D;

        for (Entity entity : list)
        {
            if (entity != this && canHitEntity(entity))
            {
                //Calculate impact of bounding box
                AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow(0.30000001192092896D); //TODO see what this magic number is used for
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    //Find closest hit point
                    double distance = start.squareDistanceTo(raytraceresult.hitVec);
                    if (distance < d0 || d0 == 0.0D)
                    {
                        result = entity;
                        result_hit = raytraceresult.hitVec;
                        d0 = distance;
                    }
                }
            }
        }

        return new Pair(result, result_hit);
    }

    /**
     * Checks if the RAWR's weapon can hit the entity. This
     * allows for projectiles to pass through targets blocking
     * attacks to create a more enjoyable experience.
     *
     * @param entity - entity hit
     * @return true to allow the hit or pass through to next entity
     */
    protected boolean canHitEntity(Entity entity)
    {
        //TODO add support for Armory FoF system
        //TODO add config to enable/disable friendly fire
        if (entity instanceof IEntityOwnable)
        {
            return ((IEntityOwnable) entity).getOwnerId() != getOwnerId();
        }
        else if (entity instanceof EntityPlayer)
        {
            EntityPlayer player = ((EntityPlayer) entity);
            if (player.getGameProfile().getId() == getOwnerId())
            {
                return false;
            }
            else if (player.isCreative() || player.isSpectator())
            {
                return false;
            }
        }
        else if (entity.getTeam() != null && entity.getTeam().isSameTeam(getTeam()))
        {
            return entity.getTeam().getAllowFriendlyFire();
        }
        return true;
    }

    @Override
    public void setSwingingArms(boolean swingingArms)
    {

    }
}
