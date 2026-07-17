package com.itpu.internship2.digital_cinema.controller;

import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.service.SeatService;
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
@RequestMapping("/seats")
@RequiredArgsConstructor
@Validated
@Tag(name = "Seats", description = "Endpoints for managing cinema seats (places)")
public class SeatController {

    private final SeatService seatService;

    @Operation(summary = "Create a new seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Seat created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<GetSeatDTO> create(@Valid @RequestBody SaveSeatDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seatService.create(dto));
    }

    @Operation(summary = "Get all seats with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seats retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<GetSeatDTO>> getAll(@Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(seatService.getAll(pageable));
    }

    @Operation(summary = "Get all places by hall ID", description = "Returns a list of all cinema seats for the specified hall ID, with nulls representing empty gaps.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of places returned successfully"),
            @ApiResponse(responseCode = "404", description = "Hall not found")
    })
    @GetMapping("/hall/{hallId}")
    public ResponseEntity<java.util.List<GetSeatDTO>> getAllPlacesByHallId(
            @Parameter(description = "Numeric ID of the hall", required = true)
            @PathVariable Long hallId) {
        return ResponseEntity.ok(seatService.getAllPlacesByHallId(hallId));
    }

    @Operation(summary = "Get a seat by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Seat not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetSeatDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getById(id));
    }

    @Operation(summary = "Update an existing seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seat updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Seat not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetSeatDTO> update(@PathVariable Long id, @Valid @RequestBody SaveSeatDTO dto) {
        return ResponseEntity.ok(seatService.update(id, dto));
    }

    @Operation(summary = "Delete a seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Seat deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Seat not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
