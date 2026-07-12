package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SessionSeatMapper;
import com.itpu.internship2.digital_cinema.repository.SeatRepository;
import com.itpu.internship2.digital_cinema.repository.SessionRepository;
import com.itpu.internship2.digital_cinema.repository.SessionSeatRepository;
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
@DisplayName("SessionSeatService unit tests")
class SessionSeatServiceTest {

    @Mock private SessionSeatRepository sessionSeatRepository;
    @Mock private SessionRepository sessionRepository;
    @Mock private SeatRepository seatRepository;
    @Mock private SessionSeatMapper sessionSeatMapper;

    @InjectMocks
    private SessionSeatService sessionSeatService;

    // ─── create ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("create() succeeds when session and seat exist")
    void create_success() {
        // Arrange
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        SessionEntity session = TestDataFactory.sessionEntity();
        SeatEntity seat = TestDataFactory.seatEntity();
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        GetSessionSeatDTO expected = TestDataFactory.getSessionSeatDTO();

        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat));
        when(sessionSeatMapper.buildEntityFromDTO(dto, session, seat)).thenReturn(entity);
        when(sessionSeatRepository.save(entity)).thenReturn(entity);
        when(sessionSeatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetSessionSeatDTO result = sessionSeatService.create(dto);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getSessionId()).isEqualTo(1L);
        assertThat(result.getSeatId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when session not found")
    void create_sessionNotFound() {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Session");
        verify(seatRepository, never()).findById(any());
        verify(sessionSeatRepository, never()).save(any());
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when seat not found")
    void create_seatNotFound() {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionEntity()));
        when(seatRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Seat");
        verify(sessionSeatRepository, never()).save(any());
    }

    // ─── getAll ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getAll() returns paginated session seat DTOs")
    void getAll_returnsPaginatedResults() {
        Pageable pageable = PageRequest.of(0, 10);
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        GetSessionSeatDTO dto = TestDataFactory.getSessionSeatDTO();
        Page<SessionSeatEntity> page = new PageImpl<>(List.of(entity), pageable, 1);

        when(sessionSeatRepository.findAll(pageable)).thenReturn(page);
        when(sessionSeatMapper.buildDTOFromEntity(entity)).thenReturn(dto);

        Page<GetSessionSeatDTO> result = sessionSeatService.getAll(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getCustomerName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("getAll() returns empty page when no session seats exist")
    void getAll_empty() {
        Pageable pageable = PageRequest.of(0, 10);
        when(sessionSeatRepository.findAll(pageable)).thenReturn(Page.empty(pageable));
        assertThat(sessionSeatService.getAll(pageable)).isEmpty();
    }

    // ─── getById ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getById() returns DTO when session seat found")
    void getById_found() {
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        GetSessionSeatDTO expected = TestDataFactory.getSessionSeatDTO();

        when(sessionSeatRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(sessionSeatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        GetSessionSeatDTO result = sessionSeatService.getById(1L);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getById() throws ResourceNotFoundException when not found")
    void getById_notFound() {
        when(sessionSeatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ─── update ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("update() succeeds when all FK entities exist")
    void update_success() {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        SessionEntity session = TestDataFactory.sessionEntity();
        SeatEntity seat = TestDataFactory.seatEntity();
        GetSessionSeatDTO expected = TestDataFactory.getSessionSeatDTO();

        when(sessionSeatRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat));
        when(sessionSeatRepository.save(entity)).thenReturn(entity);
        when(sessionSeatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        GetSessionSeatDTO result = sessionSeatService.update(1L, dto);

        assertThat(result).isNotNull();
        verify(sessionSeatMapper).updateEntityFromDTO(entity, dto, session, seat);
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when session seat not found")
    void update_sessionSeatNotFound() {
        when(sessionSeatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.update(99L, TestDataFactory.saveSessionSeatDTO()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(sessionSeatRepository, never()).save(any());
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when session not found")
    void update_sessionNotFound() {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        when(sessionSeatRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionSeatEntity()));
        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.update(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Session");
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when seat not found")
    void update_seatNotFound() {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        when(sessionSeatRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionSeatEntity()));
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.sessionEntity()));
        when(seatRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.update(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Seat");
    }

    // ─── delete ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("delete() deletes session seat when found")
    void delete_success() {
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        when(sessionSeatRepository.findById(1L)).thenReturn(Optional.of(entity));

        sessionSeatService.delete(1L);

        verify(sessionSeatRepository).delete(entity);
    }

    @Test
    @DisplayName("delete() throws ResourceNotFoundException when not found")
    void delete_notFound() {
        when(sessionSeatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionSeatService.delete(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(sessionSeatRepository, never()).delete(any());
    }
}
