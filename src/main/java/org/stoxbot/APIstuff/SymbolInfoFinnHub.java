package org.stoxbot.APIstuff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stoxbot.Environment;
import org.stoxbot.SubcommandTool;
import org.stoxbot.classes.StockSearchFinnhub;
import org.stoxbot.commands.SubcommandStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SymbolInfoFinnHub {
    private String requestString;
    private final String APItoken;
    private APIrequest apiRequest;
    String apiResponse;
    String requestURL;
    String commandResponse = "default";
    int i = 0;
    List<StockSearchFinnhub> SearchResults = new ArrayList<StockSearchFinnhub>();
    SubcommandTool subcommandStatus = SubcommandTool.getInstance();

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

            String resultsString = results.get("result").toString();

            //Make this result into a list of our custom FinnhubStockInfoClass
            List<StockSearchFinnhub> SearchResultsList = objectMapper.readValue(resultsString, new TypeReference<List<StockSearchFinnhub>>() {});

            //Filter the list of results to only show "Common Stock" type
            for(int j = 0; j < SearchResultsList.size(); j++){
                StockSearchFinnhub stock = SearchResultsList.get(j);
                if(Objects.equals(stock.getType(), "Common Stock")){
                    SearchResults.add(stock);
                }
            }

            //Make sure the command displays only the first 5 results if there are more than 5
            if(SearchResults.size() > 5){
                commandResponse = "Found " + SearchResults.size() + " results\n";
                commandResponse += "Showing the first 5 results of your search: \n";
                //Adding "next" subcommand
                subcommandStatus.setStatus(SubcommandStatus.SEARCH_STOCK);
                subcommandStatus.setParentObject(this);
            }
            else {
                commandResponse = "Showing the results of your search: \n";
            }

            for (i = 0; i < 5; i++){
                commandResponse += "----- RESULT NO. " + (i + 1) + " -----\n";
                commandResponse += SearchResults.get(i) + "\n";
            }


        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return commandResponse;
    }

    public String getNextResults(){
        commandResponse = "Showing page " + ((i / 5) + 1) + " of your search: \n";
        int remainingResults = SearchResults.size() - i;
        if(remainingResults > 5){
            for(int j = i; j < i + 5; j++){
                commandResponse += "----- RESULT NO. " + (j + 1) + " -----\n";
                commandResponse += SearchResults.get(j) + "\n";
                commandResponse += "Type !stox next to see the next page\n";
            }
            i = i + 5;
        }
        else {
            for(int j = i; j < i + remainingResults; j++){
                commandResponse += "----- RESULT NO. " + (j + 1) + " -----\n";
                commandResponse += SearchResults.get(j) + "\n";
                commandResponse += "Reached end of the results\n";
            }
            i = i + remainingResults;
            subcommandStatus.clearStatus();
        }
        return commandResponse;
    }
}
