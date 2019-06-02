package uv.airlines.app.repository;

import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.service.dto.FlightScheduleDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the FlightSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
	
	//public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThanAndAircraft_seatsTakenLessThanAircraft_capacity(
			//String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	
	//Q1
	@Query("SELECT FS FROM FlightSchedule FS " + 
			"INNER JOIN FS.airportTakeoff A " +  
			"INNER JOIN FS.aircraft AIR " +  
			"WHERE FS.takeoffDate >= :takeoffDate and  FS.takeoffDate < :takeoffDate2 AND FS.airportTakeoff.id=:airportTakeoff AND FS.airportArrival.id=:airportArrival " + 
			"AND (AIR.capacity - AIR.seatsTaken) >= :passengers " +
			"ORDER BY FS.flightRate")
	public List<FlightSchedule> findFlightsAvailable( @Param("airportTakeoff") String airportTakeoff, 
														@Param("airportArrival") String airportArrival, 
														@Param("takeoffDate") LocalDateTime takeoffDate,
														@Param("takeoffDate2") LocalDateTime takeoffDate2, 
														@Param("passengers") Integer passengers);
	
	public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThan(
			String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThanOrderByFlightRateAsc(
			String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	

	
}
