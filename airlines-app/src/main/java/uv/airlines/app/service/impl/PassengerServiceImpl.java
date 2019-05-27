package uv.airlines.app.service.impl;

import uv.airlines.app.service.PassengerService;
import uv.airlines.app.domain.Passenger;
import uv.airlines.app.repository.PassengerRepository;
import uv.airlines.app.service.dto.PassengerDTO;
import uv.airlines.app.service.mapper.PassengerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Passenger}.
 */
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final Logger log = LoggerFactory.getLogger(PassengerServiceImpl.class);

    private final PassengerRepository passengerRepository;

    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    /**
     * Save a passenger.
     *
     * @param passengerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PassengerDTO save(PassengerDTO passengerDTO) {
        log.debug("Request to save Passenger : {}", passengerDTO);
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(passenger);
    }

    /**
     * Get all the passengers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PassengerDTO> findAll() {
        log.debug("Request to get all Passengers");
        return passengerRepository.findAll().stream().map(passengerMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one passenger by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PassengerDTO> findOne(Long id) {
        log.debug("Request to get Passenger : {}", id);
        return passengerRepository.findById(id).map(passengerMapper::toDto);
    }

    /**
     * Delete the passenger by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Passenger : {}", id);
        passengerRepository.deleteById(id);
    }
}
