package com.keyin.domain;

import javax.persistence.*;
import java.util.List;


public class Airport {


    private long id;
    private String name;
    private String airportCode;

    //relationships

    //@ManyToMany
    private List<Aircraft> aircraft;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

   /* public List<Aircraft> getAircraft() {
        return aircraft;
    }

    public void setAircraft(List<Aircraft> aircraft) {
        this.aircraft = aircraft;
    }*/
}


