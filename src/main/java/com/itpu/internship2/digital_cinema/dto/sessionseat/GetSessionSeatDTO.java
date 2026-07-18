package com.itpu.internship2.digital_cinema.dto.sessionseat;

import com.itpu.internship2.digital_cinema.util.SeatStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSessionSeatDTO {

    @Schema(description = "Session Seat ID", example = "601")
    private Long id;

    @Schema(description = "Session ID", example = "4")
    private Long sessionId;

    @Schema(description = "Seat ID", example = "101")
    private Long seatId;

    @Schema(description = "Status of the seat for this session", example = "ACTIVE")
    private SeatStatus status;

    @Schema(description = "Is the seat still available (true/false)", example = "false")
    private String isAvailable;

    @Schema(description = "Customer full name", example = "Ahmed Karimov")
    private String customerName;

    @Schema(description = "Customer phone number", example = "+998901001001")
    private String contact;
}
