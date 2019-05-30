package uv.airlines.app.web.rest;

import uv.airlines.app.service.UserService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.UserDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.User}.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "testAppUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserService UserService;

    public UserResource(UserService UserService) {
        this.UserService = UserService;
    }

    /**
     * {@code POST  /Users} : Create a new User.
     *
     * @param UserDTO the UserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new UserDTO, or with status {@code 400 (Bad Request)} if the
     *         User has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/Users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO UserDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", UserDTO);
        if (UserDTO.getId() != null) {
            throw new BadRequestAlertException("A new User cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserDTO result = UserService.save(UserDTO);
        return ResponseEntity
                .created(new URI("/api/Users/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /Users} : Updates an existing User.
     *
     * @param UserDTO the UserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated UserDTO, or with status {@code 400 (Bad Request)} if the
     *         UserDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the UserDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/Users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO UserDTO) throws URISyntaxException {
        log.debug("REST request to update User : {}", UserDTO);
        if (UserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserDTO result = UserService.save(UserDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, UserDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /Users} : get all the Users.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of Users in body.
     */
    @GetMapping("/Users")
    public List<UserDTO> getAllUsers() {
        log.debug("REST request to get all Users");
        return UserService.findAll();
    }

    /**
     * {@code GET  /Users/:id} : get the "id" User.
     *
     * @param id the id of the UserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the UserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/Users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<UserDTO> UserDTO = UserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(UserDTO);
    }

    /**
     * {@code DELETE  /Users/:id} : delete the "id" User.
     *
     * @param id the id of the UserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/Users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        UserService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
