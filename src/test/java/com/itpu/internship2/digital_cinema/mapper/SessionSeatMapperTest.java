package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import com.itpu.internship2.digital_cinema.util.SeatStatus;
import com.itpu.internship2.digital_cinema.fixture.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("SessionSeatMapper unit tests")
class SessionSeatMapperTest {

    private final SessionSeatMapper mapper = new SessionSeatMapper();

    @Test
    @DisplayName("buildEntityFromDTO() maps all fields including session and seat references")
    void buildEntityFromDTO_mapsAllFields() {
        // Arrange
        SaveSessionSeatDTO dto = TestDataFactory.saveSessionSeatDTO();
        SessionEntity session = TestDataFactory.sessionEntity();
        SeatEntity seat = TestDataFactory.seatEntity();

        // Act
        SessionSeatEntity entity = mapper.buildEntityFromDTO(dto, session, seat);

        // Assert
        assertThat(entity.getSession()).isSameAs(session);
        assertThat(entity.getSeat()).isSameAs(seat);
        assertThat(entity.getStatus()).isEqualTo(SeatStatus.ACTIVE);
        assertThat(entity.getIsAvailable()).isEqualTo("YES");
        assertThat(entity.getCustomerName()).isEqualTo("John Doe");
        assertThat(entity.getContact()).isEqualTo("+998901234567");
        assertThat(entity.getId()).isNull();
    }

    @Test
    @DisplayName("buildDTOFromEntity() maps all fields including FK IDs")
    void buildDTOFromEntity_mapsAllFields() {
        // Arrange
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();

        // Act
        GetSessionSeatDTO dto = mapper.buildDTOFromEntity(entity);

        // Assert
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getSessionId()).isEqualTo(1L);
        assertThat(dto.getSeatId()).isEqualTo(1L);
        assertThat(dto.getStatus()).isEqualTo(SeatStatus.ACTIVE);
        assertThat(dto.getIsAvailable()).isEqualTo("YES");
        assertThat(dto.getCustomerName()).isEqualTo("John Doe");
        assertThat(dto.getContact()).isEqualTo("+998901234567");
    }

    @Test
    @DisplayName("updateEntityFromDTO() updates all mutable fields and keeps ID")
    void updateEntityFromDTO_updatesFields() {
        // Arrange
        SessionSeatEntity entity = TestDataFactory.sessionSeatEntity();
        SessionEntity newSession = SessionEntity.builder().id(2L).build();
        SeatEntity newSeat = SeatEntity.builder().id(2L).build();
        SaveSessionSeatDTO dto = SaveSessionSeatDTO.builder()
                .sessionId(2L)
                .seatId(2L)
                .status(SeatStatus.DEACTIVATED)
                .isAvailable("NO")
                .customerName("Jane Doe")
                .contact("+998990000000")
                .build();

        // Act
        mapper.updateEntityFromDTO(entity, dto, newSession, newSeat);

        // Assert
        assertThat(entity.getId()).isEqualTo(1L); // unchanged
        assertThat(entity.getSession()).isSameAs(newSession);
        assertThat(entity.getSeat()).isSameAs(newSeat);
        assertThat(entity.getStatus()).isEqualTo(SeatStatus.DEACTIVATED);
        assertThat(entity.getIsAvailable()).isEqualTo("NO");
        assertThat(entity.getCustomerName()).isEqualTo("Jane Doe");
        assertThat(entity.getContact()).isEqualTo("+998990000000");
    }
}
