package uv.airlines.app.repository;

import uv.airlines.app.domain.FlightSchedule;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the FlightSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
	
	public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThan(
			String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThanOrderByFlightRateAsc(
			String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);

	
}
