package com.senla.steshko.dto.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends AbstractDto{

    private String firstName;

    private String lastName;

    private  String email;

    private String password;

    public UserDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto() {
    }
}
