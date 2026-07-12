package com.itpu.internship2.digital_cinema.controller;

import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.service.SessionService;
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
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Validated
@Tag(name = "Sessions", description = "Endpoints for managing movie screening sessions")
public class SessionController {

    private final SessionService sessionService;

    @Operation(summary = "Create a new session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<GetSessionDTO> create(@Valid @RequestBody SaveSessionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.create(dto));
    }

    @Operation(summary = "Get all sessions with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<GetSessionDTO>> getAll(@Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(sessionService.getAll(pageable));
    }

    @Operation(summary = "Get a session by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetSessionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    @Operation(summary = "Update an existing session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetSessionDTO> update(@PathVariable Long id, @Valid @RequestBody SaveSessionDTO dto) {
        return ResponseEntity.ok(sessionService.update(id, dto));
    }

    @Operation(summary = "Delete a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Session deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
