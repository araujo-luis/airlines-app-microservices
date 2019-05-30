package uv.airlines.app.service.impl;

import uv.airlines.app.service.RolesService;
import uv.airlines.app.domain.Roles;
import uv.airlines.app.repository.RolesRepository;
import uv.airlines.app.service.dto.RolesDTO;
import uv.airlines.app.service.mapper.RolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesServiceImpl(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RolesDTO> findAll() {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll().stream().map(rolesMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the roles where User is {@code null}.
     * 
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RolesDTO> findAllWhereUserIsNull() {
        log.debug("Request to get all roles where User is null");
        return StreamSupport.stream(rolesRepository.findAll().spliterator(), false)
                .filter(roles -> roles.getUser() == null).map(rolesMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one roles by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(String id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findById(id).map(rolesMapper::toDto);
    }

    /**
     * Delete the roles by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }
}
