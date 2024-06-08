package projekt.karol.lesniewski.mapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
public class OurRestController {

    @Autowired
    MapLocationRepository repository;

    @Autowired
    FeignLocationService feignService;

    @GetMapping("/location")
    public List<MapLocation> getLocation() {
        feignService.getLocations();
        List<MapLocation> list = repository.findAll();
        return list;
    }

    @PostMapping("/location")
    public void sendLocation(@RequestBody InputLocation location) {
        MapLocation ml = new MapLocation(location.getFirstName(), location.getLastName(), location.getPhoneNumber(), location.getRank());
        repository.save(ml); //todo
    }

}



