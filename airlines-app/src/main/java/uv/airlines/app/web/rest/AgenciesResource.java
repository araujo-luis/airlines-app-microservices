package uv.airlines.app.web.rest;

import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AgenciesDTO;

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
 * REST controller for managing {@link uv.airlines.app.domain.Agencies}.
 */
@RestController
@RequestMapping("/api")
public class AgenciesResource {

    private final Logger log = LoggerFactory.getLogger(AgenciesResource.class);

    private static final String ENTITY_NAME = "testAppAgencies";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final AgenciesService agenciesService;

    public AgenciesResource(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }

    /**
     * {@code POST  /agencies} : Create a new agencies.
     *
     * @param agenciesDTO the agenciesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new agenciesDTO, or with status {@code 400 (Bad Request)} if
     *         the agencies has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agencies")
    public ResponseEntity<AgenciesDTO> createAgencies(@Valid @RequestBody AgenciesDTO agenciesDTO)
            throws URISyntaxException {
        log.debug("REST request to save Agencies : {}", agenciesDTO);
        if (agenciesDTO.getId() != null) {
            throw new BadRequestAlertException("A new agencies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgenciesDTO result = agenciesService.save(agenciesDTO);
        return ResponseEntity
                .created(new URI("/api/agencies/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /agencies} : Updates an existing agencies.
     *
     * @param agenciesDTO the agenciesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated agenciesDTO, or with status {@code 400 (Bad Request)} if
     *         the agenciesDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the agenciesDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agencies")
    public ResponseEntity<AgenciesDTO> updateAgencies(@Valid @RequestBody AgenciesDTO agenciesDTO)
            throws URISyntaxException {
        log.debug("REST request to update Agencies : {}", agenciesDTO);
        if (agenciesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgenciesDTO result = agenciesService.save(agenciesDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, agenciesDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /agencies} : get all the agencies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of agencies in body.
     */
    @GetMapping("/agencies")
    public List<AgenciesDTO> getAllAgencies() {
        log.debug("REST request to get all Agencies");
        return agenciesService.findAll();
    }

    /**
     * {@code GET  /agencies/:id} : get the "id" agencies.
     *
     * @param id the id of the agenciesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the agenciesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agencies/{id}")
    public ResponseEntity<AgenciesDTO> getAgencies(@PathVariable Long id) {
        log.debug("REST request to get Agencies : {}", id);
        Optional<AgenciesDTO> agenciesDTO = agenciesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agenciesDTO);
    }

    /**
     * {@code DELETE  /agencies/:id} : delete the "id" agencies.
     *
     * @param id the id of the agenciesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agencies/{id}")
    public ResponseEntity<Void> deleteAgencies(@PathVariable Long id) {
        log.debug("REST request to delete Agencies : {}", id);
        agenciesService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
