package uv.airlines.app.repository;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	public Optional<ReservationPassengers> findByPassengerIdAndReservationId(String passenger, Long reservation);

	@Query("select rp from ReservationPassengers rp " + " inner join rp.reservation r  "
			+ " inner join r.flightSchedule fs "
			+ " where fs.id = :flightScheduleId and r.agencies.id = :agencyId and rp.seatNumber = ''")
	public List<ReservationPassengers> getPassengersWithoutSeat(@Param("flightScheduleId") Long idFlightScheduleId,
			@Param("agencyId") Long idAgencies);

	@Query("select rp  from ReservationPassengers rp " + " inner join rp.reservation r "
			+ " inner join r.flightSchedule fs "
			+ " where fs.id = :flightScheduleId and r.agencies.id = :agencyId and rp.seatNumber != '' ")
	public List<ReservationPassengers> getBusySeat(@Param("flightScheduleId") Long idFlightScheduleId,
			@Param("agencyId") Long idAgencies);

	// Q3-1
	@Query("Select rp from ReservationPassengers rp inner join rp.reservation r inner join r.flightSchedule fs where fs.arrivalDate > :today and r.agencies.id = :agency ")
	public List<ReservationPassengers> findByFlightPendient(@Param("today") LocalDateTime today,
			@Param("agency") Long idAgencies);

	// Q3-2
	@Query("Select rp from ReservationPassengers rp inner join rp.reservation r inner join r.flightSchedule fs where fs.arrivalDate < :today and r.agencies.id = :agency ")
	public List<ReservationPassengers> findByFlightDone(@Param("today") LocalDateTime today,
			@Param("agency") Long idAgencies);

	// Q6
	@Query("SELECT new uv.airlines.app.service.dto.PassengersPriorityDTO(P.id, P.name, P.lastname,  COUNT(P)) "
			+ "FROM ReservationPassengers RP " + "INNER JOIN RP.passenger P " + "where RP.priority = 1 "
			+ "GROUP BY P.id " + "HAVING COUNT(P) > ?1")
	public List<PassengersPriorityDTO> getPassengerPriority(long priority);

	// Q7
	@Query("SELECT new uv.airlines.app.service.dto.ProfitFlightsDTO(A.munipality, A.continent, A.id, SUM(RP.flightRate)) "
			+ "FROM ReservationPassengers RP " + "INNER JOIN RP.reservation R " + "INNER JOIN R.flightSchedule FS "
			+ "INNER JOIN FS.airportArrival A " + "WHERE R.reservationDate BETWEEN :start AND :end "
			+ "GROUP BY FS.airportArrival " + "ORDER BY SUM(RP.flightRate) DESC")
	public List<ProfitFlightsDTO> findTop10ProfitsFlights(Pageable pageable, @Param("start") Date startDate,
			@Param("end") Date endDate);

	// Q8
	@Query("SELECT new uv.airlines.app.service.dto.MonthlyProfitsDTO(YEAR(R.reservationDate), MONTH(R.reservationDate), sum(RP.flightRate)) "
			+ "FROM ReservationPassengers RP " + "INNER JOIN RP.reservation R "
			+ "GROUP BY YEAR(R.reservationDate), MONTH(R.reservationDate) "
			+ "ORDER BY YEAR(R.reservationDate), MONTH(R.reservationDate) DESC")
	public List<MonthlyProfitsDTO> getMonthlyProfits(Pageable pageable);

}
