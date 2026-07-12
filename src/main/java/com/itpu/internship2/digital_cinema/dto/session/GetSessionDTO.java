package com.itpu.internship2.digital_cinema.dto.session;

import com.itpu.internship2.digital_cinema.util.MovieFormat;
import com.itpu.internship2.digital_cinema.util.MovieLang;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSessionDTO {

    @Schema(description = "Session ID", example = "1")
    private Long id;

    @Schema(description = "Movie ID", example = "1")
    private Long movieId;

    @Schema(description = "Hall ID", example = "1")
    private Long hallId;

    @Schema(description = "Session title or description", example = "Evening Premiere")
    private String title;

    @Schema(description = "Date of the session", example = "2026-07-20")
    private LocalDate date;

    @Schema(description = "Time of the session", example = "19:00:00")
    private LocalTime time;

    @Schema(description = "Language of the movie", example = "ENGLISH")
    private MovieLang language;

    @Schema(description = "Format of the movie", example = "IMAX_3D")
    private MovieFormat format;
}
