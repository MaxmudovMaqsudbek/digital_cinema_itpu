package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {
    List<SeatEntity> findAllByHallId(Long hallId);
}
