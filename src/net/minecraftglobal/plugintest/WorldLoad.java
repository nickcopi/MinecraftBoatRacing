package net.minecraftglobal.plugintest;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Scanner;
import java.util.logging.Logger;

public class WorldLoad {
    public static Logger logger;
    public WorldLoad() {

    }

    public static boolean loadConfig() {
        World world = Bukkit.getWorld("world");
        world.setGameRule(GameRule.DO_MOB_SPAWNING,false);
        world.setGameRule(GameRule.DO_FIRE_TICK,false);
        Block block = world.getBlockAt(1, 1, 1);
        logger.info(block.toString());
        if (!(block.getState() instanceof Chest)) {
            logger.warning("Cannot find config chest at 1,1,1?");
            return false;
        }
        Chest chest = (Chest) block.getState();
        int count = 0;
        for (ItemStack item : chest.getBlockInventory().getContents()) {
            if(item != null && item.getType() == Material.WRITTEN_BOOK) {
                readBook(item,world);
                logger.info(""+count);
                continue;
            }
            if(item == null) continue;
            //logger.info(item.toString());
            Config.deadlyBlocks.add(item.getType());
        }
        logger.info(Config.deadlyBlocks.toString());
        return true;
    }
    public static void readBook(ItemStack item, World world){
        logger.info("Reading config book...");
        BookMeta bookMeta = (BookMeta) item.getItemMeta();
        String config = bookMeta.getPage(1);
        Scanner configReader = new Scanner(config);
        String spawnCoords = configReader.nextLine();
        Scanner coordsReader = new Scanner(spawnCoords);
        coordsReader.useDelimiter(",");
        int coords[] = new int[3];
        for(int i = 0; i < 3; i++){
            int next = Integer.parseInt(coordsReader.next().trim());
            coords[i] = next;
        }
        Config.raceStart = new Location(world,coords[0],coords[1],coords[2]);
        Config.raceAxisX = configReader.nextLine().equalsIgnoreCase("x");
        logger.info(Config.raceStart.toString());
    }
}
