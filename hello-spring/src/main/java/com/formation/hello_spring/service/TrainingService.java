package com.formation.hello_spring.service;

import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    public String getTrainingName() {
        return "Formation Spring Boot.";
    }

    public String getTrainingDuration() {
        return "La formation dure 2 jours.";
    }
}
