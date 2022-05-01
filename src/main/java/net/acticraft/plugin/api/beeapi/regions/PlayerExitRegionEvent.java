package net.acticraft.plugin.api.beeapi.regions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerExitRegionEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private Region from;
    private Region to;
    private Player player;
    private boolean isCanceled;

    public PlayerExitRegionEvent(Player player, Region from, Region to, boolean isCanceled) {
        this.from = from;
        this.to = to;
        this.player = player;
        this.isCanceled = isCanceled;
    }

    public Region getFrom() {
        return from;
    }

    public Region getTo() {
        return to;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }


}
