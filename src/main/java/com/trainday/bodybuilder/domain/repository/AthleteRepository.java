package com.trainday.bodybuilder.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trainday.bodybuilder.domain.model.Athlete;



@Repository
public interface AthleteRepository extends MongoRepository<Athlete, String> {

    Athlete save(Optional<Athlete> existAthlete);

    Optional<Athlete> findByIdAndEmail(String id, String email);

    void deleteById(Athlete athlete);


}
