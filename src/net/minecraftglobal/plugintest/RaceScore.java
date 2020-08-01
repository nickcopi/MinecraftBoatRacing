package net.minecraftglobal.plugintest;

import org.bukkit.entity.Player;

import java.util.Date;

public class RaceScore implements Comparable{
    private Player player;
    private Date date;
    public RaceScore(Player player, Date date){
        this.player = player;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTime(Date startTime){
        return "" + (this.date.getTime() - startTime.getTime())/1000;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof RaceScore)) return 0;
        RaceScore otherScore = (RaceScore) o;
        return this.date.compareTo(otherScore.getDate());
    }
}
