package projekt.karol.lesniewski.mapa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByDevice(String device);
}
