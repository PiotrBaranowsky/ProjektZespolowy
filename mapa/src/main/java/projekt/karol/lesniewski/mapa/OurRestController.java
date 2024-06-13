package projekt.karol.lesniewski.mapa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
public class OurRestController {

    @Autowired
    UserRepository repository;

    @Autowired
    FeignLocationService feignService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/location")
    public List<MapLocation> getLocation() {
        System.out.println("111");
        feignService.getLocations();
        List<User> list = repository.findAll();
        return list.stream().map(user -> new MapLocation(List.of(
                Objects.isNull(user.lat) ? -1 : user.lat,
                Objects.isNull(user.lon) ? -1 : user.lon),
                user.firstName, user.lastName,
                user.phoneNumber,
                user.rank, user.brigade)).toList();
    }

    @PostMapping("/newUser")
    public void sendLocation(@RequestBody InputUser inputUser) {
        String encodedPassword = passwordEncoder.encode(inputUser.getPassword());
        User user = new User(inputUser.getEmail(),
                inputUser.getFirstName(), inputUser.getLastName(),
                inputUser.getPhoneNumber(), inputUser.getRank(), inputUser.getBrigade(),
                encodedPassword, "ADMIN"
        );
        repository.save(user); //todo
    }

    @RequestMapping("/user")
    public User getUserDetailsAfterLogin(Authentication authentication) {
        List<User> users = repository.findByEmail(authentication.getName());
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }

    }


}



