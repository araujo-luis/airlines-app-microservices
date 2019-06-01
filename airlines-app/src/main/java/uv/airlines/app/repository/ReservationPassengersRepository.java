package uv.airlines.app.repository;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
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
			"INNER JOIN RP.passenger P " + 
			"where RP.priority = 1 " + 
			"GROUP BY P.id " + 
			"HAVING COUNT(P) > ?1")
	public List<PassengersPriorityDTO> getPassengerPriority(long priority);

	
	//Q7
	@Query("SELECT new uv.airlines.app.service.dto.ProfitFlightsDTO(A.munipality, A.continent, A.id, SUM(RP.flightRate)) "+ 
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.reservation R " + 
			"INNER JOIN R.flightSchedule FS " +  
			"INNER JOIN FS.airportArrival A " +
			"WHERE R.reservationDate BETWEEN :start AND :end " + 
			"GROUP BY FS.airportArrival " +
			"ORDER BY SUM(RP.flightRate) DESC")
	public List<ProfitFlightsDTO> findTop10ProfitsFlights(Pageable pageable, @Param("start") Date startDate, @Param("end") Date  endDate);
	
	//Q8
	@Query("SELECT new uv.airlines.app.service.dto.MonthlyProfitsDTO(YEAR(R.reservationDate), MONTH(R.reservationDate), sum(RP.flightRate)) " + 
			"FROM ReservationPassengers RP " +
			"INNER JOIN RP.reservation R " + 
			"GROUP BY YEAR(R.reservationDate), MONTH(R.reservationDate) " +
			"ORDER BY YEAR(R.reservationDate), MONTH(R.reservationDate) DESC")
	public List<MonthlyProfitsDTO> getMonthlyProfits(Pageable pageable);

}
