package com.itpu.internship2.digital_cinema.repository;

import com.itpu.internship2.digital_cinema.entity.MovieGenreEntity;
import com.itpu.internship2.digital_cinema.entity.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenreEntity, MovieGenreId> {
}
