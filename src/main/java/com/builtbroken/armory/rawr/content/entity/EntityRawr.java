package com.builtbroken.armory.rawr.content.entity;

import com.builtbroken.armory.rawr.content.entity.ai.TaskAttackWhenHarmed;
import com.builtbroken.armory.rawr.content.entity.ai.TaskFindTarget;
import com.google.common.base.Optional;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class EntityRawr extends EntityCreature implements IRangedAttackMob
{
    protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityRawr.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    public final String NBT_UUID = "uuid";

    private EntityPlayer owner;

    public EntityRawr(World worldIn)
    {
        super(worldIn);
        setSize(0.8f, 0.8f);
        setPathPriority(PathNodeType.WATER, -10F);
    }

    @Override
    protected void initEntityAI()
    {
        //this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 4, 15.0F));
        this.targetTasks.addTask(1, new TaskAttackWhenHarmed(this));
        this.targetTasks.addTask(2, new TaskFindTarget(this));
        this.targetTasks.addTask(3, new EntityAIAttackRanged(this, 1.0D, 3, 20.0F));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
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

    }

    @Override
    public void setSwingingArms(boolean swingingArms)
    {

    }
}
