package com.travelapp.rest.dto;

import com.travelapp.core.model.User;
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
    private String uniqueUsername;
    private Double balance;
    public  UserRequestDTO() { }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserRequestDTO(User user) {
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.uniqueUsername = user.getUniqueUsername();
        this.password = user.getPassword();
        this.balance=user.getBalance();
    }

    public User toEntity() {
        User user = new User();
        user.setUserType(userType != null ? userType : UserType.USER);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setUniqueUsername(uniqueUsername);
        user.setCreationDate(new Date());
        return user;
    }



    public String getUniqueUsername() {
        return uniqueUsername;
    }

    public void setUniqueUsername(String uniqueUsername) {
        this.uniqueUsername = uniqueUsername;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
