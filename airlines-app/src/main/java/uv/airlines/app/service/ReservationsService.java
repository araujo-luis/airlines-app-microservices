package uv.airlines.app.service;

import uv.airlines.app.service.dto.ReservationsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Reservations}.
 */
public interface ReservationsService {

    /**
     * Save a reservations.
     *
     * @param reservationsDTO the entity to save.
     * @return the persisted entity.
     */
    ReservationsDTO save(ReservationsDTO reservationsDTO);

    /**
     * Get all the reservations.
     *
     * @return the list of entities.
     */
    List<ReservationsDTO> findAll();

    /**
     * Get the "id" reservations.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReservationsDTO> findOne(Long id);

    /**
     * Delete the "id" reservations.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
