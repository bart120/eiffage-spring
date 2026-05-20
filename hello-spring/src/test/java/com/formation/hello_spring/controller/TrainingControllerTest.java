package com.formation.hello_spring.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.formation.hello_spring.dto.TrainingResponse;
import com.formation.hello_spring.exception.ResourceNotFoundException;
import com.formation.hello_spring.service.TrainingService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(TrainingController.class)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class TrainingControllerTest {

    @MockitoBean
    private TrainingService trainingService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(2)
    void shouldReturnTrainings() throws Exception {
        when(trainingService.findAll()).thenReturn(List.of(
                new TrainingResponse(1L, "Java", "Formation Java", 5, true),
                new TrainingResponse(2L, "Spring", "Formation Spring", 3, true)));

        mockMvc.perform(get("/api/trainings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Java"))
                .andExpect(jsonPath("$[1].title").value("Spring"));
    }

    @Test
    @Order(1)
    void shouldReturnTrainingById() throws Exception {
        when(trainingService.findById(1L)).thenReturn(
                new TrainingResponse(1L, "Java", "Formation Java", 5, true));

        mockMvc.perform(get("/api/trainings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java"))
                .andExpect(jsonPath("$.description").value("Formation Java"))
                .andExpect(jsonPath("$.duration").value(5))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    @Order(3)
    void shouldReturnNotFoundWhenTrainingNotFound() throws Exception {
        when(trainingService.findById(999L))
                .thenThrow(new ResourceNotFoundException("Formation introuvable avec l'id : 999"));

        mockMvc.perform(get("/api/trainings/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Formation introuvable avec l'id : 999"));
    }

}
