package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.util.SeatStatus;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SeatMapper unit tests")
class SeatMapperTest {

    private final SeatMapper mapper = new SeatMapper();

    @Test
    @DisplayName("buildEntityFromDTO() maps all fields including hall and priceCategory references")
    void buildEntityFromDTO_mapsAllFields() {
        // Arrange
        SaveSeatDTO dto = TestDataFactory.saveSeatDTO();
        HallEntity hall = TestDataFactory.hallEntity();
        PriceCategoryEntity pc = TestDataFactory.priceCategoryEntity();

        // Act
        SeatEntity entity = mapper.buildEntityFromDTO(dto, hall, pc);

        // Assert
        assertThat(entity.getHall()).isSameAs(hall);
        assertThat(entity.getPriceCategory()).isSameAs(pc);
        assertThat(entity.getRow()).isEqualTo(5);
        assertThat(entity.getNumber()).isEqualTo(12);
        assertThat(entity.getStatus()).isEqualTo(SeatStatus.ACTIVE);
        assertThat(entity.getIsAvailable()).isTrue();
        assertThat(entity.getComment()).isEqualTo("VIP Seat");
        assertThat(entity.getId()).isNull(); // no ID from DTO
    }

    @Test
    @DisplayName("buildDTOFromEntity() maps all fields including FK IDs")
    void buildDTOFromEntity_mapsAllFields() {
        // Arrange
        SeatEntity entity = TestDataFactory.seatEntity();

        // Act
        GetSeatDTO dto = mapper.buildDTOFromEntity(entity);

        // Assert
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getHallId()).isEqualTo(1L);
        assertThat(dto.getPriceCategoryId()).isEqualTo(1L);
        assertThat(dto.getRow()).isEqualTo(5);
        assertThat(dto.getNumber()).isEqualTo(12);
        assertThat(dto.getStatus()).isEqualTo(SeatStatus.ACTIVE);
        assertThat(dto.getIsAvailable()).isTrue();
        assertThat(dto.getComment()).isEqualTo("VIP Seat");
    }

    @Test
    @DisplayName("updateEntityFromDTO() updates all mutable fields and keeps ID")
    void updateEntityFromDTO_updatesFields() {
        // Arrange
        SeatEntity entity = TestDataFactory.seatEntity();
        HallEntity newHall = HallEntity.builder().id(2L).name("Hall B").build();
        PriceCategoryEntity newPc = PriceCategoryEntity.builder().id(2L).name("VIP").price(25.0f).build();
        SaveSeatDTO dto = SaveSeatDTO.builder()
                .hallId(2L)
                .priceCategoryId(2L)
                .row(10)
                .number(5)
                .status(SeatStatus.DEACTIVATED)
                .isAvailable(false)
                .comment("Broken seat")
                .build();

        // Act
        mapper.updateEntityFromDTO(entity, dto, newHall, newPc);

        // Assert
        assertThat(entity.getId()).isEqualTo(1L); // unchanged
        assertThat(entity.getHall()).isSameAs(newHall);
        assertThat(entity.getPriceCategory()).isSameAs(newPc);
        assertThat(entity.getRow()).isEqualTo(10);
        assertThat(entity.getNumber()).isEqualTo(5);
        assertThat(entity.getStatus()).isEqualTo(SeatStatus.DEACTIVATED);
        assertThat(entity.getIsAvailable()).isFalse();
        assertThat(entity.getComment()).isEqualTo("Broken seat");
    }
}
