package org.stoxbot.commands;

import org.stoxbot.APIstuff.SymbolInfoFinnHub;
import org.stoxbot.SubcommandTool;

public class SymbolCommand {
    String requestSymbol = "null";
    SymbolInfoFinnHub gettingInfo;

    //Class for the search command

    public String Command(String userMessage){
        requestSymbol = userMessage.toString();

        if(requestSymbol.equals("null")){
            return "Please enter a stock symbol or name to find information about!";
        }
        else {
            gettingInfo = new SymbolInfoFinnHub(requestSymbol);
            return gettingInfo.Search();
        }
    }

    public String nextCommand(){
        SubcommandTool subcommandTool = SubcommandTool.getInstance();
        gettingInfo = (SymbolInfoFinnHub) subcommandTool.getParentObject();
        return gettingInfo.getNextResults();
        //return "next";
    }
}
