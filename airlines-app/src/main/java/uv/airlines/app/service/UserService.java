package uv.airlines.app.service;

import uv.airlines.app.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.User}.
 */
public interface UserService {

    /**
     * Save a User.
     *
     * @param UserDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO save(UserDTO UserDTO);

    /**
     * Get all the Users.
     *
     * @return the list of entities.
     */
    List<UserDTO> findAll();

    /**
     * Get the "id" User.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDTO> findOne(Long id);

    /**
     * Delete the "id" User.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
