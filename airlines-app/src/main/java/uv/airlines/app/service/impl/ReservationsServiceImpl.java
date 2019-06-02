package uv.airlines.app.service.impl;

import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.domain.Reservations;
import uv.airlines.app.repository.ReservationsRepository;
import uv.airlines.app.service.dto.ReservationPassengersDTO;
import uv.airlines.app.service.dto.ReservationsDTO;
import uv.airlines.app.service.mapper.ReservationPassengersMapper;
import uv.airlines.app.service.mapper.ReservationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Reservations}.
 */
@Service
@Transactional
public class ReservationsServiceImpl implements ReservationsService {

    private final Logger log = LoggerFactory.getLogger(ReservationsServiceImpl.class);

    private final ReservationsRepository reservationsRepository;

    private final ReservationsMapper reservationsMapper;
    private final ReservationPassengersMapper reservationPassengersMapper;

    public ReservationsServiceImpl(ReservationsRepository reservationsRepository, ReservationsMapper reservationsMapper,
            ReservationPassengersMapper reservationPassengersMapper) {
        this.reservationsRepository = reservationsRepository;
        this.reservationsMapper = reservationsMapper;
        this.reservationPassengersMapper = reservationPassengersMapper;
    }

    /**
     * Save a reservations.
     *
     * @param reservationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReservationsDTO saveAll(ReservationsDTO reservationsDTO,
            List<ReservationPassengersDTO> reservationPassengersDTOs) {
        log.debug("Request to save Reservations : {}", reservationsDTO);
        Reservations reservation = reservationsMapper.toEntity(reservationsDTO);
        List<ReservationPassengers> rp = reservationPassengersMapper.toEntity(reservationPassengersDTOs);
        for (ReservationPassengers passenger : rp) {
            reservation.addReservationPassengers(passenger);
        }
        reservation = reservationsRepository.save(reservation);
        return reservationsMapper.toDto(reservation);
    }

    /**
     * Save a reservations.
     *
     * @param reservationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReservationsDTO save(ReservationsDTO reservationsDTO) {
        log.debug("Request to save Reservations : {}", reservationsDTO);
        Reservations reservation = reservationsMapper.toEntity(reservationsDTO);
        reservation = reservationsRepository.save(reservation);
        return reservationsMapper.toDto(reservation);
    }

    /**
     * Get all the reservations.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReservationsDTO> findAll() {
        log.debug("Request to get all Reservations");
        return reservationsRepository.findAll().stream().map(reservationsMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one reservations by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationsDTO> findOne(Long id) {
        log.debug("Request to get Reservations : {}", id);
        return reservationsRepository.findById(id).map(reservationsMapper::toDto);
    }

    /**
     * Delete the reservations by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reservations : {}", id);
        reservationsRepository.deleteById(id);
    }
}
