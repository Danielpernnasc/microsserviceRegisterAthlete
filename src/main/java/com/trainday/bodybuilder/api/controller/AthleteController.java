package com.trainday.bodybuilder.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trainday.bodybuilder.api.DTO.request.AthleteRequest;
import com.trainday.bodybuilder.api.DTO.response.AthleteResponse;
import com.trainday.bodybuilder.application.service.AthleteService;
import com.trainday.bodybuilder.domain.model.Athlete;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/athlete")
@SecurityRequirement(name = "bearerAuth")
public class AthleteController {

   
    private final AthleteService service;

    public AthleteController(
        AthleteService service
    ){
        this.service = service;
    }

    @PostMapping
    public Athlete save(@RequestBody AthleteRequest req){
        return service.createAthlete(req);

    }

    @GetMapping("/{id}")
      public Athlete findById(@PathVariable String id) {

        return service.getAthleteById(id);
    
    }


    @PutMapping("/{id}")
    public Athlete updateAthlete(
        @PathVariable String id,
        @RequestBody AthleteRequest updateAthlete
    ){
        return service.updateAthlete(id, updateAthlete);
    }
    
}

