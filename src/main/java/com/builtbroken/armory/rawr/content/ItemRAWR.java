package com.builtbroken.armory.rawr.content;

import com.builtbroken.armory.rawr.RAWR;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
}
