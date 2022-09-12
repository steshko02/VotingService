package com.senla.steshko.mappers;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserDozerMapper {

    @Autowired
    @Qualifier("dozerMapper")
    private Mapper<User, UserDto> objectMapper;
    private User user;
    private UserDto userDto;

    private final String  testEmail = "testEmail.com";
    private final String  testLastName = "testLastName";
    private final String  testFirstname = "testFirstname";
    private final String  testPassword = "testPassword";

    private void setupUser(){
        user =  new User();
        user.setId(1L);
        user.setEmail(testEmail);
        user.setLastName(testLastName);
        user.setFirstName(testFirstname);
        user.setPassword(testPassword);
    }
    private void setupUserDto(){
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail(testEmail);
        userDto.setLastName(testLastName);
        userDto.setFirstName(testFirstname);
        userDto.setPassword(testPassword);
    }

    @BeforeEach
    private void setup() {
        setupUser();
        setupUserDto();
    }

    @Test
    public void mapToUserFromUserDtoWithDozerTest()  {
        UserDto resultUserDto = objectMapper.toDto(user);
        assertEquals(resultUserDto,userDto);
    }
}
