package com.trainday.bodybuilder.infra.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.trainday.bodybuilder.domain.model.Login;

public class UserDetailsImplTest {

    @Test
    void shouldReturnUserDetailsFromLogin() {
        Login login = new Login();
        login.setId("1");
        login.setEmail("daniel@email.com");
        login.setPassword("123456");

        UserDetailsImpl userDetails = new UserDetailsImpl(login);

        assertEquals("daniel@email.com", userDetails.getUsername());
        assertEquals("123456", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

}
