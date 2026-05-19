package com.formation.hello_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.repository.TrainingRepository;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<TrainingResponse> findAll() {
        return trainingRepository.findAll()
                .stream()
                .map(TrainingResponse::fromModel)
                .toList();
    }

    ////// TEST//////
    public String getTrainingName() {
        return "Formation Spring Boot.";
    }

    public String getTrainingDuration() {
        return "La formation dure 2 jours.";
    }
}
