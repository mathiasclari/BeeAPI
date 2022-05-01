package net.acticraft.plugin.api.beeapi.regions;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Regions {

    private static List<Region> regionsList = new ArrayList<>();

    public static void addRegion(Region region) {
        regionsList.add(region);
    }

    public static void deleteRegion(Region region) {
        regionsList.remove(region);
    }

    public static List<Region> getRegionsByName(String name) {
        List<Region> reg = new ArrayList<>();
        for(Region region : regionsList) {
            if(region.getName().equals(name)) {
                reg.add(region);
            }
        }
        return reg;
    }

    public static List<Region> getRegions() {
        return regionsList;
    }

    public static List<Region> getRegionsInLocation(Location loc) {
        List<Region> reg = new ArrayList<>();
        for(Region region : regionsList) {
            if(region.isInRegion(loc)) {
                reg.add(region);
            }
        }
        return reg;
    }

    public static Region getRegionInLocation(Location loc) {
        for(Region region : regionsList) {
            if(region.isInRegion(loc)) {
                return region;
            }
        }
        return null;
    }
}
