package com.formation.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.hello_spring.model.Training;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByTitleIgnoreCase(String title); // ==> where lower(title) = lower(?)

    List<Training> findByTitleContainingIgnoreCase(String title); // ==> where lower(title) like lower('%?%')

    List<Training> findByActiveTrue(); // ==> where active = true

    @Query("SELECT t FROM Training t WHERE t.id = :id")
    Training findByIdPerso(Long id);
}
