package com.mcmiddleearth.newplayerworldmaski.util;

import org.bukkit.entity.Player;

public class PluginData {

    public static boolean hasPermission(Player player, Permissions perm){
        return player.hasPermission(perm.getPermissionNode());
    }
}
