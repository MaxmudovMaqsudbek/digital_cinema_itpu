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

    @Schema(description = "Session Seat ID", example = "1")
    private Long id;

    @Schema(description = "Session ID", example = "1")
    private Long sessionId;

    @Schema(description = "Seat ID", example = "1")
    private Long seatId;

    @Schema(description = "Status of the seat for this session", example = "BOOKED")
    private SeatStatus status;

    @Schema(description = "Availability status reason", example = "Reserved for VIP")
    private String isAvailable;

    @Schema(description = "Customer name", example = "John Doe")
    private String customerName;

    @Schema(description = "Customer contact information", example = "+1234567890")
    private String contact;
}
