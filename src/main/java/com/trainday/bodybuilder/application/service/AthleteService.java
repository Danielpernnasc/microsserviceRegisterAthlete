package com.trainday.bodybuilder.application.service;


import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.trainday.bodybuilder.api.DTO.request.AthleteRequest;
import com.trainday.bodybuilder.api.DTO.response.AthleteResponse;
import com.trainday.bodybuilder.domain.model.Athlete;
import com.trainday.bodybuilder.domain.model.Login;
import com.trainday.bodybuilder.domain.repository.AthleteRepository;
import com.trainday.bodybuilder.domain.repository.LoginRepository;
import com.trainday.bodybuilder.infra.security.JwtService;

@Service
public class AthleteService {

    private final AthleteRepository athleterepository;
    private final LoginRepository loginRepository;
    private static final String ATHLETE_NOT_FOUND = "Athlete not found with id ";
    private final JwtService jwtService;


    public AthleteService(
        AthleteRepository athleterepository,
        LoginRepository loginRepository,
        JwtService jwtService
    ){
        this.athleterepository = athleterepository;
        this.loginRepository = loginRepository;
        this.jwtService = jwtService;
    }

    public Athlete createAthlete(AthleteRequest reqAthlete){
        Login user = loginRepository.findByEmail(reqAthlete.email())
               .orElseThrow(() -> new RuntimeException("User not found: " + reqAthlete.email()));

         
        Athlete athlete = new Athlete();
        athlete.setCPF(reqAthlete.cpf());
         athlete.setName(reqAthlete.name());
         athlete.setEmail(reqAthlete.email());
        athlete.setAge(reqAthlete.age());
        athlete.setHeight(reqAthlete.height());
        athlete.setWeight(reqAthlete.weight());
        athlete.setpercentageFat(reqAthlete.percentagefat());
        athlete.setUserId(user.getId());


        return athleterepository.save(athlete);
        
    }
    public AthleteResponse getAthleteById(String id  /*String token*/) {
        /*, String token*/

         //String jwt = token.replace("Bearer ", "");
        //String userId = jwtService.extractUsername(jwt); 

        //Athlete athlete = athleterepository.findByIdAndEmail(id ,userId)
        Athlete athlete = athleterepository.findById(id /*,userId*/)
                .orElseThrow(() -> new RuntimeException(ATHLETE_NOT_FOUND + id));

        return new AthleteResponse(
            athlete.getId(),
            athlete.getCPF(),
            athlete.getName(),
            athlete.getEmail(),
            athlete.getAge(),
            athlete.getHeight(),
            athlete.getWeight(),
            athlete.getpercentageFat()
        );
    }

	 public Athlete updateAthlete(String id,  AthleteRequest updateAthlete){
           Athlete existAthlete = athleterepository.findById(id)
        .orElseThrow(() -> new RuntimeException(ATHLETE_NOT_FOUND));

            Optional.ofNullable(updateAthlete.cpf())
                .ifPresent(existAthlete::setCPF);

            Optional.ofNullable(updateAthlete.name())
                .ifPresent(existAthlete::setName);
                
            Optional.ofNullable(updateAthlete.age())
                .ifPresent(existAthlete::setAge);

            Optional.ofNullable(updateAthlete.email())
                .ifPresent(existAthlete::setEmail);

            Optional.ofNullable(updateAthlete.height())
                .ifPresent(existAthlete::setHeight);

            Optional.ofNullable(updateAthlete.weight())
                .ifPresent(existAthlete::setWeight);

            Optional.ofNullable(updateAthlete.percentagefat())
                .ifPresent(existAthlete::setpercentageFat);

                
                return athleterepository.save(existAthlete);
     }

    public void deleteAthlete(String id) {

      Athlete athlete = athleterepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not found the id for Delete"));
         String userId = athlete.getUserId();

        athleterepository.deleteById(id);   // 👈 primeiro
        System.out.println("DELETE ID: " + id);
        loginRepository.deleteById(userId); // 👈 depois
        System.out.println("USER ID: " + userId);
     }
}
