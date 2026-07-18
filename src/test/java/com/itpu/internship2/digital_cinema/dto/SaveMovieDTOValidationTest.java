package com.itpu.internship2.digital_cinema.dto;

import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SaveMovieDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid Title");
        dto.setDurationMinutes(120);
        dto.setReleaseYear(2023);
        dto.setRating(8.5f);
        dto.setPosterUrl("http://example.com/poster.jpg");

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenTitleIsBlank_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("");
        dto.setDurationMinutes(120);

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must not be blank");
    }

    @Test
    void whenTitleIsTooLong_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("A".repeat(501)); // max is 500
        
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenDurationIsNegative_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid Title");
        dto.setDurationMinutes(-10);

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("must be greater than 0");
    }
    
    @Test
    void whenRatingIsNegative_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid Title");
        dto.setRating(-1.0f); // Min is 0

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenRatingIsTooHigh_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid Title");
        dto.setRating(11.0f); // Max is 10

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenReleaseYearIsTooOld_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid Title");
        dto.setReleaseYear(1899); // Min is 1900

        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenDurationIsExactlyOne_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(1);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenDurationIsZero_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(0);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenRatingIsExactlyZero_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setRating(0.0f);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenRatingIsExactlyTen_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setRating(10.0f);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenRatingIsJustBelowZero_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setRating(-0.1f);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenRatingIsJustAboveTen_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setRating(10.1f);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenReleaseYearIsExactly1900_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setReleaseYear(1900);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenPosterUrlIsExactly1000Chars_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setPosterUrl("A".repeat(1000));
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenPosterUrlIs1001Chars_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("Valid");
        dto.setDurationMinutes(120);
        dto.setPosterUrl("A".repeat(1001));
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }

    @Test
    void whenTitleIsExactly500Chars_thenNoViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("A".repeat(500));
        dto.setDurationMinutes(120);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenTitleIs501Chars_thenViolation() {
        SaveMovieDTO dto = new SaveMovieDTO();
        dto.setTitle("A".repeat(501));
        dto.setDurationMinutes(120);
        Set<ConstraintViolation<SaveMovieDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
    }
}
