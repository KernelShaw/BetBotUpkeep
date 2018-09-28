package com.github.kernelshaw;

import org.javacord.api.entity.user.User;

public class Bet{

    private long better;
    private long bettee;

    public Bet(User work, User bettee){
        this.better = work.getId();
        this.bettee = bettee.getId();
    }

    public void verdict(User winner){
        if (better == winner.getId()){
            Gamblers.getGambler(better).verdict(true);
            Gamblers.getGambler(bettee).verdict(false);
        }
        else if (bettee == winner.getId()){
            Gamblers.getGambler(bettee).verdict(true);
            Gamblers.getGambler(better).verdict(false);
        }
        Gamblers.save();
    }
}
