package com.formation.hello_spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @Value("${training.api.title:API de Formation}")
    private String apiTitle;

    @Value("${training.api.version:1.0.0}")
    private String apiVersion;

    @GetMapping("/")
    public String root() {
        return "API Training en formation";
    }

    @GetMapping("/api/info")
    public Map<String, Object> apiInfo() {
        return Map.of(
                "title", apiTitle,
                "version", apiVersion);
    }
}
