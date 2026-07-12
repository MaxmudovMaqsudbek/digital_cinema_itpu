package com.itpu.internship2.digital_cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.service.MovieService;
import com.itpu.internship2.digital_cinema.util.AgeRating;
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

@WebMvcTest(MovieController.class)
@DisplayName("MovieController integration tests (MockMvc)")
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MovieService movieService;

    // ─── POST /movies ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("POST /movies returns 201 and body on success")
    void create_returns201() throws Exception {
        // Arrange
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();
        GetMovieDTO response = TestDataFactory.getMovieDTO();
        when(movieService.create(any(SaveMovieDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("The Matrix"));
    }

    @Test
    @DisplayName("POST /movies returns 400 when title is blank")
    void create_blankTitle_returns400() throws Exception {
        // Arrange — title is @NotBlank
        SaveMovieDTO dto = SaveMovieDTO.builder()
                .title("")
                .durationMinutes(90)
                .releaseYear(2000)
                .build();

        // Act & Assert
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    // ─── GET /movies ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /movies returns 200 with paginated result")
    void getAll_returns200() throws Exception {
        // Arrange
        GetMovieDTO dto = TestDataFactory.getMovieDTO();
        Page<GetMovieDTO> page = new PageImpl<>(List.of(dto));
        when(movieService.getAll(any(Pageable.class))).thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("The Matrix"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    // ─── GET /movies/{id} ─────────────────────────────────────────────────────

    @Test
    @DisplayName("GET /movies/{id} returns 200 when movie found")
    void getById_found_returns200() throws Exception {
        // Arrange
        when(movieService.getById(1L)).thenReturn(TestDataFactory.getMovieDTO());

        // Act & Assert
        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.ageRating").value("R"));
    }

    @Test
    @DisplayName("GET /movies/{id} returns 404 when movie not found")
    void getById_notFound_returns404() throws Exception {
        // Arrange
        when(movieService.getById(99L)).thenThrow(new ResourceNotFoundException("Movie not found with id: 99"));

        // Act & Assert
        mockMvc.perform(get("/movies/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Movie not found with id: 99"));
    }

    // ─── PUT /movies/{id} ─────────────────────────────────────────────────────

    @Test
    @DisplayName("PUT /movies/{id} returns 200 with updated body")
    void update_returns200() throws Exception {
        // Arrange
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();
        GetMovieDTO response = TestDataFactory.getMovieDTO();
        when(movieService.update(eq(1L), any(SaveMovieDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Matrix"));
    }

    @Test
    @DisplayName("PUT /movies/{id} returns 404 when movie not found")
    void update_notFound_returns404() throws Exception {
        SaveMovieDTO dto = TestDataFactory.saveMovieDTO();
        when(movieService.update(eq(99L), any(SaveMovieDTO.class)))
                .thenThrow(new ResourceNotFoundException("Movie not found with id: 99"));

        mockMvc.perform(put("/movies/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    // ─── DELETE /movies/{id} ──────────────────────────────────────────────────

    @Test
    @DisplayName("DELETE /movies/{id} returns 204 on success")
    void delete_returns204() throws Exception {
        doNothing().when(movieService).delete(1L);

        mockMvc.perform(delete("/movies/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /movies/{id} returns 404 when movie not found")
    void delete_notFound_returns404() throws Exception {
        doThrow(new ResourceNotFoundException("Movie not found with id: 99"))
                .when(movieService).delete(99L);

        mockMvc.perform(delete("/movies/99"))
                .andExpect(status().isNotFound());
    }
}
