package com.itpu.internship2.digital_cinema.controller;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Validated
@Tag(name = "Movies", description = "Endpoints for managing movies")
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "Create a new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<GetMovieDTO> create(@Valid @RequestBody SaveMovieDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.create(dto));
    }

    @Operation(summary = "Get all movies with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<GetMovieDTO>> getAll(@Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(movieService.getAll(pageable));
    }

    @Operation(summary = "Get a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetMovieDTO> getById(
            @Parameter(description = "Numeric ID of the movie", example = "45", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @Operation(summary = "Update an existing movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetMovieDTO> update(
            @Parameter(description = "Numeric ID of the movie to update", example = "45", required = true)
            @PathVariable Long id, @Valid @RequestBody SaveMovieDTO dto) {
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @Operation(summary = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Numeric ID of the movie to delete", example = "56", required = true)
            @PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
