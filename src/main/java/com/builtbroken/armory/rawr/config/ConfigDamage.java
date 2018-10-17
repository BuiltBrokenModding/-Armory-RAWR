package com.builtbroken.armory.rawr.config;

import com.builtbroken.armory.rawr.RAWR;
import net.minecraftforge.common.config.Config;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/16/2018.
 */
@Config(modid = RAWR.DOMAIN, name = "armoryrawr/damage")
@Config.LangKey("config.armoryrawr:damage.title")
public class ConfigDamage
{
    @Config.Name("bypass_damage_resistance")
    @Config.Comment("Allows disabling damage resistance for drone attacks. Normally Minecraft will enable an attack timer to prevent an entity from being attacked several times a second.")
    public static boolean BYPASS_DAMAGE_RESISTANCE = true;

    @Config.Name("mini_gun_damage")
    @Config.Comment("Damage done per attack by the drone when using the minigun")
    public static float MINIGUN_DAMAGE = 4f;

    @Config.Name("mini_gun_attack_time")
    @Config.Comment("Time in ticks to delay between attacks")
    public static int MINIGUN_ATTACK_TIME = 3;
}
