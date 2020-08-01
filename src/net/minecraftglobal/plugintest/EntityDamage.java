package net.minecraftglobal.plugintest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    private RaceManager raceManager;
    public EntityDamage(RaceManager raceManager){
        this.raceManager = raceManager;
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        //player.sendMessage("Ow!");
        if(event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.LAVA) ||
                event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ) {
            event.setCancelled(true);
            return;
        }
        if(event.getFinalDamage() > player.getHealth()){
            event.setCancelled(true);
            player.setHealth(20);
            this.raceManager.resetPlayer(player);
        }

    }
}
