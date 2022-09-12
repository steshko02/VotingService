package com.senla.steshko.mappers.dozer;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.entities.User;
import com.senla.steshko.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("dozerMapper")
public class UserDozerMapper implements Mapper<User, UserDto> {
    private final DozerBeanMapper dozerBeanMapper;

    @Override
    public User toEntity(UserDto dto) {
        return dozerBeanMapper.map(dto,User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return dozerBeanMapper.map(entity,UserDto.class);
    }
}
