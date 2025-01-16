package org.stoxbot.commands;

import org.stoxbot.APIstuff.StockInfoFinnHub;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class PriceCommand {
    String requestSymbol = "null";
    String[] EasterEggNames = {"egon", "taavi", "karl", "kristo", "sander"};

    //Class for current, lowest, and the highest prices of the day
    //Since the commands are basically the same, I decided to put them into one class

    public String Command(String userMessage, String whichPrice) {
        requestSymbol = userMessage.toString();
        if(requestSymbol.equals("null")){
            return "No stock symbol found!";
        } else if (Arrays.asList(EasterEggNames).contains(requestSymbol)) {
            return priceEasterEgg(requestSymbol);
        }
        else {
            StockInfoFinnHub gettingPrice = new StockInfoFinnHub(requestSymbol);

            switch (whichPrice){
                case "current":
                    return gettingPrice.getCurrentPrice();
                case "highest":
                    return gettingPrice.getHighestPriceToday();
                case "lowest":
                    return gettingPrice.getLowestPriceToday();
            }

            return gettingPrice.getCurrentPrice();
        }
    }

    public String priceEasterEgg(String name){
        switch (name){
            case "karl":
                return "The creator of this bot is worth karlillion euros frfr";
            case "kristo":
                return "Karl's friends' worth can not be put into money";
            case "egon":
                return "You cannot exchange Karl's emotional support slenderman for money!";
            case "sander":
                return "You cannot buy Karl's child slave for money!";
            case "taavi":
                Random rnd = new Random();
                int rndInt = rnd.nextInt(100);
                return rndInt + " cumcoins";
            default:
                return "Congrats! You found a bug inside an easter egg, somehow.";
        }
    }
}
