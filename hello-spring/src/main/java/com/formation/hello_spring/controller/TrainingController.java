package com.formation.hello_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.hello_spring.dto.TrainingCreateRequest;
import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.service.TrainingService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<TrainingResponse> getAllTrainings() {
        return trainingService.findAll();
    }

    @GetMapping("/{id}")
    public TrainingResponse getTrainingById(@PathVariable Long id) {
        return trainingService.findById(id);
    }

    @PostMapping
    public TrainingResponse createTrainging(@Valid @RequestBody TrainingCreateRequest training) {
        return trainingService.create(training);

    }

}
