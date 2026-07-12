package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.util.MovieFormat;
import com.itpu.internship2.digital_cinema.util.MovieLang;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SessionMapper unit tests")
class SessionMapperTest {

    private final SessionMapper mapper = new SessionMapper();

    @Test
    @DisplayName("buildEntityFromDTO() maps all fields including movie and hall references")
    void buildEntityFromDTO_mapsAllFields() {
        // Arrange
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        MovieEntity movie = TestDataFactory.movieEntity();
        HallEntity hall = TestDataFactory.hallEntity();

        // Act
        SessionEntity entity = mapper.buildEntityFromDTO(dto, movie, hall);

        // Assert
        assertThat(entity.getMovie()).isSameAs(movie);
        assertThat(entity.getHall()).isSameAs(hall);
        assertThat(entity.getTitle()).isEqualTo("Evening Premiere");
        assertThat(entity.getDate()).isEqualTo(LocalDate.of(2026, 7, 20));
        assertThat(entity.getTime()).isEqualTo(LocalTime.of(19, 0));
        assertThat(entity.getLanguage()).isEqualTo(MovieLang.ENGLISH);
        assertThat(entity.getFormat()).isEqualTo(MovieFormat.TWO_D);
        assertThat(entity.getId()).isNull();
    }

    @Test
    @DisplayName("buildDTOFromEntity() maps all fields including FK IDs")
    void buildDTOFromEntity_mapsAllFields() {
        // Arrange
        SessionEntity entity = TestDataFactory.sessionEntity();

        // Act
        GetSessionDTO dto = mapper.buildDTOFromEntity(entity);

        // Assert
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getMovieId()).isEqualTo(1L);
        assertThat(dto.getHallId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("Evening Premiere");
        assertThat(dto.getDate()).isEqualTo(LocalDate.of(2026, 7, 20));
        assertThat(dto.getTime()).isEqualTo(LocalTime.of(19, 0));
        assertThat(dto.getLanguage()).isEqualTo(MovieLang.ENGLISH);
        assertThat(dto.getFormat()).isEqualTo(MovieFormat.TWO_D);
    }

    @Test
    @DisplayName("updateEntityFromDTO() updates all mutable fields and keeps ID")
    void updateEntityFromDTO_updatesFields() {
        // Arrange
        SessionEntity entity = TestDataFactory.sessionEntity();
        MovieEntity newMovie = MovieEntity.builder().id(2L).title("Inception").build();
        HallEntity newHall = HallEntity.builder().id(2L).name("Hall B").build();
        SaveSessionDTO dto = SaveSessionDTO.builder()
                .movieId(2L)
                .hallId(2L)
                .title("Matinee")
                .date(LocalDate.of(2026, 8, 1))
                .time(LocalTime.of(11, 0))
                .language(MovieLang.RUSSIAN)
                .format(MovieFormat.IMAX)
                .build();

        // Act
        mapper.updateEntityFromDTO(entity, dto, newMovie, newHall);

        // Assert
        assertThat(entity.getId()).isEqualTo(1L); // unchanged
        assertThat(entity.getMovie()).isSameAs(newMovie);
        assertThat(entity.getHall()).isSameAs(newHall);
        assertThat(entity.getTitle()).isEqualTo("Matinee");
        assertThat(entity.getDate()).isEqualTo(LocalDate.of(2026, 8, 1));
        assertThat(entity.getTime()).isEqualTo(LocalTime.of(11, 0));
        assertThat(entity.getLanguage()).isEqualTo(MovieLang.RUSSIAN);
        assertThat(entity.getFormat()).isEqualTo(MovieFormat.IMAX);
    }
}
