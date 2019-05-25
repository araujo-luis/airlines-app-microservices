package uv.airlines.app.web.rest;

import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.ReservationsDTO;

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
 * REST controller for managing {@link uv.airlines.app.domain.Reservations}.
 */
@RestController
@RequestMapping("/api")
public class ReservationsResource {

    private final Logger log = LoggerFactory.getLogger(ReservationsResource.class);

    private static final String ENTITY_NAME = "testAppReservations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReservationsService reservationsService;

    public ReservationsResource(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    /**
     * {@code POST  /reservations} : Create a new reservations.
     *
     * @param reservationsDTO the reservationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new reservationsDTO, or with status
     *         {@code 400 (Bad Request)} if the reservations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reservations")
    public ResponseEntity<ReservationsDTO> createReservations(@RequestBody ReservationsDTO reservationsDTO)
            throws URISyntaxException {
        log.debug("REST request to save Reservations : {}", reservationsDTO);
        if (reservationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationsDTO result = reservationsService.save(reservationsDTO);
        return ResponseEntity
                .created(new URI("/api/reservations/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /reservations} : Updates an existing reservations.
     *
     * @param reservationsDTO the reservationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated reservationsDTO, or with status {@code 400 (Bad Request)}
     *         if the reservationsDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the reservationsDTO couldn't
     *         be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reservations")
    public ResponseEntity<ReservationsDTO> updateReservations(@RequestBody ReservationsDTO reservationsDTO)
            throws URISyntaxException {
        log.debug("REST request to update Reservations : {}", reservationsDTO);
        if (reservationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationsDTO result = reservationsService.save(reservationsDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                reservationsDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /reservations} : get all the reservations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of reservations in body.
     */
    @GetMapping("/reservations")
    public List<ReservationsDTO> getAllReservations() {
        log.debug("REST request to get all Reservations");
        return reservationsService.findAll();
    }

    /**
     * {@code GET  /reservations/:id} : get the "id" reservations.
     *
     * @param id the id of the reservationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reservationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationsDTO> getReservations(@PathVariable Long id) {
        log.debug("REST request to get Reservations : {}", id);
        Optional<ReservationsDTO> reservationsDTO = reservationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationsDTO);
    }

    /**
     * {@code DELETE  /reservations/:id} : delete the "id" reservations.
     *
     * @param id the id of the reservationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservations(@PathVariable Long id) {
        log.debug("REST request to delete Reservations : {}", id);
        reservationsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
