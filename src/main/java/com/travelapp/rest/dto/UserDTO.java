package com.travelapp.rest.dto;

import com.travelapp.core.model.enums.UserType;

import java.util.Date;

public class UserDTO {
    private String id;
    private String name;
    private String username;
    private UserType userType;
    private String email;
    private Date creationDate;
}
