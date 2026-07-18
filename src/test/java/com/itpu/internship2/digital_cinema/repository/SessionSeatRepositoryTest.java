package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import com.itpu.internship2.digital_cinema.util.PriceCategory;
import com.itpu.internship2.digital_cinema.util.SeatStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SessionSeatRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SessionSeatRepository sessionSeatRepository;

    private SessionEntity session;
    private SeatEntity seat;

    @BeforeEach
    void setUp() {
        MovieEntity movie = new MovieEntity();
        movie.setTitle("Test Movie");
        entityManager.persist(movie);

        CinemaEntity cinema = new CinemaEntity();
        cinema.setName("Test Cinema");
        entityManager.persist(cinema);

        HallEntity hall = new HallEntity();
        hall.setName("Test Hall");
        hall.setCinema(cinema);
        entityManager.persist(hall);

        PriceCategoryEntity priceCategory = new PriceCategoryEntity();
        priceCategory.setType(PriceCategory.REGULAR);
        priceCategory.setName("Regular");
        priceCategory.setPrice(100.0f);
        entityManager.persist(priceCategory);

        seat = new SeatEntity();
        seat.setHall(hall);
        seat.setPriceCategory(priceCategory);
        seat.setRow(1);
        seat.setNumber(1);
        seat.setStatus(SeatStatus.ACTIVE);
        seat.setIsAvailable(true);
        entityManager.persist(seat);

        session = new SessionEntity();
        session.setMovie(movie);
        session.setHall(hall);
        session.setTitle("Morning Show");
        entityManager.persist(session);
    }

    @Test
    void shouldFindAllBySessionId() {
        SessionSeatEntity sessionSeat = new SessionSeatEntity();
        sessionSeat.setSession(session);
        sessionSeat.setSeat(seat);
        sessionSeat.setStatus(SeatStatus.ACTIVE);
        sessionSeat.setIsAvailable("true");
        entityManager.persist(sessionSeat);

        List<SessionSeatEntity> result = sessionSeatRepository.findAllBySessionId(session.getId());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSession().getId()).isEqualTo(session.getId());
    }
}
