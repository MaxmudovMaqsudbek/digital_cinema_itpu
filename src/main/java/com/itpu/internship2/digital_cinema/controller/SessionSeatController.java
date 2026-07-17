package com.itpu.internship2.digital_cinema.controller;

import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.service.SessionSeatService;
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
@RequestMapping("/session-seats")
@RequiredArgsConstructor
@Validated
@Tag(name = "Session Seats", description = "Endpoints for managing seats assigned to sessions")
public class SessionSeatController {

    private final SessionSeatService sessionSeatService;

    @Operation(summary = "Create a new session seat mapping")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Session Seat created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<GetSessionSeatDTO> create(@Valid @RequestBody SaveSessionSeatDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionSeatService.create(dto));
    }

    @Operation(summary = "Get all session seats with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session seats retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<GetSessionSeatDTO>> getAll(@Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(sessionSeatService.getAll(pageable));
    }

    @Operation(summary = "Get all session seats by session ID", description = "Returns a list of all assigned seats for a particular screening session.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of session seats returned successfully"),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<java.util.List<GetSessionSeatDTO>> getSessionSeatsBySessionId(
            @Parameter(description = "Numeric ID of the session", required = true)
            @PathVariable Long sessionId) {
        return ResponseEntity.ok(sessionSeatService.getAllBySessionId(sessionId));
    }

    @Operation(summary = "Get a session seat by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session seat retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Session seat not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetSessionSeatDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionSeatService.getById(id));
    }

    @Operation(summary = "Update an existing session seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session seat updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Session seat not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetSessionSeatDTO> update(@PathVariable Long id, @Valid @RequestBody SaveSessionSeatDTO dto) {
        return ResponseEntity.ok(sessionSeatService.update(id, dto));
    }

    @Operation(summary = "Delete a session seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Session seat deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Session seat not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sessionSeatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
