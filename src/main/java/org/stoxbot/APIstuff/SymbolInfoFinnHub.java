package org.stoxbot.APIstuff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.stoxbot.Environment;

import java.lang.reflect.Type;
import java.util.List;

public class SymbolInfoFinnHub {
    private String requestString;
    private final String APItoken;
    private APIrequest apiResponse;
    String requestURL;
    String apiResponseString;

    public SymbolInfoFinnHub(String requestString) {
        this.requestString = requestString;
        APItoken = "token=" + Environment.getFinnhubAPIKey();
    }

    public String SymbolToName(){
        requestURL = "https://finnhub.io/api/v1/search?q=" + requestString.toUpperCase() + "&" + APItoken;

        apiResponse = new APIrequest(requestURL);
        apiResponse.makeRequest();

        //Get the "result" part of the json response and convert into java class
        //ObjectMapper objectMapper = new ObjectMapper();

        apiResponseString = apiResponse.resJsonAttrToString("result");

        System.out.println(apiResponseString);

        return apiResponseString;
    }
}
