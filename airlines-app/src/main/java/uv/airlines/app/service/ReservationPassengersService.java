package uv.airlines.app.service;

import uv.airlines.app.service.dto.ReservationPassengersDTO;

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
