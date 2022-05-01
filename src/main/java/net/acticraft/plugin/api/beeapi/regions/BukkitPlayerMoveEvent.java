package net.acticraft.plugin.api.beeapi.regions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BukkitPlayerMoveEvent implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if(event.getFrom().getBlockX() != event.getTo().getBlockX()
                || event.getFrom().getBlockY() != event.getTo().getBlockY()
                || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {

            Region to = Regions.getRegionInLocation(event.getTo());
            Region from = Regions.getRegionInLocation(event.getFrom());

            if(to != null) {
                if(from != null) {
                    if(to != from) {
                        PlayerEnterRegionEvent enterEvent = new PlayerEnterRegionEvent(event.getPlayer(), from, to, event.isCancelled());
                        Bukkit.getPluginManager().callEvent(enterEvent);
                        PlayerExitRegionEvent exitEvent = new PlayerExitRegionEvent(event.getPlayer(), from, to, enterEvent.isCanceled());
                        Bukkit.getPluginManager().callEvent(exitEvent);
                        event.setCancelled(exitEvent.isCanceled());
                    }
                } else {
                    PlayerEnterRegionEvent enterEvent = new PlayerEnterRegionEvent(event.getPlayer(), from, to, event.isCancelled());
                    Bukkit.getPluginManager().callEvent(enterEvent);
                    event.setCancelled(enterEvent.isCanceled());
                }
            } else {
                if(from != null) {
                    PlayerExitRegionEvent exitEvent = new PlayerExitRegionEvent(event.getPlayer(), from, to, event.isCancelled());
                    Bukkit.getPluginManager().callEvent(exitEvent);
                    event.setCancelled(exitEvent.isCanceled());
                }
            }
        }
    }
}