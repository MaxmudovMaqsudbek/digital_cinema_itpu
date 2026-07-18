package com.itpu.internship2.digital_cinema.dto.movie;

import com.itpu.internship2.digital_cinema.util.AgeRating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class SaveMovieDTO {

    @Schema(description = "Movie title", example = "Inception")
    @NotBlank
    @Size(max = 500)
    private String title;

    @Schema(description = "Duration in minutes", example = "148")
    @Positive
    private Integer durationMinutes;

    @Schema(description = "Age rating", example = "PG_13")
    private AgeRating ageRating;

    @Schema(description = "Movie rating out of 10", example = "8.8")
    @Min(0)
    @Max(10)
    private Float rating;

    @Schema(description = "URL to the movie poster",
            example = "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg")
    @Size(max = 1000)
    private String posterUrl;

    @Schema(description = "Movie description",
            example = "A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.")
    private String description;

    @Schema(description = "Release year", example = "2010")
    @Min(1900)
    private Integer releaseYear;
}
