package com.github.kernelshaw;

import java.io.*;

public class Epic {

    private static int epictotal;

    public static void addCount(){
        epictotal++;
        try{
            FileOutputStream fileOut = new FileOutputStream("epicCount.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(epictotal);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static int getCount(){
        return epictotal;
    }

    public static void decCount(){
        epictotal--;
        try{
            FileOutputStream fileOut = new FileOutputStream("epicCount.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(epictotal);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void loadE(){
        try {
            FileInputStream fileIn = new FileInputStream("epicCount.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            epictotal = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Gambler class not found");
            c.printStackTrace();
        }
    }
}
