package com.trainday.bodybuilder.infra.DTO.response;

public record ExerciseResonse(
         String nameExercise,

         Integer series,

          String repetitions,

         String breakTime,

         String observation
) {
}
