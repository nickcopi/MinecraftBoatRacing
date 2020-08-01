package net.minecraftglobal.plugintest;

import net.minecraft.server.v1_16_R1.EntityBoat;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class RaceManager {
    private Date startTime;
    private ArrayList<Player> players;
    private ArrayList<RaceScore> scores;
    private HashMap<Integer,Location> playerRespawns;
    private boolean active;
    public RaceManager(){
        this.active = false;
    }
    public boolean start(){
        if(this.active) return false;
        this.active = true;
        this.players = new ArrayList<Player>();
        this.scores = new ArrayList<RaceScore>();
        this.playerRespawns = new HashMap<Integer, Location>();
        int playerNum = 0;
        for(Player player: Bukkit.getOnlinePlayers()){
            player.setHealth(20);
            player.setWalkSpeed(0.05f);
            World world = player.getWorld();
            //Location start = new Location(world,67 - playerNum*2,106,155);
            Location start = new Location(world,-93 ,102,28 - playerNum*2);
            playerRespawns.put(player.getEntityId(),start);
            Entity entity = world.spawnEntity(start, EntityType.BOAT);
            player.teleport(start);
            Vehicle boat = (Vehicle) entity;
            boat.addPassenger(player);
            this.players.add(player);
            player.setGameMode(GameMode.SURVIVAL);
            playerNum++;
        }
        startTime = new Date();
        return true;
    }
    public void finishPlayer(Player player){
        if(!this.players.contains(player)) return;
        this.players.remove(player);
        RaceScore score = new RaceScore(player, new Date());
        this.scores.add(score);
        player.setWalkSpeed(0.2f);
        this.removePlayerBoat(player);
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatColor.GREEN + "Finished with time " + score.getTime(this.startTime) + 's');
        if(this.players.size() == 0) this.end();

    }
    public void end(){
        this.active = false;
        Bukkit.broadcastMessage(ChatColor.BOLD + "Race over!");
        Collections.sort(this.scores);
        for(int i = 0; i < this.scores.size(); i++){
            RaceScore score = this.scores.get(i);
            Bukkit.broadcastMessage(i+1 + " " + score.getPlayer().getName() + " with time " + score.getTime(this.startTime) + 's');
        }
    }
    public void resetPlayer(Player player){
        World world = player.getWorld();
        Location start = this.getPlayerRespawns().get(player.getEntityId());
        //player.sendMessage(start.toString());
        Entity entity = world.spawnEntity(start, EntityType.BOAT);
        Vehicle boat = (Vehicle) entity;
        boat.addPassenger(player);
    }
    public void removePlayerBoat(Player player){
        Entity boat = player.getVehicle();
        if(boat != null) {
            boat.eject();
            boat.remove();
        }
    }

    /*private EntityType getBoatType(Player player){
        switch(player.getEntityId() % 5){
           // case 0:
               // return EntityBoat.EnumBoatType.
        }
    }*/

    public boolean isActive() {
        return active;
    }

    public HashMap<Integer, Location> getPlayerRespawns() {
        return playerRespawns;
    }
}
