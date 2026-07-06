package com.trainday.bodybuilder.infra.DTO.response;

import com.trainday.bodybuilder.domain.model.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

public record MyTrainResponse(
         String id,
         String athleteId,
         String athleteCpf,
         String athleteName,
         String athleteemail,
        Role roleAthlete,

         String nameTrain,
         String category,
         Role roleprofessional,
         String professionalId,
         String nameProfessional,
         String cref,
         String description,

        LocalDateTime createdAt,

        List<MyTrainScheduleResponse> schedules
) {
}
