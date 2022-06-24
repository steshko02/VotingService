package com.senla.steshko.dto.entities;

import lombok.Data;

@Data
public class UserDtoWithoutPass {
    private String firstName;

    private String lastName;

    private  String email;

    public UserDtoWithoutPass(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDtoWithoutPass() {
    }
}
