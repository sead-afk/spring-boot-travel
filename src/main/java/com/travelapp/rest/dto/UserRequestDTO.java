package com.travelapp.rest.dto;

import com.travelapp.core.model.enums.UserType;
import org.springframework.stereotype.Component;

import java.util.Date;

public class UserRequestDTO {
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
