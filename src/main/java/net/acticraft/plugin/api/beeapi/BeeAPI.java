package net.acticraft.plugin.api.beeapi;

import net.acticraft.plugin.api.beeapi.regions.BukkitPlayerMoveEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
        String dir = instance.getServer().getWorldContainer().getAbsolutePath();
        if(dir.endsWith(".")) {
            dir = dir.substring(0, dir.length() - 1);
        }
        File tempFile = new File(dir + "BeeAPI");
        if(!tempFile.exists()) {
            new File(dir + "MyBuild").mkdirs();
            Bukkit.getLogger().info(ChatColor.of("#FFE29F")+"BeeAPI §7- "+ChatColor.of("#FFE29F")+"BeeAPI"+" folder was created.");
        }
    }
}
