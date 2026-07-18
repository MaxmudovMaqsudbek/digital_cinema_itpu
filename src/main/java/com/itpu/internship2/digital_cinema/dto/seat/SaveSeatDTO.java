package com.itpu.internship2.digital_cinema.dto.seat;

import com.itpu.internship2.digital_cinema.util.SeatStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class SaveSeatDTO {

    @Schema(description = "Hall ID where the seat is located", example = "14")
    @NotNull
    private Long hallId;

    @Schema(description = "Price Category ID for the seat", example = "2")
    @NotNull
    private Long priceCategoryId;

    @Schema(description = "Row number", example = "3")
    @NotNull
    @Positive
    private Integer row;

    @Schema(description = "Seat number in the row", example = "7")
    @NotNull
    @Positive
    private Integer number;

    @Schema(description = "Status of the seat", example = "ACTIVE")
    private SeatStatus status;

    @Schema(description = "Availability of the seat", example = "true")
    private Boolean isAvailable;

    @Schema(description = "Additional comments", example = "Front row center seat")
    @Size(max = 500)
    private String comment;
}
