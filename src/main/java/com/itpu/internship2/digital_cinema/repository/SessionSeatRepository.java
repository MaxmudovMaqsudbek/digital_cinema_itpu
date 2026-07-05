package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionSeatRepository extends JpaRepository<SessionSeatEntity, Long> {
}
