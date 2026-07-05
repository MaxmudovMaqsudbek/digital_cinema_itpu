package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
