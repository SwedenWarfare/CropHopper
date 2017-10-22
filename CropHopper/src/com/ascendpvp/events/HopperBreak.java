package com.ascendpvp.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ascendpvp.CropHopperMain;

public class HopperBreak implements Listener {

	CropHopperMain plugin;
	public HopperBreak(CropHopperMain plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onHopperBreak(BlockBreakEvent e) {

		if(e.isCancelled()) return;
		if(e.getBlock() == null) return;
		Block blockBroke = e.getBlock();
		Player p = e.getPlayer();
		if(blockBroke.getType() != Material.HOPPER) return;

		int chunkX = blockBroke.getChunk().getX();
		int chunkZ = blockBroke.getChunk().getZ();
		int hopperX = blockBroke.getX();
		int hopperY = blockBroke.getY();
		int hopperZ = blockBroke.getZ();
		String hopperSave = String.valueOf(chunkX) + String.valueOf(chunkZ);
		int configX = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "x");
		int configY = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "y");
		int configZ = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "z");
		
		if(plugin.getConfig().getString("hopperlocs." + hopperSave) == null) return;
		if(configX == hopperX && configY == hopperY && configZ == hopperZ) {
			
			ItemStack buyTnt = new ItemStack(Material.HOPPER);
			ItemMeta buyTntIm = buyTnt.getItemMeta();
			buyTntIm.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("hopper_name")));
			buyTnt.setItemMeta(buyTntIm);
			
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			e.getBlock().getWorld().dropItem(blockBroke.getLocation(), buyTnt);
			p.sendMessage(cc(plugin.getConfig().getString("messages.hopper_break_success")));
			plugin.getConfig().set("hopperlocs." + hopperSave, null);
			plugin.saveConfig();
		}
	}
	public String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
