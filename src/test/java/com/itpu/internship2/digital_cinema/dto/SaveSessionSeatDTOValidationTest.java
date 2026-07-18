package com.itpu.internship2.digital_cinema.dto;

import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SaveSessionSeatDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSessionId(1L);
        dto.setSeatId(1L);
        dto.setIsAvailable("true");
        dto.setCustomerName("John Doe");
        dto.setContact("+1234567890");

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenSessionIdIsNull_thenViolation() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSeatId(1L);

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenSeatIdIsNull_thenViolation() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSessionId(1L);

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenIsAvailableIsTooLong_thenViolation() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSessionId(1L);
        dto.setSeatId(1L);
        dto.setIsAvailable("A".repeat(51)); // Max is 50

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenCustomerNameIsTooLong_thenViolation() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSessionId(1L);
        dto.setSeatId(1L);
        dto.setCustomerName("A".repeat(256)); // Max is 255

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenContactIsTooLong_thenViolation() {
        SaveSessionSeatDTO dto = new SaveSessionSeatDTO();
        dto.setSessionId(1L);
        dto.setSeatId(1L);
        dto.setContact("A".repeat(256)); // Max is 255

        Set<ConstraintViolation<SaveSessionSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }
}
