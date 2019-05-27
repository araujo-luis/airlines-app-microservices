package uv.airlines.app.repository;

import uv.airlines.app.domain.ReservationPassengers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the ReservationPassengers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationPassengersRepository extends JpaRepository<ReservationPassengers, Long> {

}
