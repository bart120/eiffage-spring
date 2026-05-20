package com.formation.hello_spring.dto;

import java.time.LocalDate;

import com.formation.hello_spring.model.Session;

public record SessionResponse(
        long id,
        LocalDate startDate,
        LocalDate endDate,
        String location,
        int maxParticipants,
        boolean active,
        long trainingId,
        String trainingTitle) {
    public static SessionResponse fromModel(Session session) {
        return new SessionResponse(
                session.getId(),
                session.getStartDate(),
                session.getEndDate(),
                session.getLocation(),
                session.getMaxParticipants(),
                session.isActive(),
                session.getTraining().getId(),
                session.getTraining().getTitle());
    }
}
