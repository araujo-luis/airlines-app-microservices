package uv.airlines.app.repository;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the ReservationPassengers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationPassengersRepository extends JpaRepository<ReservationPassengers, Long> {
	//Q6
	@Query("SELECT new uv.airlines.app.service.dto.PassengersPriorityDTO(P.id, P.name, P.lastname,  COUNT(P)) "+ 
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.passDni P " + 
			"where RP.priority = 1 " + 
			"GROUP BY P.id " + 
			"HAVING COUNT(P) > ?1")
	public List<PassengersPriorityDTO> getPassengerPriority(long priority);

	
	//Q7
	@Query("SELECT new uv.airlines.app.service.dto.ProfitFlightsDTO(A.munipality, A.continent, A.id, SUM(RP.flightRate)) "+ 
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.reservationId R " + 
			"INNER JOIN R.flightScheduleId FS " +  
			"INNER JOIN FS.airportArrival A " + 
			"GROUP BY FS.airportArrival")
	public List<ProfitFlightsDTO> getTop10ProfitsFlights();
}
