package com.travelapp.rest.dto;

import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.UserType;

import java.util.Date;

public class UserDTO {

    private String id;
    private String name;
    private String username;
    private UserType userType;
    private String email;
    private Date creationDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " +user.getLastName();
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.email = user.getEmail();
        this.creationDate = user.getCreationDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
