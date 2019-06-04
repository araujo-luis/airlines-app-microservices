package uv.airlines.app.service;

import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing
 * {@link uv.airlines.app.domain.ReservationPassengers}.
 */
public interface ReservationPassengersService {

    /**
     * Save a reservationPassengers.
     *
     * @param reservationPassengersDTO the entity to save.
     * @return the persisted entity.
     */
    ReservationPassengersDTO save(ReservationPassengersDTO reservationPassengersDTO);

    /**
     * Get all the reservationPassengers.
     *
     * @return the list of entities.
     */
    List<ReservationPassengersDTO> findAll();

    /**
     * Get all the passengers without Seat.
     *
     * @return the list of entities.
     */

    List<ReservationPassengersDTO> getPassengersWithoutSeat(Long idFlightScheduleId, Long idAgencies);

    /**
     * Get all the get Busy seat.
     *
     * @return the list of entities.
     */
    List<ReservationPassengersDTO> getBusySeat(Long idFlightScheduleId, Long idAgencies);

    List<ReservationPassengersDTO> findByFlightPendient(LocalDateTime today, Long idAgencies);

    /**
     * Get all the Passengers who have bought tickets with priority more than a
     * parameter.
     *
     * @return the list of entities.
     */
    List<PassengersPriorityDTO> findAllPassengersWithPriority();

    /**
     * Get all the Passengers who have bought tickets with priority more than a
     * parameter.
     *
     * @return the list of entities.
     */
    Optional<ReservationPassengersDTO> findByPassengerIdAndReservationId(String passenger, Long reservation);

    /**
     * Cancel the reservation who reserved the ticket to flight
     *
     * @return boolean value.
     */
    Boolean cancelReservation(String passenger, Long reservation);

    /**
     * Pay the ticket to realize the flight
     *
     * @return boolean value.
     */
    Boolean payReservation(String passenger, Long reservation);

    /**
     * change the Seat of passenger on reservation
     * 
     * @return
     */
    Boolean changeSeat(String passenger, Long reservation, String numberSeat);

    /**
     * Get all the Passengers who have bought tickets with priority more than a
     * parameter.
     *
     * @return the list of entities.
     */
    List<MonthlyProfitsDTO> getMonthlyProfits();

    /**
     * Get Top 10 destinations profitable
     *
     * @return the list of entities.
     */
    List<ProfitFlightsDTO> getTop10ProfitsFlights();

    /**
     * Get the "id" reservationPassengers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReservationPassengersDTO> findOne(Long id);

    /**
     * Delete the "id" reservationPassengers.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
