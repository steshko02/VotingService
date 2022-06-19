package com.senla.steshko.service;

import com.senla.steshko.api.UserService;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.entities.Role;
import com.senla.steshko.entities.User;
import com.senla.steshko.exception.EntityNotFoundException;
import com.senla.steshko.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Long save(User entity) {
        if(entity == null) {
            log.error("Entity of {} - NULL.", User.class);
            throw new NullPointerException("entity for saving is null");
        }
        entity.setRoles(Collections.singleton(new Role(2L, "USER")));
        return userRepository.save(entity).getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        if(!userRepository.existsById(id)) {
            log.error("Entity not found exception {}.",User.class);
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
        userRepository.deleteById(id);
        return id;
    }

    @Transactional
    @Override
    public User getById(Long id) {
        if(!userRepository.existsById(id)) {
            log.error("Entity not found exception {}.",User.class);
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
        return userRepository.findUserById(id);
    }

    @Transactional
    @Override
    public User update(User newEntity, Long id) {
        if(newEntity == null) {
            log.error("Entity of {} - NULL.", User.class);
            throw new NullPointerException("Entity of "+ User.class+ " - NULL");
        }
        User entityFromDB = getById(id);
        if(entityFromDB == null) {
            log.error("Entity not found exception {}.",User.class);
            throw new EntityNotFoundException(User.class,"id", id.toString());
        }
        newEntity.setId(entityFromDB.getId());
        entityFromDB = newEntity;
        return userRepository.save(entityFromDB);
    }

    @Transactional
    @Override
    public List<User> getByRole(String role) {
        return userRepository.findUsersByRole( role);
    }

    @Transactional
    @Override
    public boolean ifExists(String email) {
//       UserDto dto = userRepository.findByEmail(email);
        return userRepository.existsByEmail(email);
    }
}
