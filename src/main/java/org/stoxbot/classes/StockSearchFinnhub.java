package org.stoxbot.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Override
    public String toString() {
        return " Description: " + description + "\n Display Symbol: " + displaySymbol + "\n Symbol: " + symbol + "\n Type: " + type;
    }
}
