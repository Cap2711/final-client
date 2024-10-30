package com.keyin.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.keyin.domain.Passenger;
import com.keyin.domain.City;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {
    private String serverURL;
    private HttpClient client;

    public String getServerURL() {
        return serverURL;
    }
    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }
    public HttpClient getClient() {
        if (client == null) {
            client  = HttpClient.newHttpClient();
        }
        return client;
    }

    private HttpResponse<String> httpSender(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()==200) {
            System.out.println("*****HEY HEY HEY...is someone going away today??****");
            System.out.println("***** " + response.body());
        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }
        return response;
    }

    public List<Passenger> buildPassengerListFromResponse(String response) throws JsonProcessingException {
        List<Passenger> passengers = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        passengers = mapper.readValue(response, new TypeReference<List<Passenger>>(){});
        return passengers;
    }

    public List<City> buildCityListForPassengerFromResponse(String response) throws JsonProcessingException {
        List<City> cities = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        cities = mapper.readValue(response, new TypeReference<List<City>>(){});
        return cities;
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();
        try {
            HttpResponse<String> response = httpSender(request);
            passengers = buildPassengerListFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return passengers;
    }

    public List<City> getCitiesForPassenger() {
        List<City> cities = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(serverURL)).build();
        try {
            HttpResponse<String> response = httpSender(request);
            cities = buildCityListForPassengerFromResponse(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
