package com.itpu.internship2.digital_cinema.dto;

import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SaveSeatDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        SaveSeatDTO dto = new SaveSeatDTO();
        dto.setHallId(1L);
        dto.setPriceCategoryId(1L);
        dto.setRow(10);
        dto.setNumber(15);
        dto.setComment("Good view");

        Set<ConstraintViolation<SaveSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenHallIdIsNull_thenViolation() {
        SaveSeatDTO dto = new SaveSeatDTO();
        dto.setPriceCategoryId(1L);
        dto.setRow(10);
        dto.setNumber(15);

        Set<ConstraintViolation<SaveSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenRowIsNegative_thenViolation() {
        SaveSeatDTO dto = new SaveSeatDTO();
        dto.setHallId(1L);
        dto.setPriceCategoryId(1L);
        dto.setRow(-1); // Invalid
        dto.setNumber(15);

        Set<ConstraintViolation<SaveSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenNumberIsZero_thenViolation() {
        SaveSeatDTO dto = new SaveSeatDTO();
        dto.setHallId(1L);
        dto.setPriceCategoryId(1L);
        dto.setRow(10);
        dto.setNumber(0); // Invalid, must be positive

        Set<ConstraintViolation<SaveSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenCommentIsTooLong_thenViolation() {
        SaveSeatDTO dto = new SaveSeatDTO();
        dto.setHallId(1L);
        dto.setPriceCategoryId(1L);
        dto.setRow(10);
        dto.setNumber(15);
        dto.setComment("A".repeat(501));

        Set<ConstraintViolation<SaveSeatDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }
}
