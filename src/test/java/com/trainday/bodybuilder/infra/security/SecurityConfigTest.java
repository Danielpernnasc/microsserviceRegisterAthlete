package com.trainday.bodybuilder.infra.security;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.trainday.bodybuilder.api.DTO.request.LoginRequest;
import com.trainday.bodybuilder.api.controller.AuthController;
import com.trainday.bodybuilder.application.service.LoginService;

import jakarta.servlet.Filter;

import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    
      @Autowired
    WebApplicationContext context;

    @Autowired
    Filter springSecurityFilterChain;

    @Autowired
    FakeLoginService loginService;

    MockMvc mockMvc;
       @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

        @Test
    void loginDeveSerPublico() throws Exception {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "id": "user-1",
                      "email": "user@test.com",
                      "password": "123456"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token-test"));

                   LoginRequest authenticatedRequest = loginService.getLastAuthenticatedRequest();

                    assertNotNull(authenticatedRequest);
                    assertEquals("user@test.com", authenticatedRequest.email());
                    assertEquals("123456", authenticatedRequest.password());
                }

    @Configuration
    @EnableWebMvc
    @EnableWebSecurity
    @Import(SecurityConfig.class)
    static class TestBeans {

        @Bean
        AuthController authController(LoginService loginService) {
            return new AuthController(loginService);
        }

        @Bean
        FakeLoginService loginService() {
            return new FakeLoginService();
        }

        @Bean
        JwtService jwtService() {
            JwtService jwtService = new JwtService();
            ReflectionTestUtils.setField(jwtService, "secret", "chave-falsa-so-para-security-config-test-2026");
            ReflectionTestUtils.setField(jwtService, "experation", 3600000L);
            return jwtService;
        }

        @Bean
        UserDetailsService userDetailsService() {
            return username -> {
                throw new IllegalArgumentException("This test should not try to load a user");
            };
        }

        @Bean
        JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
            return new JwtAuthFilter(jwtService, userDetailsService);
        }
    }

    static class FakeLoginService extends LoginService{
          private LoginRequest lastAuthenticatedRequest;


          FakeLoginService(){
            super(null, null, null, null);
          }

          @Override
          public String authenticate(LoginRequest request){
            this.lastAuthenticatedRequest = request;
            return "token-test";
          }

          LoginRequest getLastAuthenticatedRequest(){
            return lastAuthenticatedRequest;
          }
    }


  

}

   






