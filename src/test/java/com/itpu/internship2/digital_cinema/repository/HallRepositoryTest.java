package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HallRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HallRepository hallRepository;

    private CinemaEntity cinema;

    @BeforeEach
    void setUp() {
        cinema = new CinemaEntity();
        cinema.setName("Test Cinema");
        entityManager.persist(cinema);
    }

    @Test
    void shouldFindAllHalls() {
        HallEntity hall = new HallEntity();
        hall.setName("Main Hall");
        hall.setCinema(cinema);
        entityManager.persist(hall);

        Page<HallEntity> result = hallRepository.findAll(PageRequest.of(0, 10));

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("Main Hall");
    }

    @Test
    void shouldSaveHall() {
        HallEntity hall = new HallEntity();
        hall.setName("IMAX Hall");
        hall.setCinema(cinema);

        HallEntity saved = hallRepository.save(hall);

        assertThat(saved.getId()).isNotNull();
    }
}
