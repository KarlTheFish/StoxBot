package org.stoxbot.commands;

import org.stoxbot.APIstuff.SymbolInfoFinnHub;
import org.stoxbot.classes.StockSearchFinnhub;

public class SymbolCommand {
    String requestSymbol = "null";

    //Class for the search command

    public String Command(String userMessage){
        requestSymbol = userMessage.toString();

        if(requestSymbol.equals("null")){
            return "Please enter a stock symbol or name to find information about!";
        }
        else {
            SymbolInfoFinnHub gettingInfo = new SymbolInfoFinnHub(requestSymbol);
            return gettingInfo.Search();
        }
    }
}
