package com.itpu.internship2.digital_cinema.dto.movie;

import com.itpu.internship2.digital_cinema.util.AgeRating;
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
public class GetMovieDTO {

    @Schema(description = "Movie ID", example = "1")
    private Long id;

    @Schema(description = "Movie title", example = "The Matrix")
    private String title;

    @Schema(description = "Duration in minutes", example = "136")
    private Integer durationMinutes;

    @Schema(description = "Age rating", example = "R")
    private AgeRating ageRating;

    @Schema(description = "Movie rating", example = "8.7")
    private Float rating;

    @Schema(description = "URL to the movie poster", example = "https://example.com/matrix.jpg")
    private String posterUrl;

    @Schema(description = "Movie description", example = "A computer hacker learns from mysterious rebels about the true nature of his reality...")
    private String description;

    @Schema(description = "Release year", example = "1999")
    private Integer releaseYear;
}
