package net.minecraftglobal.plugintest;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class VehicleExit implements Listener {
    private RaceManager raceManager;
    private int resets;
    //private boolean inReset;
    public VehicleExit(RaceManager raceManager){
        this.raceManager = raceManager;
        this.resets = 0;
        //this.inReset = false;
    }

    @EventHandler
    public void exit(VehicleExitEvent event) {
        return;
        //event.getExited().remove();
        //if(!raceManager.isActive()) return;
        //if(!(event.getExited() instanceof  Player)) return;
        //Player player = (Player) event.getExited();
        //player.sendMessage("Hopped out da whip");
        //this.resetPlayer(player);
    }
    @EventHandler
    public void destroy(VehicleDestroyEvent event){
        //event.getVehicle().remove();
        if(!raceManager.isActive()) return;
        if(event.getVehicle().getPassengers().size() == 0) return;
        if(!(event.getVehicle().getPassengers().get(0) instanceof Player)) return;
        Player player = (Player) event.getVehicle().getPassengers().get(0);
        player.sendMessage("Broke da whip");
        this.resetPlayer(player);

    }
    private void resetPlayer(Player player){
        if(this.resets > 10) return;
        //this.inReset = true;
        this.resets++;
        World world = player.getWorld();
        Location start = this.raceManager.getPlayerRespawns().get(player.getEntityId());
        if(start == null) player.sendMessage("No bed spawn location???");
        else player.sendMessage(start.toString());
        Entity entity = world.spawnEntity(start, EntityType.BOAT);
        Vehicle boat = (Vehicle) entity;
        boat.addPassenger(player);
        //this.inReset = false;
    }
}
