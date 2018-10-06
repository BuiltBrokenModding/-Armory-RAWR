package com.builtbroken.armory.rawr.content;

import com.builtbroken.armory.rawr.RAWR;
import com.builtbroken.armory.rawr.content.entity.EntityRawr;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/5/2018.
 */
public class ItemRAWR extends Item
{
    public ItemRAWR()
    {
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName(RAWR.PREFIX + "rawr_spawner");
        setTranslationKey(RAWR.PREFIX + "rawr.spawner");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(I18n.format(getTranslationKey() + ".info"));
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            //Get position
            BlockPos blockpos = pos.offset(facing);

            //Get y offset to avoid collisions or getting stuck in walls
            double y_offset = this.getYOffset(worldIn, blockpos);

            //Create entity
            final EntityRawr entity = new EntityRawr(worldIn);

            //Set position
            entity.setPosition((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + y_offset, (double) blockpos.getZ() + 0.5D);

            //Spawn entity in world
            worldIn.spawnEntity(entity);

            //Set owner
            entity.setOwner(player);

            //Set name tag
            if (entity instanceof EntityLivingBase && itemstack.hasDisplayName())
            {
                entity.setCustomNameTag(itemstack.getDisplayName());
            }

            //Consume item
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    protected double getYOffset(World world, BlockPos blockPos) //TODO check max place distance
    {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockPos)).expand(0.0D, -1.0D, 0.0D);
        List<AxisAlignedBB> list = world.getCollisionBoxes((Entity) null, axisalignedbb);

        if (list.isEmpty())
        {
            return 0.0D;
        }
        else
        {
            double y = axisalignedbb.minY;

            for (AxisAlignedBB axisalignedbb1 : list)
            {
                y = Math.max(axisalignedbb1.maxY, y);
            }

            return y - (double) blockPos.getY();
        }
    }
}
