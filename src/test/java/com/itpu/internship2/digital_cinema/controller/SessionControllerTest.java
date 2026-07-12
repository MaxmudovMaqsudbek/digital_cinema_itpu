package com.itpu.internship2.digital_cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.service.SessionService;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
@DisplayName("SessionController integration tests (MockMvc)")
class SessionControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockitoBean  private SessionService sessionService;

    // ─── POST /sessions ───────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /sessions returns 201 and body on success")
    void create_returns201() throws Exception {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        GetSessionDTO response = TestDataFactory.getSessionDTO();
        when(sessionService.create(any(SaveSessionDTO.class))).thenReturn(response);

        mockMvc.perform(post("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.movieId").value(1))
                .andExpect(jsonPath("$.title").value("Evening Premiere"));
    }

    @Test
    @DisplayName("POST /sessions returns 400 when movieId is null")
    void create_nullMovieId_returns400() throws Exception {
        SaveSessionDTO dto = SaveSessionDTO.builder()
                .movieId(null)  // @NotNull violation
                .hallId(1L)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        mockMvc.perform(post("/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // ─── GET /sessions ────────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /sessions returns 200 with paginated result")
    void getAll_returns200() throws Exception {
        Page<GetSessionDTO> page = new PageImpl<>(List.of(TestDataFactory.getSessionDTO()));
        when(sessionService.getAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Evening Premiere"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    // ─── GET /sessions/{id} ───────────────────────────────────────────────────

    @Test
    @DisplayName("GET /sessions/{id} returns 200 when session found")
    void getById_found_returns200() throws Exception {
        when(sessionService.getById(1L)).thenReturn(TestDataFactory.getSessionDTO());

        mockMvc.perform(get("/sessions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hallId").value(1));
    }

    @Test
    @DisplayName("GET /sessions/{id} returns 404 when session not found")
    void getById_notFound_returns404() throws Exception {
        when(sessionService.getById(99L))
                .thenThrow(new ResourceNotFoundException("Session not found with id: 99"));

        mockMvc.perform(get("/sessions/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Session not found with id: 99"));
    }

    // ─── PUT /sessions/{id} ───────────────────────────────────────────────────

    @Test
    @DisplayName("PUT /sessions/{id} returns 200 with updated body")
    void update_returns200() throws Exception {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        GetSessionDTO response = TestDataFactory.getSessionDTO();
        when(sessionService.update(eq(1L), any(SaveSessionDTO.class))).thenReturn(response);

        mockMvc.perform(put("/sessions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Evening Premiere"));
    }

    @Test
    @DisplayName("PUT /sessions/{id} returns 404 when session not found")
    void update_notFound_returns404() throws Exception {
        SaveSessionDTO dto = TestDataFactory.saveSessionDTO();
        when(sessionService.update(eq(99L), any(SaveSessionDTO.class)))
                .thenThrow(new ResourceNotFoundException("Session not found with id: 99"));

        mockMvc.perform(put("/sessions/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    // ─── DELETE /sessions/{id} ────────────────────────────────────────────────

    @Test
    @DisplayName("DELETE /sessions/{id} returns 204 on success")
    void delete_returns204() throws Exception {
        doNothing().when(sessionService).delete(1L);

        mockMvc.perform(delete("/sessions/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /sessions/{id} returns 404 when session not found")
    void delete_notFound_returns404() throws Exception {
        doThrow(new ResourceNotFoundException("Session not found with id: 99"))
                .when(sessionService).delete(99L);

        mockMvc.perform(delete("/sessions/99"))
                .andExpect(status().isNotFound());
    }
}
