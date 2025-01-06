package org.stoxbot.APIstuff;

import org.stoxbot.Environment;

public class SymbolInfoFinnHub {
    private String requestString;
    private final String APItoken;
    private APIrequest apiResponse;
    String requestURL;

    public SymbolInfoFinnHub(String requestString) {
        this.requestString = requestString;
        APItoken = "token=" + Environment.getFinnhubAPIKey();
    }

    public String SymbolToName(String symbolRequest){
        symbolRequest = symbolRequest.toUpperCase();
        requestURL = "https://finnhub.io/api/v1/search?q=" + symbolRequest;


        return "";
    }
}
