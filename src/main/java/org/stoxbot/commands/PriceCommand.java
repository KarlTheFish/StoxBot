package org.stoxbot.commands;

import org.stoxbot.APIstuff.StockInfoFinnHub;


public class PriceCommand {
    String requestSymbol;

    //Class for current, lowest, and the highest prices of the day
    //Since the commands are basically the same, I decided to put them into one class

    public String Command(String userMessage, String whichPrice) {
        requestSymbol = userMessage.toString();
        if(requestSymbol == "null"){
            return "No stock symbol found!";
        } else {
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
}
