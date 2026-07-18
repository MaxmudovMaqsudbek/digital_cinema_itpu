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

    @Schema(description = "Session ID", example = "4")
    @NotNull
    private Long sessionId;

    @Schema(description = "Seat ID", example = "101")
    @NotNull
    private Long seatId;

    @Schema(description = "Status of the seat for this session", example = "ACTIVE")
    private SeatStatus status;

    @Schema(description = "Is the seat still available (true/false)", example = "false")
    @Size(max = 50)
    private String isAvailable;

    @Schema(description = "Customer full name", example = "Maksudbek Makhmudov")
    @Size(max = 255)
    private String customerName;

    @Schema(description = "Customer phone number", example = "+998901234567")
    @Size(max = 255)
    private String contact;
}
