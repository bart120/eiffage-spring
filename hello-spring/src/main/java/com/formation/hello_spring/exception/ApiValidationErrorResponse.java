package com.formation.hello_spring.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors) {
}
