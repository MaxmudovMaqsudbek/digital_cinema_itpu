package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {
}
