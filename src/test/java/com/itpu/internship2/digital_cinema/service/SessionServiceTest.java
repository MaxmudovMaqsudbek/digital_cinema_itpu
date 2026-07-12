package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SessionMapper;
import com.itpu.internship2.digital_cinema.repository.HallRepository;
import com.itpu.internship2.digital_cinema.repository.MovieRepository;
import com.itpu.internship2.digital_cinema.repository.SessionRepository;
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
@DisplayName("SessionService unit tests")
class SessionServiceTest {

    @Mock private SessionRepository sessionRepository;
    @Mock private MovieRepository movieRepository;
    @Mock private HallRepository hallRepository;
    @Mock private SessionMapper sessionMapper;

    @InjectMocks
    private SessionService sessionService;

    // ─── create ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("create() succeeds when movie and hall exist")
    void create_success() {
        // Arrange
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        MovieEntity movie = TestDataFactory.movieEntity();
        HallEntity hall = TestDataFactory.hallEntity();
        SessionEntity entity = TestDataFactory.sessionEntity();
        GetSessionDTO expected = TestDataFactory.getSessionDTO();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));
        when(sessionMapper.buildEntityFromDTO(dto, movie, hall)).thenReturn(entity);
        when(sessionRepository.save(entity)).thenReturn(entity);
        when(sessionMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetSessionDTO result = sessionService.create(dto);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getMovieId()).isEqualTo(1L);
        assertThat(result.getHallId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when movie not found")
    void create_movieNotFound() {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Movie");
        verify(hallRepository, never()).findById(any());
        verify(sessionRepository, never()).save(any());
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when hall not found")
    void create_hallNotFound() {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        when(movieRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.movieEntity()));
        when(hallRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Hall");
        verify(sessionRepository, never()).save(any());
    }

    // ─── getAll ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getAll() returns paginated session DTOs")
    void getAll_returnsPaginatedResults() {
        Pageable pageable = PageRequest.of(0, 10);
        SessionEntity entity = TestDataFactory.sessionEntity();
        GetSessionDTO dto = TestDataFactory.getSessionDTO();
        Page<SessionEntity> page = new PageImpl<>(List.of(entity), pageable, 1);

        when(sessionRepository.findAll(pageable)).thenReturn(page);
        when(sessionMapper.buildDTOFromEntity(entity)).thenReturn(dto);

        Page<GetSessionDTO> result = sessionService.getAll(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Evening Premiere");
    }

    @Test
    @DisplayName("getAll() returns empty page when no sessions exist")
    void getAll_empty() {
        Pageable pageable = PageRequest.of(0, 10);
        when(sessionRepository.findAll(pageable)).thenReturn(Page.empty(pageable));
        assertThat(sessionService.getAll(pageable)).isEmpty();
    }

    // ─── getById ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getById() returns DTO when session found")
    void getById_found() {
        SessionEntity entity = TestDataFactory.sessionEntity();
        GetSessionDTO expected = TestDataFactory.getSessionDTO();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(sessionMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        GetSessionDTO result = sessionService.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getById() throws ResourceNotFoundException when session not found")
    void getById_notFound() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ─── update ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("update() succeeds when session, movie and hall all exist")
    void update_success() {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        SessionEntity entity = TestDataFactory.sessionEntity();
        MovieEntity movie = TestDataFactory.movieEntity();
        HallEntity hall = TestDataFactory.hallEntity();
        GetSessionDTO expected = TestDataFactory.getSessionDTO();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));
        when(sessionRepository.save(entity)).thenReturn(entity);
        when(sessionMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        GetSessionDTO result = sessionService.update(1L, dto);

        assertThat(result).isNotNull();
        verify(sessionMapper).updateEntityFromDTO(entity, dto, movie, hall);
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when session not found")
    void update_sessionNotFound() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.update(99L, TestDataFactory.saveSessionDTO()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(sessionRepository, never()).save(any());
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when movie not found")
    void update_movieNotFound() {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionEntity()));
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.update(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Movie");
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when hall not found")
    void update_hallNotFound() {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionEntity()));
        when(movieRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.movieEntity()));
        when(hallRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.update(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Hall");
    }

    // ─── delete ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("delete() deletes session when found")
    void delete_success() {
        SessionEntity entity = TestDataFactory.sessionEntity();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(entity));

        sessionService.delete(1L);

        verify(sessionRepository).delete(entity);
    }

    @Test
    @DisplayName("delete() throws ResourceNotFoundException when session not found")
    void delete_notFound() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.delete(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(sessionRepository, never()).delete(any());
    }
}
