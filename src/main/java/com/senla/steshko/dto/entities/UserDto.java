package com.senla.steshko.dto.entities;

import lombok.Data;

@Data
public class UserDto extends AbstractDto{

    private String firstName;

    private String lastName;

    private  String email;

    public UserDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
