package com.example.mocklocationservice;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String device;

    @Nullable
    private Double lat;

    @Nullable
    private Double lon;

    private Integer status;

    public Location(String device) {
        this.device = device;
        this.status = 1;
    }

    //0 - mock
    //1 - urządzenie włączone do użytku
    //2 - urządzenie wyłączone z użytku

    public Location() {
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void updateLocation(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
