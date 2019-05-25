package uv.airlines.app.repository;

import uv.airlines.app.domain.Airports;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Airports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {

}
