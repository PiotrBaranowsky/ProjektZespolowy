package projekt.karol.lesniewski.mapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*")
public class OurRestController {

    @Autowired
    MapLocationRepository repository;

    @GetMapping("/all")
    public List<MapLocation> getLocation() {
        List<MapLocation> list = repository.findAll();
        return list;
    }

    @DeleteMapping("/{id}")
    public MapLocation deleteLocation(@PathVariable Long id) {
        Optional<MapLocation> location = repository.findById(id);
        if(location.isEmpty())
            throw new RuntimeException("No user with that ID is available!");
        repository.deleteById(id);
        return location.get();
    }git add .

    @PostMapping
    public void sendLocation(@RequestBody MapLocation location) {
        System.out.println(location);
        repository.save(location);
    }

}



