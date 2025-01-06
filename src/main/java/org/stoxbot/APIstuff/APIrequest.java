package org.stoxbot.APIstuff;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIrequest {
    String requestURL;
    HttpRequest request;
    HttpResponse<String> response = null;
    String responseString;
    String attrString;

    public APIrequest(String requestURL) {
        this.requestURL = requestURL;
    }

    public String makeRequest(){

        request = HttpRequest.newBuilder().uri(URI.create(requestURL)).GET().build();

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response.body());
        return response.body().toString();
    }

    //Return chosen JSON attribute of the HTTP request response as a string
    public String resJsonAttrToString(String attribute){
        responseString = response.body().toString();

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(responseString);
            attrString = node.get(attribute).asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return attrString;
    }
}
