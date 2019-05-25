package uv.airlines.app.service.impl;

import uv.airlines.app.service.AirportsService;
import uv.airlines.app.domain.Airports;
import uv.airlines.app.repository.AirportsRepository;
import uv.airlines.app.service.dto.AirportsDTO;
import uv.airlines.app.service.mapper.AirportsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Airports}.
 */
@Service
@Transactional
public class AirportsServiceImpl implements AirportsService {

    private final Logger log = LoggerFactory.getLogger(AirportsServiceImpl.class);

    private final AirportsRepository airportsRepository;

    private final AirportsMapper airportsMapper;

    public AirportsServiceImpl(AirportsRepository airportsRepository, AirportsMapper airportsMapper) {
        this.airportsRepository = airportsRepository;
        this.airportsMapper = airportsMapper;
    }

    /**
     * Save a airports.
     *
     * @param airportsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AirportsDTO save(AirportsDTO airportsDTO) {
        log.debug("Request to save Airports : {}", airportsDTO);
        Airports airports = airportsMapper.toEntity(airportsDTO);
        airports = airportsRepository.save(airports);
        return airportsMapper.toDto(airports);
    }

    /**
     * Get all the airports.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AirportsDTO> findAll() {
        log.debug("Request to get all Airports");
        return airportsRepository.findAll().stream().map(airportsMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one airports by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AirportsDTO> findOne(Long id) {
        log.debug("Request to get Airports : {}", id);
        return airportsRepository.findById(id).map(airportsMapper::toDto);
    }

    /**
     * Delete the airports by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Airports : {}", id);
        airportsRepository.deleteById(id);
    }
}
