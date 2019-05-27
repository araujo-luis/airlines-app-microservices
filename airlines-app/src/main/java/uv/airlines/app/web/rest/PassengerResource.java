package uv.airlines.app.web.rest;

import uv.airlines.app.service.PassengerService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.PassengerDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.Passenger}.
 */
@RestController
@RequestMapping("/api")
public class PassengerResource {

    private final Logger log = LoggerFactory.getLogger(PassengerResource.class);

    private static final String ENTITY_NAME = "testAppPassenger";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final PassengerService passengerService;

    public PassengerResource(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    /**
     * {@code POST  /passengers} : Create a new passenger.
     *
     * @param passengerDTO the passengerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new passengerDTO, or with status {@code 400 (Bad Request)}
     *         if the passenger has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/passengers")
    public ResponseEntity<PassengerDTO> createPassenger(@Valid @RequestBody PassengerDTO passengerDTO)
            throws URISyntaxException {
        log.debug("REST request to save Passenger : {}", passengerDTO);
        if (passengerDTO.getId() != null) {
            throw new BadRequestAlertException("A new passenger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PassengerDTO result = passengerService.save(passengerDTO);
        return ResponseEntity
                .created(new URI("/api/passengers/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /passengers} : Updates an existing passenger.
     *
     * @param passengerDTO the passengerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated passengerDTO, or with status {@code 400 (Bad Request)} if
     *         the passengerDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the passengerDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/passengers")
    public ResponseEntity<PassengerDTO> updatePassenger(@Valid @RequestBody PassengerDTO passengerDTO)
            throws URISyntaxException {
        log.debug("REST request to update Passenger : {}", passengerDTO);
        if (passengerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PassengerDTO result = passengerService.save(passengerDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                passengerDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /passengers} : get all the passengers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of passengers in body.
     */
    @GetMapping("/passengers")
    public List<PassengerDTO> getAllPassengers() {
        log.debug("REST request to get all Passengers");
        return passengerService.findAll();
    }

    /**
     * {@code GET  /passengers/:id} : get the "id" passenger.
     *
     * @param id the id of the passengerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the passengerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/passengers/{id}")
    public ResponseEntity<PassengerDTO> getPassenger(@PathVariable Long id) {
        log.debug("REST request to get Passenger : {}", id);
        Optional<PassengerDTO> passengerDTO = passengerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(passengerDTO);
    }

    /**
     * {@code DELETE  /passengers/:id} : delete the "id" passenger.
     *
     * @param id the id of the passengerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        log.debug("REST request to delete Passenger : {}", id);
        passengerService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
