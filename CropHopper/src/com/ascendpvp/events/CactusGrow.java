package com.ascendpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import com.ascendpvp.CropHopperMain;


public class CactusGrow implements Listener {
	
	CropHopperMain plugin;
	public CactusGrow(CropHopperMain plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onCactusGrow(ItemSpawnEvent e) {
		
		if(e.getEntity().getItemStack().getType() != Material.CACTUS) return;
		if(e.getEntity().getCustomName() != null) return;
		
		int spawnX = e.getEntity().getLocation().getChunk().getX();
		int spawnZ = e.getEntity().getLocation().getChunk().getZ();
		String hopperSave = String.valueOf(spawnX + String.valueOf(spawnZ));
		
		if(plugin.getConfig().getString("hopperlocs." + hopperSave) != null) {
			
			e.setCancelled(true);
			int hopperX = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "x");
			int hopperY = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "y");
			int hopperZ = plugin.getConfig().getInt("hopperlocs." + hopperSave + "." + "z");
			String hopperWorld = plugin.getConfig().getString("hopperlocs." + hopperSave + "." + "world");
			Location hopperLoc = new Location(Bukkit.getWorld(hopperWorld), hopperX, hopperY, hopperZ);
			Hopper hopper = (Hopper) hopperLoc.getBlock().getState();
			
			hopper.getInventory().addItem(new ItemStack(Material.CACTUS));
			
		}
	}

	public String cc(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
