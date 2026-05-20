package com.formation.hello_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.hello_spring.dto.TrainingCreateRequest;
import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.service.TrainingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/trainings")
@Tag(name = "Formations", description = "Gestion des formations")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    @Operation(summary = "Récupérer la liste de toutes les formations")
    public List<TrainingResponse> getAllTrainings() {
        return trainingService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une formation par son ID")
    public TrainingResponse getTrainingById(@PathVariable Long id) {
        return trainingService.findById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des formations par titre")
    public List<TrainingResponse> searchTrainingsByTitle(@RequestParam String title) {
        return trainingService.searchByTitle(title);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle formation")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingResponse createTraining(@Valid @RequestBody TrainingCreateRequest training) {
        return trainingService.create(training);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une formation existante")
    public TrainingResponse updateTraining(@PathVariable Long id, @Valid @RequestBody TrainingCreateRequest training) {
        return trainingService.update(id, training);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une formation existante")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraining(@PathVariable Long id) {
        trainingService.delete(id);
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Désactiver une formation existante")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateTraining(@PathVariable Long id) {
        trainingService.deactivate(id);
    }

}
