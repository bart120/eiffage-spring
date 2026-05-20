package com.formation.hello_spring.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.formation.hello_spring.repository.TrainingRepository;

@Component
public class ActiveTrainingHealthIndicator implements HealthIndicator {

    private final TrainingRepository trainingRepository;

    public ActiveTrainingHealthIndicator(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Health health() {
        long activeTrainings = trainingRepository.countByActiveTrue();
        if (activeTrainings > 5) {
            return Health.up().withDetail("activeTrainings", activeTrainings)
                    .withDetail("message", "Des formations sont disponibles.").build();
        } else {
            return Health.down().withDetail("activeTrainings", activeTrainings)
                    .withDetail("message", "Pas assez de formations en base.").build();
        }
    }

}
