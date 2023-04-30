package com.mcmiddleearth.newplayerworldmaski;

import com.mcmiddleearth.newplayerworldmaski.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NewPlayerWorldMaski extends JavaPlugin {

    private static NewPlayerWorldMaski instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(),this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this,"mcme:connect");
    }

    public static NewPlayerWorldMaski getInstance(){return instance;}
}
