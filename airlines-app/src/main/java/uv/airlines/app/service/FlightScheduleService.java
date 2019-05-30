package uv.airlines.app.service;

import uv.airlines.app.service.dto.FlightScheduleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.FlightSchedule}.
 */
public interface FlightScheduleService {

    /**
     * Save a flightSchedule.
     *
     * @param flightScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    FlightScheduleDTO save(FlightScheduleDTO flightScheduleDTO);

    /**
     * Get all the flightSchedules.
     *
     * @return the list of entities.
     */
    List<FlightScheduleDTO> findAll();

    /**
     * Get the "id" flightSchedule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FlightScheduleDTO> findOne(Long id);

    /**
     * Delete the "id" flightSchedule.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "AirportTakeOff" flightSchedule.
     *
     * @param id the id of the entity.
     */
    List<FlightScheduleDTO> findFlights(String airportTakeoff, String airportArrival, Long takeoffDate,
            Long takeoffDate2);

    /**
     * Get the "AirportTakeOff" flightSchedule.
     *
     * @param id the id of the entity.
     */
    List<FlightScheduleDTO> findOptionalFlights(String airportTakeoff, String airportArrival, Long takeoffDate,
            Long takeoffDate2);
}
