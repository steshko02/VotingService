package com.senla.steshko.dto.views;

import lombok.Data;

import java.util.Objects;

@Data
public class UserView {

    private String firstName;

    private String lastName;


    public UserView(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
