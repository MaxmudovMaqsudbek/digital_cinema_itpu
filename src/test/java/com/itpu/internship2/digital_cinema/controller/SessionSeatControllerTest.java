package com.itpu.internship2.digital_cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.service.SessionSeatService;
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

@WebMvcTest(SessionSeatController.class)
@DisplayName("SessionSeatController integration tests (MockMvc)")
class SessionSeatControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockitoBean  private SessionSeatService sessionSeatService;

    // ─── POST /session-seats ──────────────────────────────────────────────────

    @Test
    @DisplayName("POST /session-seats returns 201 and body on success")
    void create_returns201() throws Exception {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        GetSessionSeatDTO response = TestDataFactory.getSessionSeatDTO();
        when(sessionSeatService.create(any(SaveSessionSeatDTO.class))).thenReturn(response);

        mockMvc.perform(post("/session-seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sessionId").value(1))
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    @DisplayName("POST /session-seats returns 400 when sessionId is null")
    void create_nullSessionId_returns400() throws Exception {
        SaveSessionSeatDTO dto = SaveSessionSeatDTO.builder()
                .sessionId(null)  // @NotNull violation
                .seatId(1L)
                .build();

        mockMvc.perform(post("/session-seats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // ─── GET /session-seats ───────────────────────────────────────────────────

    @Test
    @DisplayName("GET /session-seats returns 200 with paginated result")
    void getAll_returns200() throws Exception {
        Page<GetSessionSeatDTO> page = new PageImpl<>(List.of(TestDataFactory.getSessionSeatDTO()));
        when(sessionSeatService.getAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/session-seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    // ─── GET /session-seats/{id} ──────────────────────────────────────────────

    @Test
    @DisplayName("GET /session-seats/{id} returns 200 when found")
    void getById_found_returns200() throws Exception {
        when(sessionSeatService.getById(1L)).thenReturn(TestDataFactory.getSessionSeatDTO());

        mockMvc.perform(get("/session-seats/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.contact").value("+998901234567"));
    }

    @Test
    @DisplayName("GET /session-seats/{id} returns 404 when not found")
    void getById_notFound_returns404() throws Exception {
        when(sessionSeatService.getById(99L))
                .thenThrow(new ResourceNotFoundException("Session Seat not found with id: 99"));

        mockMvc.perform(get("/session-seats/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Session Seat not found with id: 99"));
    }

    // ─── PUT /session-seats/{id} ──────────────────────────────────────────────

    @Test
    @DisplayName("PUT /session-seats/{id} returns 200 with updated body")
    void update_returns200() throws Exception {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        GetSessionSeatDTO response = TestDataFactory.getSessionSeatDTO();
        when(sessionSeatService.update(eq(1L), any(SaveSessionSeatDTO.class))).thenReturn(response);

        mockMvc.perform(put("/session-seats/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    @DisplayName("PUT /session-seats/{id} returns 404 when not found")
    void update_notFound_returns404() throws Exception {
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        when(sessionSeatService.update(eq(99L), any(SaveSessionSeatDTO.class)))
                .thenThrow(new ResourceNotFoundException("Session Seat not found with id: 99"));

        mockMvc.perform(put("/session-seats/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    // ─── DELETE /session-seats/{id} ───────────────────────────────────────────

    @Test
    @DisplayName("DELETE /session-seats/{id} returns 204 on success")
    void delete_returns204() throws Exception {
        doNothing().when(sessionSeatService).delete(1L);

        mockMvc.perform(delete("/session-seats/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /session-seats/{id} returns 404 when not found")
    void delete_notFound_returns404() throws Exception {
        doThrow(new ResourceNotFoundException("Session Seat not found with id: 99"))
                .when(sessionSeatService).delete(99L);

        mockMvc.perform(delete("/session-seats/99"))
                .andExpect(status().isNotFound());
    }
}
