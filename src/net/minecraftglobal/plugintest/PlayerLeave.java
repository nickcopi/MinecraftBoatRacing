package net.minecraftglobal.plugintest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerLeave implements Listener {
    private RaceManager raceManager;
    public PlayerLeave(RaceManager raceManager){
        this.raceManager = raceManager;
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        if(!this.raceManager.isActive()) return;
        Player player = event.getPlayer();
        this.raceManager.removePlayerBoat(player);
        this.raceManager.removePlayer(player);

    }
}
