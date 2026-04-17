package com.trainday.bodybuilder.application.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainday.bodybuilder.api.DTO.request.LoginRequest;
import com.trainday.bodybuilder.api.DTO.response.LoginResponse;
import com.trainday.bodybuilder.domain.model.Login;
import com.trainday.bodybuilder.domain.repository.LoginRepository;
import com.trainday.bodybuilder.infra.security.JwtService;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final JwtService jwtservice;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginService(
        LoginRepository loginRepository,
        JwtService jwtservice,
       AuthenticationManager authenticationManager,
       PasswordEncoder passwordEncoder
    ){
        this.loginRepository = loginRepository;
        this.jwtservice = jwtservice;
        this.authenticationManager = authenticationManager; 
        this.passwordEncoder =  passwordEncoder;
    }


    public LoginResponse createLogin(LoginRequest loginRequest ){
        Login login = new Login();
        login.setId(loginRequest.id());
        login.setEmail(loginRequest.email());
        login.setPassword(passwordEncoder.encode(loginRequest.password()));
         Login saved = loginRepository.save(login);
        return new LoginResponse(
          saved.getId(),
          saved.getEmail()
        );
        
    }


    public String authenticate(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        return jwtservice.generateToken(request.email()); // ← gera o token
    }



}
