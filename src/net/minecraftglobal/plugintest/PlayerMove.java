package net.minecraftglobal.plugintest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    private RaceManager raceManager;
    public PlayerMove(RaceManager raceManager){
        this.raceManager = raceManager;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(event.getFrom().equals(event.getTo())) return;
        if(!this.raceManager.isActive()) return;
        Player player = event.getPlayer();
        //player.sendMessage("moved");
        Location underPlayer = player.getLocation().add(0,-1,0);
        Block under = underPlayer.getBlock();
        if(under.getType().equals(Material.BLACK_WOOL))
            raceManager.finishPlayer(player);
        if(under.getType().equals(Material.BLUE_ICE))
            this.raceManager.getPlayerRespawns().put(player.getEntityId(),underPlayer.add(0,1,0));
    }
}
