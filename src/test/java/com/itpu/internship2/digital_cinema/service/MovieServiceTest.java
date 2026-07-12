package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.MovieMapper;
import com.itpu.internship2.digital_cinema.repository.MovieRepository;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("MovieService unit tests")
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieService movieService;

    // ─── create ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("create() maps DTO to entity, saves and returns GetMovieDTO")
    void create_success() {
        // Arrange
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();
        MovieEntity entity = TestDataFactory.movieEntity();
        GetMovieDTO expected = TestDataFactory.getMovieDTO();

        when(movieMapper.buildEntityFromDTO(dto)).thenReturn(entity);
        when(movieRepository.save(entity)).thenReturn(entity);
        when(movieMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetMovieDTO result = movieService.create(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("The Matrix");
        verify(movieMapper).buildEntityFromDTO(dto);
        verify(movieRepository).save(entity);
        verify(movieMapper).buildDTOFromEntity(entity);
    }

    // ─── getAll ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getAll() returns paginated GetMovieDTOs")
    void getAll_returnsPaginatedResults() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        MovieEntity entity = TestDataFactory.movieEntity();
        GetMovieDTO dto = TestDataFactory.getMovieDTO();
        Page<MovieEntity> entityPage = new PageImpl<>(List.of(entity), pageable, 1);

        when(movieRepository.findAll(pageable)).thenReturn(entityPage);
        when(movieMapper.buildDTOFromEntity(entity)).thenReturn(dto);

        // Act
        Page<GetMovieDTO> result = movieService.getAll(pageable);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("The Matrix");
        verify(movieRepository).findAll(pageable);
    }

    @Test
    @DisplayName("getAll() returns empty page when no movies exist")
    void getAll_emptyPage() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        when(movieRepository.findAll(pageable)).thenReturn(Page.empty(pageable));

        // Act
        Page<GetMovieDTO> result = movieService.getAll(pageable);

        // Assert
        assertThat(result).isEmpty();
    }

    // ─── getById ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getById() returns DTO when movie exists")
    void getById_found() {
        // Arrange
        MovieEntity entity = TestDataFactory.movieEntity();
        GetMovieDTO expected = TestDataFactory.getMovieDTO();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(movieMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetMovieDTO result = movieService.getById(1L);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("The Matrix");
    }

    @Test
    @DisplayName("getById() throws ResourceNotFoundException when movie not found")
    void getById_notFound_throwsException() {
        // Arrange
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> movieService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ─── update ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("update() updates entity and returns updated DTO")
    void update_success() {
        // Arrange
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();
        MovieEntity entity = TestDataFactory.movieEntity();
        GetMovieDTO expected = TestDataFactory.getMovieDTO();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(movieRepository.save(entity)).thenReturn(entity);
        when(movieMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetMovieDTO result = movieService.update(1L, dto);

        // Assert
        assertThat(result).isNotNull();
        verify(movieMapper).updateEntityFromDTO(entity, dto);
        verify(movieRepository).save(entity);
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when movie not found")
    void update_notFound_throwsException() {
        // Arrange
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> movieService.update(99L, TestDataFactory.saveMovieDTO()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(movieRepository, never()).save(any());
    }

    // ─── delete ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("delete() deletes movie when found")
    void delete_success() {
        // Arrange
        MovieEntity entity = TestDataFactory.movieEntity();
        when(movieRepository.findById(1L)).thenReturn(Optional.of(entity));

        // Act
        movieService.delete(1L);

        // Assert
        verify(movieRepository).delete(entity);
    }

    @Test
    @DisplayName("delete() throws ResourceNotFoundException when movie not found")
    void delete_notFound_throwsException() {
        // Arrange
        when(movieRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> movieService.delete(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(movieRepository, never()).delete(any());
    }
}
