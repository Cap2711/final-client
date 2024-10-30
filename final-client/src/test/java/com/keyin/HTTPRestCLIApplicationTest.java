package com.keyin;

import com.keyin.domain.Passenger;
import com.keyin.domain.City;
import com.keyin.http.client.RESTClient;
import com.keyin.http.cli.HTTPRestCLIApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HTTPRestCLIApplicationTest {

    private HTTPRestCLIApplication cliApp;
    private RESTClient mockRestClient;

    @BeforeEach
    public void setUp() {
        cliApp = new HTTPRestCLIApplication();
        mockRestClient = Mockito.mock(RESTClient.class);


        cliApp.setRestClient(mockRestClient);
    }

    @Test
    public void testGeneratePassengerReport() {

        Passenger passenger1 = new Passenger();
        passenger1.setId(1L);
        passenger1.setFirstName("John");
        passenger1.setLastName("Doe");

        Passenger passenger2 = new Passenger();
        passenger2.setId(2L);
        passenger2.setFirstName("Jane");
        passenger2.setLastName("Smith");

        List<Passenger> passengers = Arrays.asList(passenger1, passenger2);
        when(mockRestClient.getAllPassengers()).thenReturn(passengers);

        String report = cliApp.generatePassengerReport();
        String expectedReport = "John Doe - 1,Jane Smith - 2";
        assertEquals(expectedReport, report);


        verify(mockRestClient, times(1)).getAllPassengers();
    }

    @Test
    public void testGenerateListOfCitiesForSpecificPassenger() {

        City city1 = new City();
        city1.setName("New York");
        city1.setState("NY");

        City city2 = new City();
        city2.setName("Los Angeles");
        city2.setState("CA");

        List<City> cities = Arrays.asList(city1, city2);

        when(mockRestClient.getCitiesForPassenger()).thenReturn(cities);
        String report = cliApp.generateListOfCitiesForSpecificPassenger();

        String expectedReport = "New York - NY,Los Angeles - CA";
        assertEquals(expectedReport, report);

        verify(mockRestClient, times(1)).getCitiesForPassenger();
    }

    @Test
    public void testNoPassengers() {

        when(mockRestClient.getAllPassengers()).thenReturn(List.of());

        String report = cliApp.generatePassengerReport();
        assertEquals("", report);
        verify(mockRestClient, times(1)).getAllPassengers();
    }
}
