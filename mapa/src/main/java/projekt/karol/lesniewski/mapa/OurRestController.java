package projekt.karol.lesniewski.mapa;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class OurRestController {

    private final MapLocationRepository repository;

    private final DistanceService distanceService;

    @GetMapping("/all")
    public List<MapLocation> getLocation() {
        return repository.findAll();
    }

    @PostMapping("/dist")
    public List<MapLocationDistance> getLocation(@RequestBody DistanceFrom base) {
        return distanceService.getLocations(base);
    }

    @DeleteMapping("/{id}")
    public MapLocation deleteLocation(@PathVariable Long id) {
        Optional<MapLocation> location = repository.findById(id);
        if(location.isEmpty())
            throw new RuntimeException("No user with that ID is available!");
        repository.deleteById(id);
        return location.get();
    }



    @PostMapping
    public void sendLocation(@RequestBody MapLocation location) {
        System.out.println(location);
        repository.save(location);
    }

}



