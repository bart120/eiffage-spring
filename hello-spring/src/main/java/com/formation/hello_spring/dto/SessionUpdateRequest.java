package com.formation.hello_spring.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SessionUpdateRequest(
        @NotNull(message = "La date de début est obligatoire") @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur") LocalDate startDate,

        @NotNull(message = "La date de fin est obligatoire") @FutureOrPresent(message = "La date de fin doit être aujourd'hui ou dans le futur") LocalDate endDate,

        @NotNull(message = "Le lieu est obligatoire") String location,

        @Min(value = 3, message = "Le nombre maximum de participants doit être au moins 1") int maxParticipants) {
}
