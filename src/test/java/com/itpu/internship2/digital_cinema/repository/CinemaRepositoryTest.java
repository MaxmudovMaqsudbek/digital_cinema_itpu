package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CinemaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Test
    void shouldFindAllCinemas() {
        CinemaEntity cinema = new CinemaEntity();
        cinema.setName("ITPU Grand");
        cinema.setAddress("Tashkent");
        cinema.setCity("Tashkent");
        entityManager.persist(cinema);

        Page<CinemaEntity> result = cinemaRepository.findAll(PageRequest.of(0, 10));

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("ITPU Grand");
    }

    @Test
    void shouldDeleteCinema() {
        CinemaEntity cinema = new CinemaEntity();
        cinema.setName("To Be Deleted");
        CinemaEntity saved = entityManager.persistAndFlush(cinema);

        cinemaRepository.deleteById(saved.getId());

        assertThat(cinemaRepository.findById(saved.getId())).isEmpty();
    }
}
