package projekt.karol.lesniewski.mapa;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
public class OurRestController {

    @Autowired
    UserRepository repository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    FeignLocationService feignService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping("/location")
    public List<MapLocation> getLocation() {
        System.out.println("111");
        feignService.getLocations();
        List<User> list = repository.findAll();
        List<MapLocation> mapLocations = new ArrayList<>();
        for(User user : list) {
            Location location = locationRepository.findByDevice(user.getDevice()).get(0);
            MapLocation mp = new MapLocation(List.of(
                    Objects.isNull(location.getLat()) ? -1 : location.getLat(),
                    Objects.isNull(location.getLon()) ? -1 : location.getLon()),
                    user.firstName, user.lastName,
                    user.phoneNumber,
                    user.rank, user.brigade);
            mapLocations.add(mp);
        }
        return mapLocations;
    }

    @PostMapping("/location")
    public String updateLocation(@RequestBody DeviceLocation deviceLocation) {
        System.out.println("location-service");
        System.out.println(deviceLocation.getDevice());
        List<User> users = repository.findByDevice(deviceLocation.getDevice());
        if(users.isEmpty())
            return "Invalid";
        User user = users.get(0);
        String device = user.getDevice();
        Location location = locationRepository.findByDevice(device).get(0);
        location.updateLocation(deviceLocation.getLat(), deviceLocation.getLon());
        locationRepository.save(location);
        return "Updated";
    }

    @PostMapping("/device")
    public String updateLocation(@RequestBody DeviceRegister deviceRegister) {
        System.out.println("device-service");
        List<Location> locations = locationRepository.findByDevice(deviceRegister.getDevice());
        if(locations.isEmpty())
            return "NoDevice";
        List<User> users = repository.findByDevice(deviceRegister.getDevice());
        if(users.isEmpty())
            return "NoUser";
        Location location = locations.get(0);
        User user = users.get(0);
        String phoneNumber = user.getPhoneNumber();
        if(phoneNumber.equals(deviceRegister.getPhoneNumber()))
            location.setVerified(1);
        else
            return "PhoneNumberError";
        return String.valueOf(user.getBrigade());
    }

    @PostMapping("/newUser")
    public String sendLocation(@RequestBody InputUser inputUser) {
        //String encodedPassword = passwordEncoder.encode(inputUser.getPassword());
        String device = "";
        int count = 1;
        while (count != 0) {
            device = randomDeviceID();
            List<User> users = repository.findByDevice(device);
            if(users.isEmpty())
                count = 0;
        }
        User user = new User(inputUser.getEmail(),
                inputUser.getFirstName(), inputUser.getLastName(),
                inputUser.getPhoneNumber(), inputUser.getRank(), inputUser.getBrigade(),
                inputUser.getPassword(), device
        );
        Location location = new Location(device);
        locationRepository.save(location);
        repository.save(user);
        return user.getDevice();
    }

    private static String randomDeviceID() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
//
//    @RequestMapping("/user")
//    public User getUserDetailsAfterLogin(Authentication authentication) {
//        List<User> users = repository.findByEmail(authentication.getName());
//        if (!users.isEmpty()) {
//            return users.get(0);
//        } else {
//            return null;
//        }
//    }


}



