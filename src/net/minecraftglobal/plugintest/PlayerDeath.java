package net.minecraftglobal.plugintest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    private RaceManager raceManager;
    public PlayerDeath(RaceManager raceManager){
        this.raceManager = raceManager;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){

    }
}
