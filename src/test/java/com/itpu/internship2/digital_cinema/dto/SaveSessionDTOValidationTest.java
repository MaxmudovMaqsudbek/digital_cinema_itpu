package com.itpu.internship2.digital_cinema.dto;

import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SaveSessionDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setMovieId(1L);
        dto.setHallId(1L);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(12, 0));
        dto.setTitle("Afternoon Show");

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenMovieIdIsNull_thenViolation() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setHallId(1L);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(12, 0));

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenHallIdIsNull_thenViolation() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setMovieId(1L);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(12, 0));

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenDateIsNull_thenViolation() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setMovieId(1L);
        dto.setHallId(1L);
        dto.setTime(LocalTime.of(12, 0));

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenTimeIsNull_thenViolation() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setMovieId(1L);
        dto.setHallId(1L);
        dto.setDate(LocalDate.now());

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenTitleIsTooLong_thenViolation() {
        SaveSessionDTO dto = new SaveSessionDTO();
        dto.setMovieId(1L);
        dto.setHallId(1L);
        dto.setDate(LocalDate.now());
        dto.setTime(LocalTime.of(12, 0));
        dto.setTitle("A".repeat(501));

        Set<ConstraintViolation<SaveSessionDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }
}
