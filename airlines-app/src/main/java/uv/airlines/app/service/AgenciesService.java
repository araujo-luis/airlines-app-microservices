package uv.airlines.app.service;

import uv.airlines.app.service.dto.AgenciesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Agencies}.
 */
public interface AgenciesService {

    /**
     * Save a agencies.
     *
     * @param agenciesDTO the entity to save.
     * @return the persisted entity.
     */
    AgenciesDTO save(AgenciesDTO agenciesDTO);

    /**
     * Get all the agencies.
     *
     * @return the list of entities.
     */
    List<AgenciesDTO> findAll();

    /**
     * Get the "id" agencies.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AgenciesDTO> findOne(Long id);

    /**
     * Delete the "id" agencies.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
