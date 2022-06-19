package com.senla.steshko.dtoapi;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.entities.User;

import java.util.List;

public interface UserDtoService {
    Long save(User entity);
    Long delete(Long id);
    UserDto getById(Long id);
    UserDto update(UserDto newEntity, Long id);

    List<UserDto> getByRole(String role);

    boolean ifExists(String email);
}
