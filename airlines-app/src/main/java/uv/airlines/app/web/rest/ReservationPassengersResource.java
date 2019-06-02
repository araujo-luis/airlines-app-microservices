package uv.airlines.app.web.rest;

import uv.airlines.app.service.ReservationPassengersService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;

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
 * REST controller for managing
 * {@link uv.airlines.app.domain.ReservationPassengers}.
 */
@RestController
@RequestMapping("/api")
public class ReservationPassengersResource {

    private final Logger log = LoggerFactory.getLogger(ReservationPassengersResource.class);

    private static final String ENTITY_NAME = "ReservationPassengers";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final ReservationPassengersService reservationPassengersService;

    public ReservationPassengersResource(ReservationPassengersService reservationPassengersService) {
        this.reservationPassengersService = reservationPassengersService;
    }

    /**
     * {@code POST  /reservation-passengers} : Create a new reservationPassengers.
     *
     * @param reservationPassengersDTO the reservationPassengersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new reservationPassengersDTO, or with status
     *         {@code 400 (Bad Request)} if the reservationPassengers has already an
     *         ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reservation-passengers")
    public ResponseEntity<ReservationPassengersDTO> createReservationPassengers(
            @Valid @RequestBody ReservationPassengersDTO reservationPassengersDTO) throws URISyntaxException {
        log.debug("REST request to save ReservationPassengers : {}", reservationPassengersDTO);
        if (reservationPassengersDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservationPassengers cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        ReservationPassengersDTO result = reservationPassengersService.save(reservationPassengersDTO);
        return ResponseEntity
                .created(new URI("/api/reservation-passengers/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /reservation-passengers} : Updates an existing
     * reservationPassengers.
     *
     * @param reservationPassengersDTO the reservationPassengersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated reservationPassengersDTO, or with status
     *         {@code 400 (Bad Request)} if the reservationPassengersDTO is not
     *         valid, or with status {@code 500 (Internal Server Error)} if the
     *         reservationPassengersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reservation-passengers")
    public ResponseEntity<ReservationPassengersDTO> updateReservationPassengers(
            @Valid @RequestBody ReservationPassengersDTO reservationPassengersDTO) throws URISyntaxException {
        log.debug("REST request to update ReservationPassengers : {}", reservationPassengersDTO);
        if (reservationPassengersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationPassengersDTO result = reservationPassengersService.save(reservationPassengersDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                reservationPassengersDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /reservation-passengers} : get all the reservationPassengers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of reservationPassengers in body.
     */
    @GetMapping("/reservation-passengers")
    public List<ReservationPassengersDTO> getAllReservationPassengers() {
        log.debug("REST request to get all ReservationPassengers");
        return reservationPassengersService.findAll();
    }

    /**
     * {@code GET  /reservation-passengers/:id} : get the "id"
     * reservationPassengers.
     *
     * @param id the id of the reservationPassengersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reservationPassengersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservation-passengers/{id}")
    public ResponseEntity<ReservationPassengersDTO> getReservationPassengers(@PathVariable Long id) {
        log.debug("REST request to get ReservationPassengers : {}", id);
        Optional<ReservationPassengersDTO> reservationPassengersDTO = reservationPassengersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationPassengersDTO);
    }

    /**
     * {@code GET  /reservation-passengers/:id} : get the "id"
     * reservationPassengers.
     *
     * @param id the id of the reservationPassengersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reservationPassengersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservation-passengers/boarding-priority")
    public List<PassengersPriorityDTO> getPriorityPassengers() {

        return reservationPassengersService.findAllPassengersWithPriority();
    }

    /**
     * {@code GET  /reservation-passengers/:id} : get the "id"
     * reservationPassengers.
     *
     * @param id the id of the reservationPassengersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reservationPassengersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservation-passengers/top-destinations")
    public List<ProfitFlightsDTO> getTop10Destinations() {

        return reservationPassengersService.getTop10ProfitsFlights();
    }

    /**
     * {@code GET  /reservation-passengers/:id} : get the "id"
     * reservationPassengers.
     *
     * @param id the id of the reservationPassengersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the reservationPassengersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservation-passengers/monthly-profits")
    public List<MonthlyProfitsDTO> getMonthlyProfits() {
        return reservationPassengersService.getMonthlyProfits();
    }

    /**
     * {@code PUT  /reservation-passengers} : Updates an existing
     * reservationPassengers.
     *
     * @param reservationPassengersDTO the reservationPassengersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated reservationPassengersDTO, or with status
     *         {@code 400 (Bad Request)} if the reservationPassengersDTO is not
     *         valid, or with status {@code 500 (Internal Server Error)} if the
     *         reservationPassengersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reservation-passengers/{idPassenger}/reservation/{idReservation}/change/{idSeat}")
    public ResponseEntity<Boolean> changeSeat(@PathVariable String idPassenger, @PathVariable Long idReservation,
            @PathVariable String idSeat) {
        reservationPassengersService.changeSeat(idPassenger, idReservation, idSeat);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, idReservation.toString()))
                .build();

    }

    /**
     * {@code DELETE  /reservation-passengers/:id} : delete the "id"
     * reservationPassengers.
     *
     * @param id the id of the reservationPassengersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reservation-passengers/{id}")
    public ResponseEntity<Void> deleteReservationPassengers(@PathVariable Long id) {
        log.debug("REST request to delete ReservationPassengers : {}", id);
        reservationPassengersService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
