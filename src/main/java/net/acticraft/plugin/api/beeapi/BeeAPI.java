package net.acticraft.plugin.api.beeapi;

import net.acticraft.plugin.api.beeapi.regions.BukkitPlayerMoveEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.File;

import java.io.File;

public final class BeeAPI extends JavaPlugin {
 public static BeeAPI instance;
    @Override
    public void onEnable() {

        instance = this;
        createFolder();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getPluginManager().registerEvents(new BukkitPlayerMoveEvent(), instance);
    }

    private void createFolder() {
        String dir = plugin.getServer().getWorldContainer().getAbsolutePath();
        if (dir.endsWith(".")) {
            dir = dir.substring(0, dir.length() - 1);
        }
        File tempFile = new File(dir + "BeeAPI");
        if (!tempFile.exists()) {
            new File(dir + "BeeAPI").mkdirs();
            Bukkit.getConsoleSender().sendMessage(ChatColor.of(new Color(51, 255, 255)) + "BeeAPI §7- " + ChatColor.of(new Color(0, 255, 0)) + "BeeAPI" + " folder was created.");
        }
    }

    private static void defaultFile() {
        JsonManager.createJsonFile("BeeAPI");
    }
    public static BeeAPI getInstance(){
        return plugin;
    }

    public static void createPluginConfig(Plugin plugin) {
        JsonManager.createJsonFile(plugin.getName());
    }


    public static BeeMySQL getMySQL() {
        return mysql;
    }

    public static void setupMySQL(String host, String dataBase, String username, String password) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.of("#FEE057")+"BeeAPI - Connecting to MySQL...");
        mysql = new BeeMySQL(host, dataBase, username, password);
    }
}
