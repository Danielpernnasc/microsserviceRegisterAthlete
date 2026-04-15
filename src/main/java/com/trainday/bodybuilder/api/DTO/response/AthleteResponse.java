package com.trainday.bodybuilder.api.DTO.response;

public record AthleteResponse(
        String id,
        String cpf,
        String name,
        String email,
        Long age,
        Double height,
        Double weight,
        Long percentagefat
) {}
