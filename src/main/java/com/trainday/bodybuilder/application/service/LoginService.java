package com.trainday.bodybuilder.application.service;


import org.springframework.stereotype.Service;

import com.trainday.bodybuilder.api.DTO.request.LoginRequest;
import com.trainday.bodybuilder.api.DTO.response.LoginResponse;
import com.trainday.bodybuilder.domain.model.Login;
import com.trainday.bodybuilder.domain.repository.LoginRepository;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(
        LoginRepository loginRepository
    ){
        this.loginRepository = loginRepository;
    }


    public LoginResponse createLogin(LoginRequest loginRequest ){
        Login login = new Login();
        login.setEmail(loginRequest.email());
        login.setPassword(loginRequest.password());
         Login saved = loginRepository.save(login);
        return new LoginResponse(
          saved.getId(),
          saved.getEmail()
        );
        
    }



}
