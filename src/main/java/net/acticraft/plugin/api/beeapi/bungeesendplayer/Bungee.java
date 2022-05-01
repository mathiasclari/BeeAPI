package net.acticraft.plugin.api.beeapi.bungeesendplayer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.acticraft.plugin.api.beeapi.BeeAPI;
import org.bukkit.entity.Player;

public class Bungee {
    public static void sendPlayerToServer(Player player, String server) {
        ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
        byteArrayDataOutput.writeUTF("Connect");
        byteArrayDataOutput.writeUTF(server);
        player.sendPluginMessage(BeeAPI.instance, "BungeeCord", byteArrayDataOutput.toByteArray());
    }
}
