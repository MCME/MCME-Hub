package com.mcmiddleearth.newplayerworldmaski.util;

import com.mcmiddleearth.newplayerworldmaski.NewPlayerWorldMaski;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author Jubo
 */
public class PluginData {

    private static FileConfiguration config;

    public static boolean hasPermission(Player player, Permissions perm){
        return player.hasPermission(perm.getPermissionNode());
    }

    public static void loadConfig(){
        config = NewPlayerWorldMaski.getInstance().getConfig();
    }

    public static int getBlindnessTime(){
        return config.getConfigurationSection("blindness").getInt("time");
    }

    public static int getCinematicTime(String cinematicNum){
        return config.getConfigurationSection("cinematicTime").getInt(cinematicNum);
    }

    public static String getCinematicName(String cinematicNum){
        return config.getConfigurationSection("cinematicCommand").getString(cinematicNum);
    }
}
