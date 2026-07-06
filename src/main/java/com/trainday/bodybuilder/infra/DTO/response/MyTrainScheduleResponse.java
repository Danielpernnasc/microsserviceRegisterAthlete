package com.trainday.bodybuilder.infra.DTO.response;

import java.util.List;

public record MyTrainScheduleResponse(
         String weekday,

         String musclegroup,

         String emphasis,

         List<ExerciseResonse> exercises
) {
}
