package com.trainday.bodybuilder.api.DTO.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AthleteRequestTest {

    @Test
    void shouldAthleteRequest(){

        AthleteRequest athleteRequest = new AthleteRequest(
            "999.999.999-99",
            "Daniel Péricles do Nascimento",
            "dpericles6@gmail.com",
            45L,
            182.5,
            105.5,
            18L
        );
        assertEquals("999.999.999-99", athleteRequest.cpf());
        assertEquals("Daniel Péricles do Nascimento", athleteRequest.name());
        assertEquals("dpericles6@gmail.com", athleteRequest.email());
        assertEquals(45L, athleteRequest.age());
        assertEquals(182.5, athleteRequest.height());
        assertEquals(105.5, athleteRequest.weight());
        assertEquals(18L, athleteRequest.percentagefat());

        
    }

}
