package com.formation.hello_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.formation.hello_spring.service.TrainingService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    private final TrainingService trainingService;

    public HelloController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring!";
    }

    @GetMapping("/training/name")
    public String getTrainingName() {
        return trainingService.getTrainingName();
    }
}
