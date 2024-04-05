package pl.politechnika.mapawot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapaWotController {
    @RequestMapping
    public String helloWorld(){
        return "Hello world";
    }

    @RequestMapping("/goodbye")
    public String goodbye(){
        return "Goodbye";
    }
}
