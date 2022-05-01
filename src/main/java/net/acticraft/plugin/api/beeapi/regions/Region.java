package net.acticraft.plugin.api.beeapi.regions;

import org.bukkit.Location;

public class Region {

    private String name;
    private Location location_0;
    private Location location_1;

    public Region(String regionName, Location location_0, Location location_1) {
        this.name = regionName;
        sortLocations(location_0, location_1);
    }

    private void sortLocations(Location loc1, Location loc2) {
        location_0 = new Location(loc1.getWorld(), Math.min(loc1.getX(), loc2.getX()), Math.min(loc1.getY(), loc2.getY()), Math.min(loc1.getZ(), loc2.getZ()));
        location_1 = new Location(loc1.getWorld(), Math.max(loc1.getX(), loc2.getX()), Math.max(loc1.getY(), loc2.getY()), Math.max(loc1.getZ(), loc2.getZ()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation_0() {
        return location_0;
    }

    public void setLocation_0(Location location_0) {
        sortLocations(location_0, location_1);
    }

    public Location getLocation_1() {
        return location_1;
    }

    public void setLocation_1(Location location_1) {
        sortLocations(location_0, location_1);
    }

    public boolean isInRegion(Location location) {
        boolean isInX = location.getX() >= location_0.getX() && location.getX() <= location_1.getX();
        boolean isInY = location.getY() >= location_0.getY() && location.getY() <= location_1.getY();
        boolean isInZ = location.getZ() >= location_0.getZ() && location.getZ() <= location_1.getZ();
        return isInX && isInY && isInZ;
    }
}
