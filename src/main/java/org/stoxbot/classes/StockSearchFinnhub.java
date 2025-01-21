package org.stoxbot.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockSearchFinnhub {
    @JsonProperty("description")
    String description;
    @JsonProperty("displaySymbol")
    String displaySymbol;
    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("type")
    String type;

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if(type == ""){
            type = "unknown";
        }
        return " Description: " + description + "\n Display Symbol: " + displaySymbol + "\n Symbol: " + symbol + "\n Type: " + type;
    }
}
