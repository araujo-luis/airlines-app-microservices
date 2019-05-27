package uv.airlines.app.service;

import uv.airlines.app.service.dto.AirportsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Airports}.
 */
public interface AirportsService {

    /**
     * Save a airports.
     *
     * @param airportsDTO the entity to save.
     * @return the persisted entity.
     */
    AirportsDTO save(AirportsDTO airportsDTO);

    /**
     * Get all the airports.
     *
     * @return the list of entities.
     */
    List<AirportsDTO> findAll();

    /**
     * Get the "id" airports.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AirportsDTO> findOne(Long id);

    /**
     * Delete the "id" airports.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
