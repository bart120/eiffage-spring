package com.formation.hello_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.hello_spring.service.SessionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/sessions")
@Tag(name = "Sessions", description = "Gestion des sessions de formation")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public java.util.List<com.formation.hello_spring.dto.SessionResponse> getAllSessions() {
        return sessionService.findAll();
    }

    @GetMapping("/{id}")
    public com.formation.hello_spring.dto.SessionResponse getSessionById(@PathVariable Long id) {
        return sessionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public com.formation.hello_spring.dto.SessionResponse createSession(
            @Valid @RequestBody com.formation.hello_spring.dto.SessionCreateRequest sessionRequest) {
        return sessionService.create(sessionRequest);
    }

    @PutMapping("/{id}")
    public com.formation.hello_spring.dto.SessionResponse updateSession(@Valid @PathVariable Long id,
            @Valid @RequestBody com.formation.hello_spring.dto.SessionUpdateRequest sessionRequest) {
        return sessionService.update(id, sessionRequest);
    }

    @GetMapping("/upcoming")
    public java.util.List<com.formation.hello_spring.dto.SessionResponse> getUpcomingSessions() {
        return sessionService.findUpcomingSessions();
    }

    @GetMapping("/search")
    public java.util.List<com.formation.hello_spring.dto.SessionResponse> searchSessionsByTrainingTitle(
            @RequestParam String title) {
        return sessionService.findSessionsByTrainingTitle(title);
    }

    @GetMapping("/dates/search")
    public java.util.List<com.formation.hello_spring.dto.SessionResponse> searchSessionsByDates(
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return sessionService.findSessionsByDates(startDate, endDate);
    }
}
