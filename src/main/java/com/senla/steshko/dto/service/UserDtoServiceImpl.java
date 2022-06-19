package com.senla.steshko.dto.service;

import com.senla.steshko.api.UserService;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.User;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper<User, UserDto> modelMapper;

    @Override
    public Long save(User entity) {
        return userService.save(entity);
    }

    @Override
    public Long delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public UserDto getById(Long id) {
        return modelMapper.toDto(userService.getById(id));
    }

    @Override
    public UserDto update(UserDto newEntity, Long id) {
        return modelMapper.toDto(userService.update(modelMapper.toEntity(newEntity), id));
    }

    @Override
    public List<UserDto> getByRole(String role) {
        return userService.getByRole(role).stream().map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public boolean ifExists(String email) {
        return userService.ifExists(email);
    }
}
