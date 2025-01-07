package org.stoxbot.APIstuff;

public class SearchResultFinnhubClass {
    String description;
    String displaySymbol;
    String symbol;
    String type;

    public SearchResultFinnhubClass(String description, String displaySymbol, String symbol, String type) {
        this.description = description;
        this.displaySymbol = displaySymbol;
        this.symbol = symbol;
        this.type = type;
    }
}
