package com.example.businesslayer;

import java.util.Random;

public class Fodder {
    private int available;
    public void setAvailable(int t){
        available=t;
    }
    public int getAvailable(){

        return(available);
    }
    public void randomizeFodder(){
        Random rand = new Random();
        available = rand.nextInt(81) + 20;
    }
}
