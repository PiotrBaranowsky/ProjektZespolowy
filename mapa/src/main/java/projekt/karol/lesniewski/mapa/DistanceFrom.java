package projekt.karol.lesniewski.mapa;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DistanceFrom {

    Double lat;
    Double lon;
    Long distance;

}
