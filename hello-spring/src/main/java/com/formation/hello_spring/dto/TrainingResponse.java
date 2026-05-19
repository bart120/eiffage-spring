package com.formation.hello_spring.dto;

import com.formation.hello_spring.model.Training;

public record TrainingResponse(
        Long id,
        String title,
        String description,
        int duration,
        boolean active) {
    public static TrainingResponse fromModel(Training training) {
        return new TrainingResponse(
                training.getId(),
                training.getTitle(),
                training.getDescription(),
                training.getDuration(),
                training.isActive());
    }
}
