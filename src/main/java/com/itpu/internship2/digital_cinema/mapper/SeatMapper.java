package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public SeatEntity buildEntityFromDTO(SaveSeatDTO dto, HallEntity hall, PriceCategoryEntity priceCategory) {
        return SeatEntity.builder()
                .hall(hall)
                .priceCategory(priceCategory)
                .row(dto.getRow())
                .number(dto.getNumber())
                .status(dto.getStatus())
                .isAvailable(dto.getIsAvailable())
                .comment(dto.getComment())
                .build();
    }

    public GetSeatDTO buildDTOFromEntity(SeatEntity entity) {
        return GetSeatDTO.builder()
                .id(entity.getId())
                .hallId(entity.getHall().getId())
                .priceCategoryId(entity.getPriceCategory().getId())
                .row(entity.getRow())
                .number(entity.getNumber())
                .status(entity.getStatus())
                .isAvailable(entity.getIsAvailable())
                .comment(entity.getComment())
                .build();
    }

    public void updateEntityFromDTO(SeatEntity entity, SaveSeatDTO dto, HallEntity hall, PriceCategoryEntity priceCategory) {
        entity.setHall(hall);
        entity.setPriceCategory(priceCategory);
        entity.setRow(dto.getRow());
        entity.setNumber(dto.getNumber());
        entity.setStatus(dto.getStatus());
        entity.setIsAvailable(dto.getIsAvailable());
        entity.setComment(dto.getComment());
    }
}
