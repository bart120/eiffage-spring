package com.formation.hello_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.hello_spring.dto.TrainingCreateRequest;
import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.exception.ResourceNotFoundException;
import com.formation.hello_spring.model.Training;
import com.formation.hello_spring.repository.TrainingRepository;

import jakarta.transaction.Transactional;

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
        return TrainingResponse.fromModel(trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Formation introuvable avec l'id : " + id)));
    }

    public TrainingResponse create(TrainingCreateRequest trainingCreateRequest) {
        Training training = new Training(
                trainingCreateRequest.title(),
                trainingCreateRequest.description(),
                trainingCreateRequest.duration());

        Training savedTraining = trainingRepository.save(training);

        return TrainingResponse.fromModel(savedTraining);

    }

    @Transactional
    public TrainingResponse update(Long id, TrainingCreateRequest trainingCreateRequest) {
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Formation introuvable avec l'id : " + id));

        training.setTitle(trainingCreateRequest.title());
        training.setDescription(trainingCreateRequest.description());
        training.setDuration(trainingCreateRequest.duration());

        /* Training updatedTraining = trainingRepository.save(training); */

        return TrainingResponse.fromModel(training);
    }

    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }

    @Transactional
    public void deactivate(Long id) {
        Training training = trainingRepository.findById(id).orElseThrow();
        training.setActive(false);
    }

    public List<TrainingResponse> searchByTitle(String title) {
        return trainingRepository.findByTitleContainingIgnoreCase(title)
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
