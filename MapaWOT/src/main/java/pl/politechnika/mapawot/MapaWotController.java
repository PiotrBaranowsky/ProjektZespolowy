package pl.politechnika.mapawot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MapaWotController {
    @RequestMapping("/map")
    public String map(){
        return "map";
    }
}
