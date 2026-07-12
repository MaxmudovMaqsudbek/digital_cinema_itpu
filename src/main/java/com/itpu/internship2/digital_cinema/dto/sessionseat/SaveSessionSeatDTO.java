package com.itpu.internship2.digital_cinema.dto.sessionseat;

import com.itpu.internship2.digital_cinema.util.SeatStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class SaveSessionSeatDTO {

    @Schema(description = "Session ID", example = "1")
    @NotNull
    private Long sessionId;

    @Schema(description = "Seat ID", example = "1")
    @NotNull
    private Long seatId;

    @Schema(description = "Status of the seat for this session", example = "BOOKED")
    private SeatStatus status;

    @Schema(description = "Availability status reason", example = "Reserved for VIP")
    @Size(max = 50)
    private String isAvailable;

    @Schema(description = "Customer name", example = "John Doe")
    @Size(max = 255)
    private String customerName;

    @Schema(description = "Customer contact information", example = "+1234567890")
    @Size(max = 255)
    private String contact;
}
