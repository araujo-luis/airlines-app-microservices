package uv.airlines.app.repository;

import uv.airlines.app.domain.FlightSchedule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the FlightSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

}
