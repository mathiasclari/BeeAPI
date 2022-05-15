package net.acticraft.plugin.api.beeapi.jsonmanager;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.acticraft.plugin.api.beeapi.BeeAPI;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import net.md_5.bungee.api.ChatColor;

public class JsonManager {

    public static void createJsonFile(String name) {
        String dir = BeeAPI.getInstance().getServer().getWorldContainer().getAbsolutePath();
        if(dir.endsWith(".")) {
            dir = dir.substring(0, dir.length() - 1);
        }
        dir = dir + "EasyAPI/";
        File tempFile = new File(dir + name+".json");
        if(!tempFile.exists()) {
            JSONObject jsonFile = new JSONObject();
            try {
                FileWriter file = new FileWriter(dir+"/"+name+".json");
                Bukkit.getConsoleSender().sendMessage(ChatColor.of(new Color(51,255,255))+"EasyAPI §7- "+ChatColor.of(new Color(0,255,0))+name+".json"+" file was created.");
                file.write(jsonFile.toJSONString());
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
        }
    }

    public static JSONObject getJsonBody(String name) {
        String dir = BeeAPI.getInstance().getServer().getWorldContainer().getAbsolutePath();
        if(dir.endsWith(".")) {
            dir = dir.substring(0, dir.length() - 1);
        }
        dir = dir + "EasyAPI/";
        File tempFile = new File(dir + name+".json");
        if(tempFile.exists()) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(dir+name+".json"));
                JSONObject jsonObject =  (JSONObject) obj;
                return jsonObject;
            } catch (IOException | ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static void updateJsonBody(String name, JSONObject JSONObject) {
        String dir = BeeAPI.getInstance().getServer().getWorldContainer().getAbsolutePath();
        if(dir.endsWith(".")) {
            dir = dir.substring(0, dir.length() - 1);
        }
        dir = dir + "EasyAPI/";
        File tempFile = new File(dir + name+".json");
        if(tempFile.exists()) {
            try {
                FileWriter file = new FileWriter(dir+"/"+name+".json");
                Bukkit.getConsoleSender().sendMessage(ChatColor.of(new Color(51,255,255))+"EasyAPI §7- "+ChatColor.of(new Color(0,255,0))+name+".json"+" file was updated.");
                file.write(JSONObject.toJSONString());
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
