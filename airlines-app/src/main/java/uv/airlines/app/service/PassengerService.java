package uv.airlines.app.service;

import uv.airlines.app.service.dto.PassengerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Passenger}.
 */
public interface PassengerService {

    /**
     * Save a passenger.
     *
     * @param passengerDTO the entity to save.
     * @return the persisted entity.
     */
    PassengerDTO save(PassengerDTO passengerDTO);

    /**
     * Get all the passengers.
     *
     * @return the list of entities.
     */
    List<PassengerDTO> findAll();

    /**
     * Get the "id" passenger.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PassengerDTO> findOne(Long id);

    /**
     * Delete the "id" passenger.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
