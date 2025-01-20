package org.stoxbot.APIstuff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stoxbot.Environment;
import org.stoxbot.classes.StockSearchFinnhub;
import org.stoxbot.commands.SubcommandStatus;

import java.util.List;

import static org.stoxbot.Main.evokedCommand;

public class SymbolInfoFinnHub {
    private String requestString;
    private final String APItoken;
    private APIrequest apiRequest;
    String apiResponse;
    String requestURL;
    String commandResponse = "default";
    int startIndex = 0;

    public SymbolInfoFinnHub(String requestString) {
        this.requestString = requestString;
        APItoken = "token=" + Environment.getFinnhubAPIKey();
    }

    public String Search(){
        requestURL = "https://finnhub.io/api/v1/search?q=" + requestString.toUpperCase() + "&exchange=US&" + APItoken;

        apiRequest = new APIrequest(requestURL);
        apiResponse = apiRequest.makeRequest();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode results = objectMapper.readTree(apiResponse);

            //Make sure the command displays only the first 5 results if there are more than 5
            int resultsAmount = results.get("count").asInt();
            if(resultsAmount > 5){
                commandResponse = "Showing the first 5 results of your search: \n";
                resultsAmount = 5;
                //Adding "next" subcommand
                evokedCommand.setSubCommandStatus(SubcommandStatus.SEARCH_STOCK);
            }
            else {
                commandResponse = "Showing the results of your search: \n";
            }

            String resultsString = results.get("result").toString();

            //Make this result into a list of our custom FinnhubStockInfoClass
            List<StockSearchFinnhub> SearchResultsList = objectMapper.readValue(resultsString, new TypeReference<List<StockSearchFinnhub>>() {});

            for (startIndex = 0; startIndex < resultsAmount; startIndex++){
                commandResponse += "----- RESULT NO. " + (startIndex + 1) + " -----\n";
                commandResponse += SearchResultsList.get(startIndex) + "\n";
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return commandResponse;
    }
}
