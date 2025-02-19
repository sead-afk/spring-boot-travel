package com.travelapp.core.repository;

import com.travelapp.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);
    //@Query(value="{$or:[{email:'?0'}, {username:'?0'}]}")
    Optional<User> findByUsernameOrEmail(String payload);
    Optional<User> findUserById(String userId);

    Optional<User> findByUsername(String username);

    Optional<User> findUserByUsername(String username);

    //List<User> findByUser(User user);
}
