package com.senla.steshko.api;

import com.senla.steshko.dto.entities.UserAuthDto;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dto.entities.UserDtoWithoutPass;
import com.senla.steshko.exception.IncorrectPasswordException;

import java.util.Map;

public interface AuthenticationService {
    Map<String, String> login(UserAuthDto dto) throws IncorrectPasswordException;
    UserDtoWithoutPass register(UserDto dto) throws IncorrectPasswordException;
}
