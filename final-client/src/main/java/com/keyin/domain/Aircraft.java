package com.keyin.domain;


import java.util.List;


public class Aircraft {


    private long id;
    private String type;
    private String airLineName;
    private int numberOfPassengers;




    private List<Passenger> passengers;

    // getters
    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAirLineName() {
        return airLineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    //setters

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAirLineName(String airLineName) {
        this.airLineName = airLineName;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }


}


