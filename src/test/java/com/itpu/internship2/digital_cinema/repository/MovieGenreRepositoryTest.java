package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.MovieGenreEntity;
import com.itpu.internship2.digital_cinema.util.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieGenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    private MovieEntity movie;

    @BeforeEach
    void setUp() {
        movie = new MovieEntity();
        movie.setTitle("Test Movie");
        entityManager.persist(movie);
    }

    @Test
    void shouldFindAllByMovieId() {
        MovieGenreEntity genre1 = new MovieGenreEntity();
        genre1.setMovie(movie);
        genre1.setGenre(Genre.ACTION);
        entityManager.persist(genre1);

        MovieGenreEntity genre2 = new MovieGenreEntity();
        genre2.setMovie(movie);
        genre2.setGenre(Genre.COMEDY);
        entityManager.persist(genre2);

        List<MovieGenreEntity> result = movieGenreRepository.findAll();

        assertThat(result).hasSize(2);
    }
}
