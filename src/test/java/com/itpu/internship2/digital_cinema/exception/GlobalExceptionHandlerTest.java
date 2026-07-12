package com.itpu.internship2.digital_cinema.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("GlobalExceptionHandler unit tests")
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    // ─── ResourceNotFoundException → 404 ─────────────────────────────────────

    @Test
    @DisplayName("handleResourceNotFoundException() returns 404 with error message")
    void handleResourceNotFoundException_returns404() {
        // Arrange
        ResourceNotFoundException ex = new ResourceNotFoundException("Movie not found with id: 1");

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleResourceNotFoundException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).containsEntry("error", "Movie not found with id: 1");
    }

    // ─── ResourceAlreadyExistsException → 409 ────────────────────────────────

    @Test
    @DisplayName("handleResourceAlreadyExistsException() returns 409 with error message")
    void handleResourceAlreadyExistsException_returns409() {
        // Arrange
        ResourceAlreadyExistsException ex = new ResourceAlreadyExistsException("Resource already exists");

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleResourceAlreadyExistsException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).containsEntry("error", "Resource already exists");
    }

    // ─── BusinessException → 400 ─────────────────────────────────────────────

    @Test
    @DisplayName("handleBusinessException() returns 400 with error message")
    void handleBusinessException_returns400() {
        // Arrange
        BusinessException ex = new BusinessException("Business rule violated");

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleBusinessException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsEntry("error", "Business rule violated");
    }

    // ─── ValidationException → 400 ───────────────────────────────────────────

    @Test
    @DisplayName("handleValidationException() returns 400 with error message")
    void handleValidationException_returns400() {
        // Arrange
        ValidationException ex = new ValidationException("Validation failed for field X");

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleValidationException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsEntry("error", "Validation failed for field X");
    }

    // ─── MethodArgumentNotValidException → 400 ────────────────────────────────

    @Test
    @DisplayName("handleMethodArgumentNotValidException() returns 400 with field-level errors")
    void handleMethodArgumentNotValidException_returns400WithFields() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("saveMovieDTO", "title", "must not be blank");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleMethodArgumentNotValidException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsEntry("title", "must not be blank");
    }

    // ─── ConstraintViolationException → 400 ──────────────────────────────────

    @Test
    @DisplayName("handleConstraintViolationException() returns 400")
    void handleConstraintViolationException_returns400() {
        // Arrange
        ConstraintViolationException ex = new ConstraintViolationException("constraint violation", Set.of());

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleConstraintViolationException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsKey("error");
    }

    // ─── Generic Exception → 500 ──────────────────────────────────────────────

    @Test
    @DisplayName("handleGeneralException() returns 500 with generic message")
    void handleGeneralException_returns500() {
        // Arrange
        Exception ex = new RuntimeException("Something went wrong");

        // Act
        ResponseEntity<Map<String, String>> response = handler.handleGeneralException(ex);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).containsEntry("error", "An unexpected error occurred.");
    }
}
