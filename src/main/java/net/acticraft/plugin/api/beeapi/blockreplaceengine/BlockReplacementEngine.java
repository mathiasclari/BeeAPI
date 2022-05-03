package net.acticraft.plugin.api.beeapi.blockreplaceengine;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockReplacementEngine {
    public static void checkBlockReplace(BlockBreakEvent e, Material allowedTool, boolean requirement, boolean restoreRequirement, Material temporaryBlockReplacement, Sound breakSound, long delay, Material replaceWith, BlockData blockData, List<Material> allowedBlockTypes) { // overload for single allowed tool
        List<Material> allowedTools = new ArrayList<>();
        if (allowedTool != null && allowedTool != Material.AIR) allowedTools.add(allowedTool);
        checkBlockReplace(e, allowedTools, requirement, restoreRequirement, temporaryBlockReplacement, breakSound, delay, replaceWith, blockData, allowedBlockTypes);
    }

    public static void checkBlockReplace(BlockBreakEvent e, List<Material> allowedTools, boolean requirement, boolean restoreRequirement, Material temporaryBlockReplacement, Sound breakSound, long delay, Material replaceWith, BlockData blockData, Material allowedBlockType) { // overload for single allowed block replacement type
        List<Material> allowedBlockTypes = new ArrayList<>();
        if (allowedBlockType != null) allowedBlockTypes.add(allowedBlockType);
        checkBlockReplace(e, allowedTools, requirement, restoreRequirement, temporaryBlockReplacement, breakSound, delay, replaceWith, blockData, allowedBlockTypes);
    }

    public static void checkBlockReplace(BlockBreakEvent e, List<Material> allowedTools, boolean requirement, boolean restoreRequirement, Material temporaryBlockReplacement, Sound breakSound, long delay, Material replaceWith, BlockData blockData, List<Material> allowedBlockTypes) {
        if (!requirement) return;
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if (!allowedTools.contains(p.getInventory().getItemInMainHand().getType()) && !allowedTools.isEmpty()) return;
        p.playSound(p.getLocation(), breakSound, 1, 1);
        if (temporaryBlockReplacement != null && temporaryBlockReplacement.isBlock()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("ActiSkyBlockCore"), () -> {
                b.getWorld().getBlockAt(b.getLocation()).setType(temporaryBlockReplacement);
            }, 1);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("ActiSkyBlockCore"), () -> {
            if (!restoreRequirement) return;
            Block newBlock = b.getWorld().getBlockAt(b.getLocation());
            if (((allowedBlockTypes == null || allowedBlockTypes.isEmpty()) && temporaryBlockReplacement != null && newBlock.getType() == temporaryBlockReplacement) || (allowedBlockTypes != null && allowedBlockTypes.contains(newBlock.getType()))) { // allowedBlockTypes is null/empty and the current block is the same as what it was replaced with OR allowed block types isn't empty and current block type is contained in it
                if (replaceWith.isBlock()) {
                    newBlock.setType(replaceWith);
                    if (blockData != null) newBlock.setBlockData(blockData);
                } else {
                    newBlock.setType(Material.AIR);
                }
            }
        }, delay * 20L);
    }
}
