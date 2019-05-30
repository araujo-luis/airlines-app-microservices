package uv.airlines.app.web.rest;

import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AircraftsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.Aircrafts}.
 */
@RestController
@RequestMapping("/api")
public class AircraftsResource {

    private final Logger log = LoggerFactory.getLogger(AircraftsResource.class);

    private static final String ENTITY_NAME = "testAppAircrafts";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final AircraftsService aircraftsService;

    public AircraftsResource(AircraftsService aircraftsService) {
        this.aircraftsService = aircraftsService;
    }

    /**
     * {@code POST  /aircrafts} : Create a new aircrafts.
     *
     * @param aircraftsDTO the aircraftsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new aircraftsDTO, or with status {@code 400 (Bad Request)}
     *         if the aircrafts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aircrafts")
    public ResponseEntity<AircraftsDTO> createAircrafts(@Valid @RequestBody AircraftsDTO aircraftsDTO)
            throws URISyntaxException {
        log.debug("REST request to save Aircrafts : {}", aircraftsDTO);
        if (aircraftsDTO.getId() != null) {
            throw new BadRequestAlertException("A new aircrafts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AircraftsDTO result = aircraftsService.save(aircraftsDTO);
        return ResponseEntity
                .created(new URI("/api/aircrafts/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /aircrafts} : Updates an existing aircrafts.
     *
     * @param aircraftsDTO the aircraftsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated aircraftsDTO, or with status {@code 400 (Bad Request)} if
     *         the aircraftsDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the aircraftsDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aircrafts")
    public ResponseEntity<AircraftsDTO> updateAircrafts(@Valid @RequestBody AircraftsDTO aircraftsDTO)
            throws URISyntaxException {
        log.debug("REST request to update Aircrafts : {}", aircraftsDTO);
        if (aircraftsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AircraftsDTO result = aircraftsService.save(aircraftsDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                aircraftsDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /aircrafts} : get all the aircrafts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of aircrafts in body.
     */
    @GetMapping("/aircrafts")
    public List<AircraftsDTO> getAllAircrafts() {
        log.debug("REST request to get all Aircrafts");
        return aircraftsService.findAll();
    }

    /**
     * {@code GET  /aircrafts/:id} : get the "id" aircrafts.
     *
     * @param id the id of the aircraftsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the aircraftsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aircrafts/{id}")
    public ResponseEntity<AircraftsDTO> getAircrafts(@PathVariable Long id) {
        log.debug("REST request to get Aircrafts : {}", id);
        Optional<AircraftsDTO> aircraftsDTO = aircraftsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aircraftsDTO);
    }

    /**
     * {@code DELETE  /aircrafts/:id} : delete the "id" aircrafts.
     *
     * @param id the id of the aircraftsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aircrafts/{id}")
    public ResponseEntity<Void> deleteAircrafts(@PathVariable Long id) {
        log.debug("REST request to delete Aircrafts : {}", id);
        aircraftsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
