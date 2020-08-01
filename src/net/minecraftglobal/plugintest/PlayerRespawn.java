package net.minecraftglobal.plugintest;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
    private RaceManager raceManager;
    public PlayerRespawn(RaceManager raceManager){
        this.raceManager = raceManager;
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if(!raceManager.isActive()) return;
        Player player = event.getPlayer();
        World world = player.getWorld();
        Entity entity = world.spawnEntity(player.getLocation(), EntityType.BOAT);
        Vehicle boat = (Vehicle) entity;
        boat.addPassenger(player);
    }
}
