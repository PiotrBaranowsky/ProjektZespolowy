package projekt.karol.lesniewski.mapa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistanceService {

    private final MapLocationRepository repository;
    public final double EARTH_RADIUS = 6371;

    List<MapLocationDistance> getLocations(DistanceFrom base) {
        List<MapLocation> locations = repository.findAll();
        List<MapLocationDistance> results = new ArrayList<>();
        for(MapLocation ml : locations) {
            double calculatedDistance = calculateDistance(ml.lat, ml.lon, base.lat, base.lon);
            if(calculatedDistance < base.distance) {
                results.add(new MapLocationDistance(ml.lat, ml.lon, ml.firstName, ml.lastName, ml.phoneNumber, ml.phoneNumber, calculatedDistance));
            }
        }
        return results;
    }

    private double calculateDistance(double lat, double lon, double baseLat, double baseLon) {
        double radLat = Math.toRadians(lat);
        double radLatBase = Math.toRadians(baseLat);
        double radLon = Math.toRadians(lon);
        double radLonBase = Math.toRadians(baseLon);
        double x = (radLonBase - radLon) * Math.cos((radLat + radLatBase) / 2);
        double y = radLatBase - radLat;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * EARTH_RADIUS;
    }

}
