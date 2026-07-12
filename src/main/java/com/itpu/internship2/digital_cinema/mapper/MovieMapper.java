package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieEntity buildEntityFromDTO(SaveMovieDTO dto) {
        return MovieEntity.builder()
                .title(dto.getTitle())
                .durationMinutes(dto.getDurationMinutes())
                .ageRating(dto.getAgeRating())
                .rating(dto.getRating())
                .posterUrl(dto.getPosterUrl())
                .description(dto.getDescription())
                .releaseYear(dto.getReleaseYear())
                .build();
    }

    public GetMovieDTO buildDTOFromEntity(MovieEntity entity) {
        return GetMovieDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .durationMinutes(entity.getDurationMinutes())
                .ageRating(entity.getAgeRating())
                .rating(entity.getRating())
                .posterUrl(entity.getPosterUrl())
                .description(entity.getDescription())
                .releaseYear(entity.getReleaseYear())
                .build();
    }

    public void updateEntityFromDTO(MovieEntity entity, SaveMovieDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setDurationMinutes(dto.getDurationMinutes());
        entity.setAgeRating(dto.getAgeRating());
        entity.setRating(dto.getRating());
        entity.setPosterUrl(dto.getPosterUrl());
        entity.setDescription(dto.getDescription());
        entity.setReleaseYear(dto.getReleaseYear());
    }
}
