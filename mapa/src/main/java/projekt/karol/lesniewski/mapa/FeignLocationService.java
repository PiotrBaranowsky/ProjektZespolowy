package projekt.karol.lesniewski.mapa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@EnableFeignClients(basePackages = {"project.karol.lesniewski.mapa"})
@FeignClient("MOCK-SERVICE")
public interface FeignLocationService {

    @GetMapping("/location")
    public void getLocations();

}
