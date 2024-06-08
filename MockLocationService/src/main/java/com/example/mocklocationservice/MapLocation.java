package com.example.mocklocationservice;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "maplocations")
public class MapLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Double lat;
    Double lon;
    String firstName;

    String lastName;

    String phoneNumber;

    String rank;

    public MapLocation(List<Double> loc, String firstName, String lastName, String phoneNumber, String rank) {
        this.lat = loc.get(0);
        this.lon = loc.get(1);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
    }

    public MapLocation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "MapLocation{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
