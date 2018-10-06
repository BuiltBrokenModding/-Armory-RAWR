package com.builtbroken.armory.rawr.content.entity;

import com.builtbroken.armory.rawr.content.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.World;

import java.util.UUID;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class EntityRawr extends EntityLiving
{
    public final String NBT_USERNAME = "username";
    public final String NBT_UUID = "uuid";

    private EntityPlayer owner;
    private String owner_username;
    private UUID owner_uuid;

    public EntityRawr(World worldIn)
    {
        super(worldIn);
        setSize(0.8f, 0.8f);
        setPathPriority(PathNodeType.WATER, -10F);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(6, new EntityAIFollowOwner(this,1.0D, 4, 15.0F));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    public EntityPlayer getOwner()
    {
        if(owner_uuid != null)
        {
            if (owner == null || owner.isDead || owner.getGameProfile() == null || owner.getGameProfile().getId() != owner_uuid)
            {
                owner = world.getPlayerEntityByUUID(owner_uuid);
            }
            return owner;
        }
        return null;
    }

    public void setOwner(EntityPlayer player)
    {
        this.owner_username = player.getGameProfile().getName();
        this.owner_uuid = player.getGameProfile().getId();
    }

    public boolean shouldFollowOwner()
    {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        if (owner_username != null)
        {
            compound.setString(NBT_USERNAME, owner_username);
        }
        if (owner_uuid != null)
        {
            compound.setTag(NBT_UUID, NBTUtil.createUUIDTag(owner_uuid));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (compound.hasKey(NBT_USERNAME))
        {
            owner_username = compound.getString(NBT_USERNAME);
        }
        if (compound.hasKey(NBT_UUID))
        {
            owner_uuid = NBTUtil.getUUIDFromTag(compound.getCompoundTag(NBT_UUID));
        }
    }
}
