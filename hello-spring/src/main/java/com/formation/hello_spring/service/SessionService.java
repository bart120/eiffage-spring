package com.formation.hello_spring.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.hello_spring.dto.SessionCreateRequest;
import com.formation.hello_spring.dto.SessionResponse;
import com.formation.hello_spring.dto.SessionUpdateRequest;
import com.formation.hello_spring.exception.ResourceNotFoundException;
import com.formation.hello_spring.repository.SessionRepository;
import com.formation.hello_spring.repository.TrainingRepository;

import jakarta.transaction.Transactional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TrainingRepository trainingRepository;

    public SessionService(SessionRepository sessionRepository, TrainingRepository trainingRepository) {
        this.sessionRepository = sessionRepository;
        this.trainingRepository = trainingRepository;
    }

    public List<SessionResponse> findAll() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionResponse::fromModel)
                .toList();
    }

    public SessionResponse findById(Long id) {
        return sessionRepository.findById(id)
                .map(SessionResponse::fromModel)
                .orElseThrow(() -> new ResourceNotFoundException("Session introuvable avec l'id : " + id));
    }

    public SessionResponse create(SessionCreateRequest sessionRequest) {
        var training = trainingRepository.findById(sessionRequest.trainingId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Formation introuvable avec l'id : " + sessionRequest.trainingId()));

        var session = new com.formation.hello_spring.model.Session(
                sessionRequest.startDate(),
                sessionRequest.endDate(),
                sessionRequest.location(),
                sessionRequest.maxParticipants(),
                training);

        var savedSession = sessionRepository.save(session);

        return SessionResponse.fromModel(savedSession);
    }

    @Transactional
    public SessionResponse update(Long id, SessionUpdateRequest sessionRequest) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session introuvable avec l'id : " + id));

        session.setStartDate(sessionRequest.startDate());
        session.setEndDate(sessionRequest.endDate());
        session.setLocation(sessionRequest.location());
        session.setMaxParticipants(sessionRequest.maxParticipants());

        /* var updatedSession = sessionRepository.save(session); */

        return SessionResponse.fromModel(session);
    }

    public List<SessionResponse> findUpcomingSessions() {
        return sessionRepository.findByStartDateGreaterThanEqual(LocalDate.now())
                .stream()
                .map(SessionResponse::fromModel)
                .toList();
    }

    public List<SessionResponse> findSessionsByDates(LocalDate startDate, LocalDate endDate) {
        return sessionRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate)
                .stream()
                .map(SessionResponse::fromModel)
                .toList();
    }

    public List<SessionResponse> findSessionsByTrainingTitle(String title) {
        return sessionRepository.findByTrainingTitleContainingIgnoreCase(title)
                .stream()
                .map(SessionResponse::fromModel)
                .toList();
    }

}
