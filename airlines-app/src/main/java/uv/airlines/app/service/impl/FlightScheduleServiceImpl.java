package uv.airlines.app.service.impl;

import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.repository.FlightScheduleRepository;
import uv.airlines.app.service.dto.FlightScheduleDTO;
import uv.airlines.app.service.mapper.FlightScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlightSchedule}.
 */
@Service
@Transactional
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final Logger log = LoggerFactory.getLogger(FlightScheduleServiceImpl.class);

    private final FlightScheduleRepository flightScheduleRepository;

    private final FlightScheduleMapper flightScheduleMapper;

    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository,
            FlightScheduleMapper flightScheduleMapper) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.flightScheduleMapper = flightScheduleMapper;
    }

    /**
     * Save a flightSchedule.
     *
     * @param flightScheduleDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FlightScheduleDTO save(FlightScheduleDTO flightScheduleDTO) {
        log.debug("Request to save FlightSchedule : {}", flightScheduleDTO);
        FlightSchedule flightSchedule = flightScheduleMapper.toEntity(flightScheduleDTO);
        flightSchedule = flightScheduleRepository.save(flightSchedule);
        return flightScheduleMapper.toDto(flightSchedule);
    }

    /**
     * Get all the flightSchedules.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FlightScheduleDTO> findAll() {
        log.debug("Request to get all FlightSchedules");
        return flightScheduleRepository.findAll().stream().map(flightScheduleMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one flightSchedule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FlightScheduleDTO> findOne(Long id) {
        log.debug("Request to get FlightSchedule : {}", id);
        return flightScheduleRepository.findById(id).map(flightScheduleMapper::toDto);
    }

    /**
     * Delete the flightSchedule by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FlightSchedule : {}", id);
        flightScheduleRepository.deleteById(id);
    }
}
