package com.trainday.bodybuilder.api.DTO.request;

public record AthleteRequest(
    String cpf,
    String name,
    String email,
    Long age,
    Double height,
    Double weight,
    Long percentagefat
){
  
}    



