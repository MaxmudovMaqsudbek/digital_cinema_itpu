package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.util.AgeRating;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("MovieMapper unit tests")
class MovieMapperTest {

    private final MovieMapper mapper = new MovieMapper();

    @Test
    @DisplayName("buildEntityFromDTO() maps all fields correctly")
    void buildEntityFromDTO_mapsAllFields() {
        // Arrange
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();

        // Act
        MovieEntity entity = mapper.buildEntityFromDTO(dto);

        // Assert
        assertThat(entity.getTitle()).isEqualTo("The Matrix");
        assertThat(entity.getDurationMinutes()).isEqualTo(136);
        assertThat(entity.getAgeRating()).isEqualTo(AgeRating.R);
        assertThat(entity.getRating()).isEqualTo(8.7f);
        assertThat(entity.getPosterUrl()).isEqualTo("https://example.com/matrix.jpg");
        assertThat(entity.getDescription()).isEqualTo("A sci-fi classic.");
        assertThat(entity.getReleaseYear()).isEqualTo(1999);
        assertThat(entity.getId()).isNull(); // ID is not set from DTO
    }

    @Test
    @DisplayName("buildDTOFromEntity() maps all fields correctly")
    void buildDTOFromEntity_mapsAllFields() {
        // Arrange
        MovieEntity entity = TestDataFactory.movieEntity();

        // Act
        GetMovieDTO dto = mapper.buildDTOFromEntity(entity);

        // Assert
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("The Matrix");
        assertThat(dto.getDurationMinutes()).isEqualTo(136);
        assertThat(dto.getAgeRating()).isEqualTo(AgeRating.R);
        assertThat(dto.getRating()).isEqualTo(8.7f);
        assertThat(dto.getPosterUrl()).isEqualTo("https://example.com/matrix.jpg");
        assertThat(dto.getDescription()).isEqualTo("A sci-fi classic.");
        assertThat(dto.getReleaseYear()).isEqualTo(1999);
    }

    @Test
    @DisplayName("updateEntityFromDTO() updates all mutable fields on existing entity")
    void updateEntityFromDTO_updatesAllFields() {
        // Arrange
        MovieEntity entity = TestDataFactory.movieEntity();
        SaveMovieDTO dto = SaveMovieDTO.builder()
                .title("Inception")
                .durationMinutes(148)
                .ageRating(AgeRating.PG_13)
                .rating(9.0f)
                .posterUrl("https://example.com/inception.jpg")
                .description("Dream within a dream.")
                .releaseYear(2010)
                .build();

        // Act
        mapper.updateEntityFromDTO(entity, dto);

        // Assert
        assertThat(entity.getTitle()).isEqualTo("Inception");
        assertThat(entity.getDurationMinutes()).isEqualTo(148);
        assertThat(entity.getAgeRating()).isEqualTo(AgeRating.PG_13);
        assertThat(entity.getRating()).isEqualTo(9.0f);
        assertThat(entity.getPosterUrl()).isEqualTo("https://example.com/inception.jpg");
        assertThat(entity.getDescription()).isEqualTo("Dream within a dream.");
        assertThat(entity.getReleaseYear()).isEqualTo(2010);
        assertThat(entity.getId()).isEqualTo(1L); // ID must remain unchanged
    }
}
