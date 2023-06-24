package com.mcmiddleearth.newplayerworldmaski.handler;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mcmiddleearth.newplayerworldmaski.NewPlayerWorldMaski;
import com.mcmiddleearth.newplayerworldmaski.util.PluginData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Jubo, Maski98
 */
public class    handlerF {

    private static int blindness_time = PluginData.getBlindnessTime();
    private static int timer1 = PluginData.getCinematicTime("1");
    private static int timer2 = blindness_time + timer1 + PluginData.getCinematicTime("2");
    private static int timer3 = blindness_time + timer2 + PluginData.getCinematicTime("3");

    private static String cinematic1 = PluginData.getCinematicName("1");
    private static String cinematic2 = PluginData.getCinematicName("2");
    private static String cinematic3 = PluginData.getCinematicName("3");

    public static void execute(Player player, HandlerStates state){
        ItemStack clayHotbar = new ItemStack(Material.CLAY_BALL,1);
        ItemStack clayOffhand = new ItemStack(Material.CLAY_BALL,1);
        ItemMeta clayHotbarMeta = clayHotbar.getItemMeta();
        ItemMeta clayLefthandMeta = clayOffhand.getItemMeta();
        switch(state){
            case ONE:
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " +player.getName()+ " -1252 269 2224");
                clayHotbarMeta.setCustomModelData(475);
                clayLefthandMeta.setCustomModelData(474);
                break;
            case TWO:
                clayHotbarMeta.setCustomModelData(476);
                clayLefthandMeta.setCustomModelData(474);
                break;
            case THREE:
                clayHotbarMeta.setCustomModelData(477);
                clayLefthandMeta.setCustomModelData(474);
                break;
            case FOUR:
                player.getInventory().clear();
                //starting Cinematic 1
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pc startother " +player.getName()+ " cinematic1");
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        player.getInventory().clear();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " +player.getName()+ " -1322 267 2891");
                        setBlindness(player);
                    }
                }.runTaskLater(NewPlayerWorldMaski.getInstance(),timer1 * 20);

                //starting Cinematic 2

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        removeBlindness(player);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pc startother " +player.getName()+ " cinematic2");
                    }
                }.runTaskLater(NewPlayerWorldMaski.getInstance(),(timer1 + blindness_time) * 20);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "teleport " +player.getName()+ " -1124 307 2521");
                        setBlindness(player);
                    }
                }.runTaskLater(NewPlayerWorldMaski.getInstance(),(timer2 * 20));


                new BukkitRunnable(){
                    @Override
                    public void run() {
                        removeBlindness(player);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pc startother " +player.getName()+ " cinematic3");
                    }
                }.runTaskLater(NewPlayerWorldMaski.getInstance(),(timer2 + blindness_time) * 20);
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        teleportToMain(player);
                    }
                }.runTaskLater(NewPlayerWorldMaski.getInstance(),(timer3 + blindness_time) * 20);

        }
        clayHotbar.setItemMeta(clayHotbarMeta);
        clayOffhand.setItemMeta(clayLefthandMeta);
        for(int i = 0; i < 9;i++)
            player.getInventory().setItem(i,clayHotbar);
        player.getInventory().setItem(EquipmentSlot.OFF_HAND,clayOffhand);
    }

    private static void teleportToMain(Player player){
        Plugin connectPlugin = Bukkit.getPluginManager().getPlugin("MCME-Connect");
        if(connectPlugin.isEnabled()){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("world");
            out.writeUTF(player.getName());
            player.sendPluginMessage(NewPlayerWorldMaski.getInstance(),"mcme:connect", out.toByteArray());
        }
    }

    public static HandlerStates getNextStateByItem(Player player){
        int ModelData = player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();
        switch(ModelData){
            case 475:
                return HandlerStates.TWO;
            case 476:
                return HandlerStates.THREE;
            case 477:
                return HandlerStates.FOUR;
            default:
                return null;
        }
    }

    private static void setBlindness(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 15));
    }

    private static void removeBlindness(Player player){
        player.removePotionEffect(PotionEffectType.BLINDNESS);
    }
}
