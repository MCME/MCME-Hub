package com.mcmiddleearth.newplayerworldmaski;

import com.mcmiddleearth.newplayerworldmaski.listener.PlayerListener;
import com.mcmiddleearth.newplayerworldmaski.util.PluginData;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Jubo
 */
public final class NewPlayerWorldMaski extends JavaPlugin {

    private static NewPlayerWorldMaski instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(),this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this,"mcme:connect");
        PluginData.loadConfig();
    }

    public static NewPlayerWorldMaski getInstance(){return instance;}
}
