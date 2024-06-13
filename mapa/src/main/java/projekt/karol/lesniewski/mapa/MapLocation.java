package projekt.karol.lesniewski.mapa;

import jakarta.annotation.Nullable;

import java.util.List;

public class MapLocation {
    @Nullable
    Double lat;

    @Nullable
    Double lon;
    String firstName;

    String lastName;

    String phoneNumber;

    String rank;

    Integer brigade;


    public MapLocation(List<Double> loc, String firstName, String lastName, String phoneNumber, String rank, Integer brigade) {
        this.lat = loc.get(0);
        this.lon = loc.get(1);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.brigade = brigade;
    }

    public MapLocation(String firstName, String lastName, String phoneNumber, String rank, Integer brigade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
    }

    public MapLocation() {

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

    public Integer getBrigade() {
        return brigade;
    }

    public void setBrigade(Integer brigade) {
        this.brigade = brigade;
    }

    @Override
    public String toString() {
        return "MapLocation{" +
                ", lat=" + lat +
                ", lon=" + lon +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rank='" + rank + '\'' +
                ", brigade='" + brigade + '\'' +
                '}';
    }
}
