package com.formation.hello_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.hello_spring.dto.TrainingCreateRequest;
import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.model.Training;
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

    public TrainingResponse findById(Long id) {
        return TrainingResponse.fromModel(trainingRepository.findById(id).orElseThrow());
    }

    public TrainingResponse create(TrainingCreateRequest trainingCreateRequest) {
        Training training = new Training(
                trainingCreateRequest.title(),
                trainingCreateRequest.description(),
                trainingCreateRequest.duration());

        Training savedTraining = trainingRepository.save(training);

        return TrainingResponse.fromModel(savedTraining);

    }

    ////// TEST//////
    public String getTrainingName() {
        return "Formation Spring Boot.";
    }

    public String getTrainingDuration() {
        return "La formation dure 2 jours.";
    }
}
