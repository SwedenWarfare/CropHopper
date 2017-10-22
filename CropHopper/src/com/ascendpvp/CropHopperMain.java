package com.ascendpvp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ascendpvp.events.CactusGrow;
import com.ascendpvp.events.HopperBreak;
import com.ascendpvp.events.HopperPlace;
import com.ascendpvp.events.ItemDrop;

public class CropHopperMain extends JavaPlugin {
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new CactusGrow(this), this);
		Bukkit.getPluginManager().registerEvents(new HopperPlace(this), this);
		Bukkit.getPluginManager().registerEvents(new ItemDrop(this), this);
		Bukkit.getPluginManager().registerEvents(new HopperBreak(this), this);
		//getCommand("genbucket").setExecutor(new GenBuy(this));
		saveDefaultConfig();
	}
}
