package com.example.mocklocationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LocationGeneratorService {

    @Autowired
    MapLocationRepository repository;

    final double MIN_LATITUDE = 50.0;
    final double MAX_LATITUDE = 54.0;
    final double MIN_LONGITUDE = 15.0;
    final double MAX_LONGITUDE = 23.0;
    public void generate() {
        List<MapLocation> locations = repository.findAll();
        for(MapLocation mp : locations) {
            Double lat = 50 + Math.random() * 4;
            Double lon = 15 + Math.random() * 8;
            mp.setLat(lat);
            mp.setLon(lon);
            repository.save(mp);
        }
    }

}
