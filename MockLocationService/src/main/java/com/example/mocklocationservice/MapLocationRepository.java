package com.example.mocklocationservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MapLocationRepository extends JpaRepository<MapLocation, Long> {
}
