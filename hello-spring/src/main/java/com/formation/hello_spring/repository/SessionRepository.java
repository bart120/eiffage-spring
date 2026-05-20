package com.formation.hello_spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.hello_spring.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByTrainingId(Long trainingId);

    List<Session> findByActiveTrue();

    List<Session> findByStartDateGreaterThanEqual(LocalDate startDate);

    List<Session> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    List<Session> findByLocationContainingIgnoreCase(String location);

    List<Session> findByTrainingTitleContainingIgnoreCase(String trainingTitle);
}
