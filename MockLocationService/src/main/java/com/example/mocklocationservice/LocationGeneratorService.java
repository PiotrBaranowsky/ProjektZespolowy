package com.example.mocklocationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationGeneratorService {

    @Autowired
    LocationRepository repository;

    final double MIN_LATITUDE = 50.0;
    final double MAX_LATITUDE = 54.0;
    final double MIN_LONGITUDE = 15.0;
    final double MAX_LONGITUDE = 23.0;
    public void generate() {
        List<Location> locations = repository.findByStatus(0);
        for(Location mp : locations) {
            Double lat = MIN_LATITUDE + Math.random() * (MAX_LATITUDE - MIN_LATITUDE);
            Double lon = MIN_LONGITUDE + Math.random() * (MAX_LONGITUDE - MIN_LONGITUDE);
            mp.setLat(lat);
            mp.setLon(lon);
            repository.save(mp);
        }
    }

}
