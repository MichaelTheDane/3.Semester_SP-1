package app.services;

import app.exceptions.ApiException;
// import app.utils.Utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiFetchers {

    public String fetchFromApi(String Uri){

        HttpClient httpClient = HttpClient.newHttpClient();
        String APITOKEN = System.getenv("API_TOKEN");

        try {

            // Create a request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(Uri))
                    .header("accept", "application/json")  // insert token
                    .header("Authorization", "Bearer " + APITOKEN)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send request (get weather data)
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request went well
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new ApiException(response.statusCode(), "Error in fetching");
            }
        }
        /*
        Original commented out
        try {
            String APITOKEN = System.getenv("API_TOKEN");
            String APIKEY = System.getenv("API_KEY");
            // Create a request
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(Uri))
                    .header(APITOKEN,APIKEY)  // insert token
                    .GET()
                    .build();

            // Send request (get weather data)
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request went well
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new ApiException(response.statusCode(), "Error in fetching");
            }
        }*/
        catch (Exception e){
            throw new ApiException(500, e.getMessage());
        }
    }

}