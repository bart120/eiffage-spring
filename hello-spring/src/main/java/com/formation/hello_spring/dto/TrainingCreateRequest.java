package com.formation.hello_spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TrainingCreateRequest(
        @NotBlank(message = "Le titre est obligatoire") String title,
        @NotBlank(message = "La description est obligatoire") String description,
        @Min(value = 1, message = "La durée doit être au moins de 1 jour") int duration) {
}
