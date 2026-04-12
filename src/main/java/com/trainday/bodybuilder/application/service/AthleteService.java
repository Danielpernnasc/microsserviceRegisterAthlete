package com.trainday.bodybuilder.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trainday.bodybuilder.api.DTO.request.AthleteRequest;
import com.trainday.bodybuilder.api.DTO.response.AthleteResponse;
import com.trainday.bodybuilder.domain.model.Athlete;
import com.trainday.bodybuilder.domain.model.Login;
import com.trainday.bodybuilder.domain.repository.AthleteRepository;
import com.trainday.bodybuilder.domain.repository.LoginRepository;

@Service
public class AthleteService {

    private final AthleteRepository athleterepository;
    private final LoginRepository loginRepository;
    private static final String ATHLETE_NOT_FOUND = "Athlete not found with id ";


    public AthleteService(
        AthleteRepository athleterepository,
        LoginRepository loginRepository
    ){
        this.athleterepository = athleterepository;
        this.loginRepository = loginRepository;
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
        athlete.setPercentageFat(reqAthlete.percentagefat());
        athlete.setUserId(user.getId());


        return athleterepository.save(athlete);
        
    }

   public List<AthleteResponse> searchAthlete(){
        return athleterepository.findAll()
                .stream()
                .map(a -> new AthleteResponse(
                    a.getId(),
                    a.getCPF(),
                    a.getName(),
                    a.getEmail(),
                    a.getAge(),
                    a.getHeight(),
                    a.getWeight(),
                    a.getpercentageFat()
                ))
                .toList(); // MUITO IMPORTANTE
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
                .ifPresent(existAthlete::setPercentageFat);    
                
                return athleterepository.save(existAthlete);
     }
}
