package com.example.myapplicationtest1;

public class DeviceLocation {

        private String device;

        private Double lat;

        private Double lon;

    public DeviceLocation(String device, Double lat, Double lon) {
        this.device = device;
        this.lat = lat;
        this.lon = lon;
    }

    public DeviceLocation() {
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
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

}
