package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
