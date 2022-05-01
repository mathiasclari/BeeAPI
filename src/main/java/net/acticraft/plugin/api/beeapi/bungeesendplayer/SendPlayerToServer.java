package net.acticraft.plugin.api.beeapi.bungeesendplayer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.acticraft.plugin.api.beeapi.BeeAPI;
import org.bukkit.entity.Player;

public class SendPlayerToServer {
    public SendPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(BeeAPI.instance, "BungeeCord", out.toByteArray());
    }
}
