package com.github.kernelshaw;

import org.javacord.api.entity.user.User;

public class Gambler implements java.io.Serializable {

    private String name;
    private long userID;
    private int wins;
    private int losses;

    public Gambler(String name, User username){
        this.name = name;
        this.userID = username.getId();
    }

    public void verdict(boolean result){
        if (result)
            wins++;
        else
            losses++;
    }

    public double winRatio(){
        int total = wins + losses;
        double convert = ((double)wins/(double)total) * 100;
        return Math.round(convert * 100.0) / 100.0;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public long getUserID() {
        return userID;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        String stats = "";
        stats += "This player is " + name + ".";
        stats += "They have " + wins + " wins and " + losses + " losses, for a win ratio of about " + winRatio() + "%.";
        return stats;
    }
}
