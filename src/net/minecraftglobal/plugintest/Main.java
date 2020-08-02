package net.minecraftglobal.plugintest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private RaceManager raceManager;
    public Main(){
        super();
        this.raceManager = new RaceManager();
    }
    @Override
    public void  onEnable(){
        //on server startup/plugin reloads
        this.getServer().getPluginManager().registerEvents(new PlayerMove(this.raceManager),this);
        //this.getServer().getPluginManager().registerEvents(new PlayerRespawn(this.raceManager),this);
        this.getServer().getPluginManager().registerEvents(new VehicleExit(this.raceManager),this);
        this.getServer().getPluginManager().registerEvents(new EntityDamage(this.raceManager),this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeave(this.raceManager),this);
    }
    @Override
    public void  onDisable(){
        //opposite of onEnable
    }
    // basic /command implementation
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(label.equalsIgnoreCase("start")){
            if(this.raceManager.start()){
                sender.sendMessage(ChatColor.GREEN + "Race started!");
            } else {
                sender.sendMessage(ChatColor.RED +  "Cannot start race as it is already active!");
            }
            return true;
        }

        if(label.equalsIgnoreCase("hello")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                if( player.hasPermission("hello.use")) {
                    player.sendMessage(ChatColor.DARK_BLUE + "Test!");
                }
                player.sendMessage(ChatColor.DARK_RED + "No perms!");
            } else {
                sender.sendMessage("Ur not a player lol");
            }
            return true;
        }
        return false;
    }
}
