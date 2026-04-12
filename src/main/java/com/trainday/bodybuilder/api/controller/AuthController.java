package com.trainday.bodybuilder.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainday.bodybuilder.api.DTO.request.LoginRequest;
import com.trainday.bodybuilder.api.DTO.response.LoginResponse;
import com.trainday.bodybuilder.application.service.LoginService;



@RestController
@RequestMapping("/auth")
public class AuthController {
     private final LoginService service;

    public AuthController(LoginService service){
        this.service = service;
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody LoginRequest request){
        return service.createLogin(request);
    }


}
