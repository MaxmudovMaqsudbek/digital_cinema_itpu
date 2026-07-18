package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
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
class SeatRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SeatRepository seatRepository;

    private HallEntity hall;
    private PriceCategoryEntity priceCategory;

    @BeforeEach
    void setUp() {
        CinemaEntity cinema = new CinemaEntity();
        cinema.setName("Test Cinema");
        entityManager.persist(cinema);

        hall = new HallEntity();
        hall.setName("Test Hall");
        hall.setCinema(cinema);
        entityManager.persist(hall);

        priceCategory = new PriceCategoryEntity();
        priceCategory.setType(PriceCategory.REGULAR);
        priceCategory.setName("Regular");
        priceCategory.setPrice(100.0f);
        entityManager.persist(priceCategory);
    }

    @Test
    void shouldFindAllByHallId() {
        SeatEntity seat1 = new SeatEntity();
        seat1.setHall(hall);
        seat1.setPriceCategory(priceCategory);
        seat1.setRow(1);
        seat1.setNumber(1);
        seat1.setStatus(SeatStatus.ACTIVE);
        seat1.setIsAvailable(true);
        entityManager.persist(seat1);

        SeatEntity seat2 = new SeatEntity();
        seat2.setHall(hall);
        seat2.setPriceCategory(priceCategory);
        seat2.setRow(1);
        seat2.setNumber(2);
        seat2.setStatus(SeatStatus.ACTIVE);
        seat2.setIsAvailable(true);
        entityManager.persist(seat2);

        List<SeatEntity> result = seatRepository.findAllByHallId(hall.getId());

        assertThat(result).hasSize(2);
    }
}
