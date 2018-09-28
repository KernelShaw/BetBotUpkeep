package com.github.kernelshaw;

import java.util.ArrayList;
import org.javacord.api.entity.user.User;
import java.io.*;

public class Gamblers implements java.io.Serializable {

    private static ArrayList<Gambler> gamblerList = new ArrayList<Gambler>();
    private static Bet currentBet;

    public Gamblers(){
        gamblerList = new ArrayList<>();
    }

    public static void addGambler(Gambler add){
        gamblerList.add(add);
        try{
            FileOutputStream fileOut = new FileOutputStream("gamblerSaveFile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gamblerList);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Gambler getGambler(long faggot){
        for (int i = 0; i < gamblerList.size(); i++){
            if (faggot == gamblerList.get(i).getUserID()){
                return gamblerList.get(i);
            }
        }
        return null;
    }

    public static boolean findGambler(long find){
        for (Gambler check:gamblerList){
            if (find == check.getUserID())
                return true;
        }
        return false;
    }

    public static String startBet(Bet newBet){
        if (currentBet == null){
            currentBet = newBet;
            return "The bet has commenced!";
        }
        else
            return "There's already a bet in progress! Don't ruin a man's bet!";
    }

    public static String verdict(User winner){
        currentBet.verdict(winner);
        currentBet = null;
        return winner.getName() + "wins!";
    }

    public static String getBet(){
        if (currentBet == null){
            return "There's no bet currently going on.";
        }
        else{
            return "There is a bet going on! Please be patient.";
        }
    }

    public static boolean getBetBool(){
        if (currentBet == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static String gamblerListString() {
        String list = "";
        for (Gambler cur: gamblerList){
            list += cur.getName() + " ";
        }
        return list;
    }

    public static void loadGamblers(){
        gamblerList = null;
        try {
            FileInputStream fileIn = new FileInputStream("gamblerSaveFile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gamblerList = (ArrayList<Gambler>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Gambler class not found");
            c.printStackTrace();
        }
    }

    public static void pussyOut(){
        currentBet = null;
    }

    public static String pathetic(){
        return "upsetkitty/" + (int)(Math.random() * 13) + ".jpg";
    }

    public static void save(){
        try{
            FileOutputStream fileOut = new FileOutputStream("gamblerSaveFile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gamblerList);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
}
