package com.travelapp.core.model;

import com.travelapp.core.model.enums.UserType;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        User user = new User();
        user.setId("u1");
        user.setUserType(UserType.USER);
        user.setFirstName("Taro");
        user.setLastName("Yamada");
        user.setEmail("taro@gmail.com");
        user.setUsername("taro@gmail.com");
        user.setPassword("secret");
        user.setCreationDate(new Date());
        user.setUniqueUsername("taroUnique");
        user.setBalance(500.0);

        assertEquals("u1", user.getId());
        assertEquals(UserType.USER, user.getUserType());
        assertEquals("Taro", user.getFirstName());
        assertEquals("Yamada", user.getLastName());
        assertEquals("taro@gmail.com", user.getEmail());
        assertEquals("taro@gmail.com", user.getUsername());
        assertEquals("secret", user.getPassword());
        assertNotNull(user.getCreationDate());
        assertEquals("taroUnique", user.getUniqueUsername());
        assertEquals(500.0, user.getBalance());
    }

    @Test
    void testUserAuthorities() {
        // Check that the getAuthorities method returns a SimpleGrantedAuthority with user type as string
        User user = new User();
        user.setUserType(UserType.ADMIN);
        user.setUsername("admin@example.com");
        // The getAuthorities implementation in your code returns a list with a new SimpleGrantedAuthority(userType.name())
        assertFalse(user.getAuthorities().isEmpty());
        assertEquals("ADMIN", user.getAuthorities().iterator().next().getAuthority());
    }
}
