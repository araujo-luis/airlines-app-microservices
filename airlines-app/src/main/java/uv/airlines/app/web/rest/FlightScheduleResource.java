package uv.airlines.app.web.rest;

import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.FlightScheduleDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.FlightSchedule}.
 */
@RestController
@RequestMapping("/api")
public class FlightScheduleResource {

    private final Logger log = LoggerFactory.getLogger(FlightScheduleResource.class);

    private static final String ENTITY_NAME = "testAppFlightSchedule";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleResource(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    /**
     * {@code POST  /flight-schedules} : Create a new flightSchedule.
     *
     * @param flightScheduleDTO the flightScheduleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new flightScheduleDTO, or with status
     *         {@code 400 (Bad Request)} if the flightSchedule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flight-schedules")
    public ResponseEntity<FlightScheduleDTO> createFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO)
            throws URISyntaxException {
        log.debug("REST request to save FlightSchedule : {}", flightScheduleDTO);
        if (flightScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new flightSchedule cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        FlightScheduleDTO result = flightScheduleService.save(flightScheduleDTO);
        return ResponseEntity
                .created(new URI("/api/flight-schedules/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /flight-schedules} : Updates an existing flightSchedule.
     *
     * @param flightScheduleDTO the flightScheduleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated flightScheduleDTO, or with status
     *         {@code 400 (Bad Request)} if the flightScheduleDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         flightScheduleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flight-schedules")
    public ResponseEntity<FlightScheduleDTO> updateFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO)
            throws URISyntaxException {
        log.debug("REST request to update FlightSchedule : {}", flightScheduleDTO);
        if (flightScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlightScheduleDTO result = flightScheduleService.save(flightScheduleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                flightScheduleDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /flight-schedules} : get all the flightSchedules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of flightSchedules in body.
     */
    @GetMapping("/flight-schedules")
    public List<FlightScheduleDTO> getFlights(@RequestParam Map<String,String> allParams) {
        return flightScheduleService.findFlights(allParams);
    }

    /**
     * {@code GET  /flight-schedules} : get all the flightSchedules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of flightSchedules in body.
     */
    @GetMapping("/flight-schedules/optional/{takeoffDate}/{takeoffAirport}/{arrivalAirport}")
    public List<FlightScheduleDTO> getOptionaFlights(@PathVariable Long takeoffDate,
            @PathVariable String takeoffAirport, @PathVariable String arrivalAirport) {

        return flightScheduleService.findOptionalFlights(takeoffAirport, arrivalAirport, takeoffDate, takeoffDate);
    }

    /**
     * {@code GET  /flight-schedules/:id} : get the "id" flightSchedule.
     *
     * @param id the id of the flightScheduleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the flightScheduleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flight-schedules/{id}")
    public ResponseEntity<FlightScheduleDTO> getFlightSchedule(@PathVariable Long id) {
        log.debug("REST request to get FlightSchedule : {}", id);
        Optional<FlightScheduleDTO> flightScheduleDTO = flightScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flightScheduleDTO);
    }

    /**
     * {@code DELETE  /flight-schedules/:id} : delete the "id" flightSchedule.
     *
     * @param id the id of the flightScheduleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flight-schedules/{id}")
    public ResponseEntity<Void> deleteFlightSchedule(@PathVariable Long id) {
        log.debug("REST request to delete FlightSchedule : {}", id);
        flightScheduleService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
