package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.util.MovieFormat;
import com.itpu.internship2.digital_cinema.util.MovieLang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SessionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SessionRepository sessionRepository;

    private MovieEntity movie;
    private HallEntity hall;

    @BeforeEach
    void setUp() {
        movie = new MovieEntity();
        movie.setTitle("Test Movie");
        entityManager.persist(movie);

        CinemaEntity cinema = new CinemaEntity();
        cinema.setName("Test Cinema");
        entityManager.persist(cinema);

        hall = new HallEntity();
        hall.setName("Test Hall");
        hall.setCinema(cinema);
        entityManager.persist(hall);
    }

    @Test
    void shouldFindAllSessions() {
        SessionEntity session = new SessionEntity();
        session.setMovie(movie);
        session.setHall(hall);
        session.setTitle("Morning Show");
        session.setDate(LocalDate.now());
        session.setTime(LocalTime.of(10, 0));
        session.setLanguage(MovieLang.ENGLISH);
        session.setFormat(MovieFormat.TWO_D);
        entityManager.persist(session);

        Page<SessionEntity> result = sessionRepository.findAll(PageRequest.of(0, 10));

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Morning Show");
    }
}
