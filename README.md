# ActiCraftBeeAPI
Plugin was originaly made by JernejTDO and alandioda, but was semi-recoded by PxLib.

<article align="center">
<hr>
<h4>MAVEN IMPLEMENTATION:</h4>
</article>
<article align="left">

```xml
<repository>
   <id>maven-releases</id>
   <url>http://jernejtdo.si:8081/repository/maven-releases/</url>
</repository>
```

```xml
<dependency>
  <groupId>net.acticraft.plugin.api</groupId>
  <artifactId>BeeAPI</artifactId>
  <version>1.0.2</version>
</dependency>
```

    
</article>

<article align="left">
<hr>
<h4>REGIONS:</h4>

Create a region

<p> First you need to make to make 2 locations.
Create the region.
Last step add the region to the list.

```java
Location loc1 = new Location(Bukkit.getWorld("<World Name>"), 0, 0, 0);
Location loc2 = new Location(Bukkit.getWorld("<World Name>"), 0, 0, 0);
Region region = new Region("<Region Name>", loc1, loc2);
Regions.addRegion(region);
```
Region Events

PlayerEnterRegionEvent - When a player joins the region.
PlayerExitRegionEvent - When a player leaves a region.

```java
@EventHandler
public void regionEnter(PlayerEnterRegionEvent event) {
}
```

```java 
@EventHandler
public void Region(PlayerExitRegionEvent event) {
}
```

Get info of region.


```java 
Region region = null; //Your region
Location loc = null;  //Bukkit location

Regions.addRegion(region); //Adds a region to the plugin.
Regions.deleteRegion(region); //Removes the region from the plugin.
Regions.getRegionInLocation(loc); //Gets the region in that location.
Regions.getRegions(); //List of all the regions created.
Regions.getRegionsByName("<Name>"); //Gets the region by name.
Regions.getRegionsInLocation(loc); //List of all the regions in that location.
```

</article>

<article align="left">
<hr>
<h5>MYSQL:</h5>

Connect to database. put in onEnable()
```java 
@Override
public void onEnable() {
BeeAPI.setupMySQL("Host", "Data Base", "Username", "Password");
}
```
You need to install this dependency
```xml 
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.21</version>
</dependency>
```
Get the connection to data base.
```java 
BeeAPI.mysql.GetConnection();
```
</article>
<hr>
<article align="left">
<h3>ScoreBoard:</h3>
<h5>Features</h5>

<p> No flickering (without using a buffer)</p>
<p> Easy to use</p>
<p> Dynamic scoreboard size: you don't need to add/remove lines, you can just give a string list (or array) to change all the lines</p>
<p> Everything is at packet level, so it works with other plugins using scoreboard and/or teams</p>
<p> Can be used asynchronously</p>
<p> No character limit on 1.13 and higher</p>
<p> Supports hex colors on 1.16 and higher</p>

#6 Creating a scoreboard

<p>Just create a new `BeeScoreboard` and update the title and the lines:</p>

```java
BeeScoreboard board = new BeeScoreboard(player);

// Set the title
board.updateTitle(ChatColor.GOLD + "ScoreBoard");

// Change the lines
board.updateLines(
        "", // Empty line
        "One line",
        "",
        "Second line"
);
```

### Example

Just a small example plugin with a scoreboard that refreshes every second:

```java
package net.acticraft.plugin.example;

import net.acticraft.plugin.api.beeapi.scoreboard.BeeScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ExamplePlugin extends JavaPlugin implements Listener {

    private final Map<UUID, BeeScoreboard> boards = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (BeeScoreboard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        FastBoard board = new BeeScoreboard(player);

        board.updateTitle(ChatColor.RED + "BeeScoreBoard");

        this.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    private void updateBoard(BeeScoreboard board) {
        board.updateLines(
                "",
                "Players: " + getServer().getOnlinePlayers().size(),
                "",
                "Kills: " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS),
                ""
        );
    }
}
```

</article>
<hr>
<p align="center">
    &copy;McBee 2022
</p>
