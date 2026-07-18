package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.util.AgeRating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void shouldFindMovieById() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Inception");
        movie.setDurationMinutes(148);
        movie.setAgeRating(AgeRating.PG_13);
        movie.setReleaseYear(2010);
        movie.setRating(8.8f);
        MovieEntity savedMovie = entityManager.persistAndFlush(movie);

        Optional<MovieEntity> found = movieRepository.findById(savedMovie.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Inception");
    }

    @Test
    void shouldReturnEmptyWhenMovieNotFound() {
        Optional<MovieEntity> found = movieRepository.findById(999L);
        assertThat(found).isNotPresent();
    }
    
    @Test
    void shouldSaveMovie() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Interstellar");
        movie.setDurationMinutes(169);
        movie.setAgeRating(AgeRating.PG_13);
        movie.setReleaseYear(2014);
        
        MovieEntity saved = movieRepository.save(movie);
        
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }
    
    @Test
    void shouldDeleteMovie() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Tenet");
        MovieEntity savedMovie = entityManager.persistAndFlush(movie);
        
        movieRepository.deleteById(savedMovie.getId());
        
        Optional<MovieEntity> found = movieRepository.findById(savedMovie.getId());
        assertThat(found).isNotPresent();
    }
}
