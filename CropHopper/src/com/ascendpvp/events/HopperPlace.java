package com.ascendpvp.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.ascendpvp.CropHopperMain;

public class HopperPlace implements Listener {

	CropHopperMain plugin;
	public HopperPlace(CropHopperMain plugin){
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onHopperPlace(BlockPlaceEvent e) {

		if(e.isCancelled()) return;
		if(e.getItemInHand() == null) return;
		ItemStack blockPlaced = e.getItemInHand();
		Player p = e.getPlayer();
		if(blockPlaced.getType() != Material.HOPPER) return;
		if(!blockPlaced.hasItemMeta() || !blockPlaced.getItemMeta().hasDisplayName()) return;
		if(!blockPlaced.getItemMeta().getDisplayName().equals(cc(plugin.getConfig().getString("hopper_name")))) return;

		int chunkX = e.getBlockPlaced().getChunk().getX();
		int chunkZ = e.getBlockPlaced().getChunk().getZ();
		int hopperX = e.getBlockPlaced().getX();
		int hopperY = e.getBlockPlaced().getY();
		int hopperZ = e.getBlockPlaced().getZ();
	    
		String hopperSave = String.valueOf(chunkX) + String.valueOf(chunkZ);

		if(plugin.getConfig().getString("hopperlocs." + hopperSave) == null) {

			p.sendMessage(cc(plugin.getConfig().getString("messages.hopper_place_success")));
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "chunkx", chunkX);
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "chunkz", chunkZ);
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "x", hopperX);
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "y", hopperY);
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "z", hopperZ);
			plugin.getConfig().set("hopperlocs." + hopperSave + "." + "world", e.getBlockPlaced().getWorld().getName());
			plugin.saveConfig();

		} else {
			e.setCancelled(true);
			p.sendMessage(cc(plugin.getConfig().getString("messages.hopper_already_in_chunk")));
		}
	}
	public String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
