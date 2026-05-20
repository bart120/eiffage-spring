package com.formation.hello_spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.exception.ResourceNotFoundException;
import com.formation.hello_spring.model.Training;
import com.formation.hello_spring.repository.TrainingRepository;

public class TrainingServiceTest {

    private final TrainingRepository trainingRepository = Mockito.mock(TrainingRepository.class);

    private final TrainingService trainingService = new TrainingService(trainingRepository);

    @Test
    void shouldFindTrainingById() {
        Training training = new Training("Java", "Formation Java", 5);

        Mockito.when(trainingRepository.findById(1L)).thenReturn(Optional.of(training));

        TrainingResponse response = trainingService.findById(1L);

        assertEquals("Formation Java", response.description());
        assertEquals("Java", response.title());
        assertEquals(5, response.duration());
    }

    @Test
    void shouldThrowExceptionWhenTrainingNotFound() {
        Mockito.when(trainingRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> trainingService.findById(999L));
    }
}
