package com.mcmiddleearth.newplayerworldmaski.listener;

import com.mcmiddleearth.newplayerworldmaski.handler.HandlerStates;
import com.mcmiddleearth.newplayerworldmaski.handler.handlerF;
import com.mcmiddleearth.newplayerworldmaski.util.Permissions;
import com.mcmiddleearth.newplayerworldmaski.util.PluginData;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(PlayerInteractEvent event){
        if(!PluginData.hasPermission(event.getPlayer(), Permissions.STAFF))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void swtichState(PlayerSwapHandItemsEvent event){
        if(!PluginData.hasPermission(event.getPlayer(), Permissions.STAFF)){
            event.setCancelled(true);
            handlerF.execute(event.getPlayer(), handlerF.getNextStateByItem(event.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void playerMove(PlayerMoveEvent event){
        if(!PluginData.hasPermission(event.getPlayer(),Permissions.STAFF)){
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void joinServer(PlayerJoinEvent event){
        if(!PluginData.hasPermission(event.getPlayer(), Permissions.STAFF)){
            Location loc = event.getPlayer().getLocation();
            loc.setPitch(80);
            event.getPlayer().teleport(loc);

            handlerF.execute(event.getPlayer(), HandlerStates.ONE);
        }
    }
}
