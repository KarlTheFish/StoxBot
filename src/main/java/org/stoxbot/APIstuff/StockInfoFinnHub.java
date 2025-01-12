package org.stoxbot.APIstuff;

import org.stoxbot.Environment;

public class StockInfoFinnHub {
    private String requestSymbol;
    private final String APItoken;
    private APIrequest apiResponse;
    String requestURL;

    public StockInfoFinnHub(String requestSymbol) {
        this.requestSymbol = requestSymbol;
        APItoken = "token=" + Environment.getFinnhubAPIKey();
    }

    private void getPriceInfo(){ //helper method to request the stock info
        requestSymbol = requestSymbol.toUpperCase();
        requestURL = "https://finnhub.io/api/v1/quote?symbol=" + requestSymbol + "&" + APItoken;

        apiResponse = new APIrequest(requestURL);
        apiResponse.makeRequest();
    }

    //These 3 methods convert the chosen attributes from json to string
    public String getCurrentPrice(){
        getPriceInfo();
        return apiResponse.resJsonAttrToString("c");
    }

    public String getLowestPriceToday(){
        getPriceInfo();
        return apiResponse.resJsonAttrToString("l");
    }

    public String getHighestPriceToday(){
        getPriceInfo();
        return apiResponse.resJsonAttrToString("h");
    }
}
