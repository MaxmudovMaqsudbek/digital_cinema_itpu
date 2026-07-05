package com.itpu.internship2.digital_cinema.entity;

import com.itpu.internship2.digital_cinema.util.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieGenreId implements Serializable {

    @Column(name = "movie_id")
    private Long movieId;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 50)
    private Genre genre;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
