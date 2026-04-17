package com.trainday.bodybuilder.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trainday.bodybuilder.api.DTO.request.LoginRequest;
import com.trainday.bodybuilder.api.DTO.response.LoginResponse;
import com.trainday.bodybuilder.domain.model.Login;
import com.trainday.bodybuilder.domain.repository.LoginRepository;
import com.trainday.bodybuilder.infra.security.JwtService;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    LoginRepository loginrepository;

    @Mock
    JwtService jwtservice;

    @Mock
    AuthenticationManager authenticationManager;
    
    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    LoginService loginservice;


    @Test
    void shouldCreateLogin(){
        LoginRequest request = new LoginRequest(
            "id-user",
            "dpericles6@gmail.com",
            "123456"
        );

        when(passwordEncoder.encode("123456")).thenReturn("senha-criptografada");
        when(loginrepository.save(any(Login.class)))
            .thenAnswer(invocation -> {
                Login savedLogin = invocation.getArgument(0);
                savedLogin.setId("id-user");
                return savedLogin;
            });

        LoginResponse service = loginservice.createLogin(request);

        assertNotNull(service);
        assertEquals("id-user", service.id());
        assertEquals("dpericles6@gmail.com", service.email());

        verify(passwordEncoder).encode("123456");
        verify(loginrepository).save(any(Login.class));

    }

    @Test
    void shoudauthenticate(){
          LoginRequest request = new LoginRequest(
            "id-user",
            "dpericles6@gmail.com",
            "123456"
        );

       org.springframework.security.core.Authentication authentication =
            mock(org.springframework.security.core.Authentication.class);

         when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
             .thenReturn(authentication);

        when(jwtservice.generateToken("dpericles6@gmail.com"))
            .thenReturn("token-fake");  
            
        String token = loginservice.authenticate(request);

        assertNotNull(token);
        assertEquals("token-fake", token);

        verify(authenticationManager).authenticate(argThat(auth ->
            auth.getPrincipal().equals("dpericles6@gmail.com") &&
            auth.getCredentials().equals("123456")
        ));

        verify(jwtservice).generateToken("dpericles6@gmail.com");
    }


    



}
