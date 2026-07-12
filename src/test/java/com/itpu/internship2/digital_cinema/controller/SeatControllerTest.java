package com.itpu.internship2.digital_cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.service.SeatService;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeatController.class)
@DisplayName("SeatController integration tests (MockMvc)")
class SeatControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockitoBean  private SeatService seatService;

    // ─── POST /seats ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /seats returns 201 and body on success")
    void create_returns201() throws Exception {
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        GetSeatDTO response = TestDataFactory.getSeatDTO();
        when(seatService.create(any(SaveSeatDTO.class))).thenReturn(response);

        mockMvc.perform(post("/seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.row").value(5))
                .andExpect(jsonPath("$.number").value(12));
    }

    @Test
    @DisplayName("POST /seats returns 400 when hallId is null")
    void create_nullHallId_returns400() throws Exception {
        SaveSeatDTO dto = SaveSeatDTO.builder()
                .hallId(null)           // @NotNull violation
                .priceCategoryId(1L)
                .row(1)
                .number(1)
                .build();

        mockMvc.perform(post("/seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // ─── GET /seats ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /seats returns 200 with paginated result")
    void getAll_returns200() throws Exception {
        Page<GetSeatDTO> page = new PageImpl<>(List.of(TestDataFactory.getSeatDTO()));
        when(seatService.getAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].row").value(5))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    // ─── GET /seats/{id} ─────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /seats/{id} returns 200 when seat found")
    void getById_found_returns200() throws Exception {
        when(seatService.getById(1L)).thenReturn(TestDataFactory.getSeatDTO());

        mockMvc.perform(get("/seats/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.comment").value("VIP Seat"));
    }

    @Test
    @DisplayName("GET /seats/{id} returns 404 when seat not found")
    void getById_notFound_returns404() throws Exception {
        when(seatService.getById(99L)).thenThrow(new ResourceNotFoundException("Seat not found with id: 99"));

        mockMvc.perform(get("/seats/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Seat not found with id: 99"));
    }

    // ─── PUT /seats/{id} ─────────────────────────────────────────────────────

    @Test
    @DisplayName("PUT /seats/{id} returns 200 with updated body")
    void update_returns200() throws Exception {
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        GetSeatDTO response = TestDataFactory.getSeatDTO();
        when(seatService.update(eq(1L), any(SaveSeatDTO.class))).thenReturn(response);

        mockMvc.perform(put("/seats/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.row").value(5));
    }

    @Test
    @DisplayName("PUT /seats/{id} returns 404 when seat not found")
    void update_notFound_returns404() throws Exception {
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        when(seatService.update(eq(99L), any(SaveSeatDTO.class)))
                .thenThrow(new ResourceNotFoundException("Seat not found with id: 99"));

        mockMvc.perform(put("/seats/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    // ─── DELETE /seats/{id} ───────────────────────────────────────────────────

    @Test
    @DisplayName("DELETE /seats/{id} returns 204 on success")
    void delete_returns204() throws Exception {
        doNothing().when(seatService).delete(1L);

        mockMvc.perform(delete("/seats/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /seats/{id} returns 404 when seat not found")
    void delete_notFound_returns404() throws Exception {
        doThrow(new ResourceNotFoundException("Seat not found with id: 99"))
                .when(seatService).delete(99L);

        mockMvc.perform(delete("/seats/99"))
                .andExpect(status().isNotFound());
    }
}
