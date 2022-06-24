package com.senla.steshko.mappers;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto>{

    private final ModelMapper mapper;

    public User toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDto toDto(User entity) {
            return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }

}
