package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.util.PriceCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PriceCategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceCategoryRepository priceCategoryRepository;

    @Test
    void shouldFindAllPriceCategories() {
        PriceCategoryEntity pc = new PriceCategoryEntity();
        pc.setName("LUXURY");
        pc.setType(PriceCategory.LUXURY);
        pc.setPrice(50.0f);
        entityManager.persist(pc);

        Page<PriceCategoryEntity> result = priceCategoryRepository.findAll(PageRequest.of(0, 10));

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("LUXURY");
    }
}
