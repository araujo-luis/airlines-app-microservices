package uv.airlines.app.web.rest;

import uv.airlines.app.service.AirportsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AirportsDTO;

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
 * REST controller for managing {@link uv.airlines.app.domain.Airports}.
 */
@RestController
@RequestMapping("/api")
public class AirportsResource {

    private final Logger log = LoggerFactory.getLogger(AirportsResource.class);

    private static final String ENTITY_NAME = "testAppAirports";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final AirportsService airportsService;

    public AirportsResource(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    /**
     * {@code POST  /airports} : Create a new airports.
     *
     * @param airportsDTO the airportsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new airportsDTO, or with status {@code 400 (Bad Request)} if
     *         the airports has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/airports")
    public ResponseEntity<AirportsDTO> createAirports(@Valid @RequestBody AirportsDTO airportsDTO)
            throws URISyntaxException {
        log.debug("REST request to save Airports : {}", airportsDTO);
        if (airportsDTO.getId() != null) {
            throw new BadRequestAlertException("A new airports cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AirportsDTO result = airportsService.save(airportsDTO);
        return ResponseEntity
                .created(new URI("/api/airports/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /airports} : Updates an existing airports.
     *
     * @param airportsDTO the airportsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated airportsDTO, or with status {@code 400 (Bad Request)} if
     *         the airportsDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the airportsDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/airports")
    public ResponseEntity<AirportsDTO> updateAirports(@Valid @RequestBody AirportsDTO airportsDTO)
            throws URISyntaxException {
        log.debug("REST request to update Airports : {}", airportsDTO);
        if (airportsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AirportsDTO result = airportsService.save(airportsDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, airportsDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /airports} : get all the airports.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of airports in body.
     */
    @GetMapping("/airports")
    public List<AirportsDTO> getAllAirports() {
        log.debug("REST request to get all Airports");
        return airportsService.findAll();
    }

    /**
     * {@code GET  /airports/:id} : get the "id" airports.
     *
     * @param id the id of the airportsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the airportsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/airports/{id}")
    public ResponseEntity<AirportsDTO> getAirports(@PathVariable Long id) {
        log.debug("REST request to get Airports : {}", id);
        Optional<AirportsDTO> airportsDTO = airportsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(airportsDTO);
    }

    /**
     * {@code DELETE  /airports/:id} : delete the "id" airports.
     *
     * @param id the id of the airportsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/airports/{id}")
    public ResponseEntity<Void> deleteAirports(@PathVariable Long id) {
        log.debug("REST request to delete Airports : {}", id);
        airportsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
