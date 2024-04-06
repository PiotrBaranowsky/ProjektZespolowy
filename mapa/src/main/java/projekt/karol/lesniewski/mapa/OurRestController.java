package projekt.karol.lesniewski.mapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OurRestController {

    @Autowired
    MapLocationRepository repository;

    @GetMapping("/location")
    public List<MapLocation> getLocation() {
        List<MapLocation> list = repository.findAll();
        return list;
    }

    @PostMapping("/location")
    public void sendLocation(@RequestBody MapLocation location) {
        System.out.println(location);
        MapLocation mapLocation = new MapLocation(List.of(51.5, -0.07), "Andrzej", "Janowski", "123654832", "szeregowy");        repository.save(mapLocation);
    }

}



