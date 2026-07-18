package com.itpu.internship2.digital_cinema.dto.seat;

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
public class GetSeatDTO {

    @Schema(description = "Seat ID", example = "101")
    private Long id;

    @Schema(description = "Hall ID where the seat is located", example = "14")
    private Long hallId;

    @Schema(description = "Price Category ID for the seat", example = "1")
    private Long priceCategoryId;

    @Schema(description = "Row number", example = "1")
    private Integer row;

    @Schema(description = "Seat number in the row", example = "1")
    private Integer number;

    @Schema(description = "Status of the seat", example = "ACTIVE")
    private SeatStatus status;

    @Schema(description = "Availability of the seat", example = "true")
    private Boolean isAvailable;

    @Schema(description = "Additional comments", example = "Front row center seat")
    private String comment;
}
