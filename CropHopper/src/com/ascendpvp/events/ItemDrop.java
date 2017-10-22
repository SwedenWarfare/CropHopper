package com.ascendpvp.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.ascendpvp.CropHopperMain;


public class ItemDrop implements Listener {

	CropHopperMain plugin;
	public ItemDrop(CropHopperMain plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent e) {

		if(e.getItemDrop().getItemStack().getType() != Material.CACTUS) return;
		int spawnX = e.getItemDrop().getLocation().getChunk().getX();
		int spawnZ = e.getItemDrop().getLocation().getChunk().getZ();
		String hopperSave = String.valueOf(spawnX + String.valueOf(spawnZ));
		if(plugin.getConfig().getString("hopperlocs." + hopperSave) == null) return;
		
		e.getItemDrop().setCustomName("1337CaCtUs");
	}
}
