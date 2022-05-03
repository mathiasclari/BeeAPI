# ActiCraftBeeAPI
Plugin was originaly made by JernejTDO and alandioda, but was semi-recoded by PxLib.

<article align="center">
<hr>
<h4>MAVEN IMPLEMENTATION:</h4>

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
<h4>MYSQL:</h4>

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
<p align="center">
    &copy;McBee 2022
</p>