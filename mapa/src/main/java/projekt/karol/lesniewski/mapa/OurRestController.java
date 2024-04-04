package projekt.karol.lesniewski.mapa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OurRestController {

    @GetMapping("/location")
    public MapLocation getLocation() {
        return new MapLocation(List.of(51.5, -0.07), "Andrzej", "Janowski", "123654832", "szeregowy");
    }

    @PostMapping("/location")
    public void sendLocation(@RequestBody MapLocation location) {
        System.out.println(location);
    }

}



