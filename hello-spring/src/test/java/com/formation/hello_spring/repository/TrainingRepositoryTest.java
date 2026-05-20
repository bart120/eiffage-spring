package com.formation.hello_spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.formation.hello_spring.model.Training;

@DataJpaTest
public class TrainingRepositoryTest {

    @Autowired
    private TrainingRepository trainingRepository;

    @Test
    void shouldFindTrainingByTitleIgnoreCase() {
        Training training = new Training("Java", "Formation Java", 5);
        trainingRepository.save(training);

        List<Training> trainings = trainingRepository.findByTitleIgnoreCase("java");

        assertEquals(1, trainings.size());
        assertEquals("Formation Java", trainings.get(0).getDescription());

    }
}
