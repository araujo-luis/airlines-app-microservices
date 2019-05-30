package uv.airlines.app.service.impl;

import uv.airlines.app.service.UserService;
import uv.airlines.app.domain.User;
import uv.airlines.app.repository.UserRepository;
import uv.airlines.app.service.dto.UserDTO;
import uv.airlines.app.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link User}.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository UserRepository;

    private final UserMapper UserMapper;

    public UserServiceImpl(UserRepository UserRepository, UserMapper UserMapper) {
        this.UserRepository = UserRepository;
        this.UserMapper = UserMapper;
    }

    /**
     * Save a User.
     *
     * @param UserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserDTO save(UserDTO UserDTO) {
        log.debug("Request to save User : {}", UserDTO);
        User User = UserMapper.toEntity(UserDTO);
        User = UserRepository.save(User);
        return UserMapper.toDto(User);
    }

    /**
     * Get all the Users.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.debug("Request to get all Users");
        return UserRepository.findAll().stream().map(UserMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one User by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return UserRepository.findById(id).map(UserMapper::toDto);
    }

    /**
     * Delete the User by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete User : {}", id);
        UserRepository.deleteById(id);
    }
}
