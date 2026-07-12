package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionSeatMapper {

    public SessionSeatEntity buildEntityFromDTO(SaveSessionSeatDTO dto, SessionEntity session, SeatEntity seat) {
        return SessionSeatEntity.builder()
                .session(session)
                .seat(seat)
                .status(dto.getStatus())
                .isAvailable(dto.getIsAvailable())
                .customerName(dto.getCustomerName())
                .contact(dto.getContact())
                .build();
    }

    public GetSessionSeatDTO buildDTOFromEntity(SessionSeatEntity entity) {
        return GetSessionSeatDTO.builder()
                .id(entity.getId())
                .sessionId(entity.getSession().getId())
                .seatId(entity.getSeat().getId())
                .status(entity.getStatus())
                .isAvailable(entity.getIsAvailable())
                .customerName(entity.getCustomerName())
                .contact(entity.getContact())
                .build();
    }

    public void updateEntityFromDTO(SessionSeatEntity entity, SaveSessionSeatDTO dto, SessionEntity session, SeatEntity seat) {
        entity.setSession(session);
        entity.setSeat(seat);
        entity.setStatus(dto.getStatus());
        entity.setIsAvailable(dto.getIsAvailable());
        entity.setCustomerName(dto.getCustomerName());
        entity.setContact(dto.getContact());
    }
}
