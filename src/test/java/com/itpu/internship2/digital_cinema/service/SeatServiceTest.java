package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SeatMapper;
import com.itpu.internship2.digital_cinema.repository.HallRepository;
import com.itpu.internship2.digital_cinema.repository.PriceCategoryRepository;
import com.itpu.internship2.digital_cinema.repository.SeatRepository;
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
@DisplayName("SeatService unit tests")
class SeatServiceTest {

    @Mock private SeatRepository seatRepository;
    @Mock private HallRepository hallRepository;
    @Mock private PriceCategoryRepository priceCategoryRepository;
    @Mock private SeatMapper seatMapper;

    @InjectMocks
    private SeatService seatService;

    // ─── create ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("create() succeeds when hall and priceCategory exist")
    void create_success() {
        // Arrange
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        HallEntity hall = TestDataFactory.hallEntity();
        PriceCategoryEntity pc = TestDataFactory.priceCategoryEntity();
        SeatEntity entity = TestDataFactory.seatEntity();
        GetSeatDTO expected = TestDataFactory.getSeatDTO();

        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));
        when(priceCategoryRepository.findById(1L)).thenReturn(Optional.of(pc));
        when(seatMapper.buildEntityFromDTO(dto, hall, pc)).thenReturn(entity);
        when(seatRepository.save(entity)).thenReturn(entity);
        when(seatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetSeatDTO result = seatService.create(dto);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getRow()).isEqualTo(5);
        assertThat(result.getNumber()).isEqualTo(12);
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when hall not found")
    void create_hallNotFound() {
        // Arrange
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        when(hallRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> seatService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Hall");
        verify(priceCategoryRepository, never()).findById(any());
        verify(seatRepository, never()).save(any());
    }

    @Test
    @DisplayName("create() throws ResourceNotFoundException when priceCategory not found")
    void create_priceCategoryNotFound() {
        // Arrange
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        when(hallRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.hallEntity()));
        when(priceCategoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> seatService.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Price Category");
        verify(seatRepository, never()).save(any());
    }

    // ─── getAll ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getAll() returns paginated seat DTOs")
    void getAll_returnsPaginatedResults() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        SeatEntity entity = TestDataFactory.seatEntity();
        GetSeatDTO dto = TestDataFactory.getSeatDTO();
        Page<SeatEntity> entityPage = new PageImpl<>(List.of(entity), pageable, 1);

        when(seatRepository.findAll(pageable)).thenReturn(entityPage);
        when(seatMapper.buildDTOFromEntity(entity)).thenReturn(dto);

        // Act
        Page<GetSeatDTO> result = seatService.getAll(pageable);

        // Assert
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getRow()).isEqualTo(5);
    }

    @Test
    @DisplayName("getAll() returns empty page when no seats exist")
    void getAll_empty() {
        Pageable pageable = PageRequest.of(0, 10);
        when(seatRepository.findAll(pageable)).thenReturn(Page.empty(pageable));
        assertThat(seatService.getAll(pageable)).isEmpty();
    }

    // ─── getById ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getById() returns DTO when seat exists")
    void getById_found() {
        // Arrange
        SeatEntity entity = TestDataFactory.seatEntity();
        GetSeatDTO expected = TestDataFactory.getSeatDTO();

        when(seatRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(seatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetSeatDTO result = seatService.getById(1L);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getById() throws ResourceNotFoundException when seat not found")
    void getById_notFound() {
        when(seatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> seatService.getById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ─── update ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("update() succeeds when seat, hall and priceCategory all exist")
    void update_success() {
        // Arrange
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        SeatEntity entity = TestDataFactory.seatEntity();
        HallEntity hall = TestDataFactory.hallEntity();
        PriceCategoryEntity pc = TestDataFactory.priceCategoryEntity();
        GetSeatDTO expected = TestDataFactory.getSeatDTO();

        when(seatRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));
        when(priceCategoryRepository.findById(1L)).thenReturn(Optional.of(pc));
        when(seatRepository.save(entity)).thenReturn(entity);
        when(seatMapper.buildDTOFromEntity(entity)).thenReturn(expected);

        // Act
        GetSeatDTO result = seatService.update(1L, dto);

        // Assert
        assertThat(result).isNotNull();
        verify(seatMapper).updateEntityFromDTO(entity, dto, hall, pc);
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when seat not found")
    void update_seatNotFound() {
        when(seatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> seatService.update(99L, TestDataFactory.saveSeatDTO()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(seatRepository, never()).save(any());
    }

    @Test
    @DisplayName("update() throws ResourceNotFoundException when hall not found")
    void update_hallNotFound() {
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        when(seatRepository.findById(1L)).thenReturn(Optional.of(TestDataFactory.seatEntity()));
        when(hallRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> seatService.update(1L, dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Hall");
    }

    // ─── delete ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("delete() deletes seat when found")
    void delete_success() {
        SeatEntity entity = TestDataFactory.seatEntity();
        when(seatRepository.findById(1L)).thenReturn(Optional.of(entity));

        seatService.delete(1L);

        verify(seatRepository).delete(entity);
    }

    @Test
    @DisplayName("delete() throws ResourceNotFoundException when seat not found")
    void delete_notFound() {
        when(seatRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> seatService.delete(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
        verify(seatRepository, never()).delete(any());
    }
}
