package com.itpu.internship2.digital_cinema.mapper;

import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    public SessionEntity buildEntityFromDTO(SaveSessionDTO dto, MovieEntity movie, HallEntity hall) {
        return SessionEntity.builder()
                .movie(movie)
                .hall(hall)
                .title(dto.getTitle())
                .date(dto.getDate())
                .time(dto.getTime())
                .language(dto.getLanguage())
                .format(dto.getFormat())
                .build();
    }

    public GetSessionDTO buildDTOFromEntity(SessionEntity entity) {
        return GetSessionDTO.builder()
                .id(entity.getId())
                .movieId(entity.getMovie().getId())
                .hallId(entity.getHall().getId())
                .title(entity.getTitle())
                .date(entity.getDate())
                .time(entity.getTime())
                .language(entity.getLanguage())
                .format(entity.getFormat())
                .build();
    }

    public void updateEntityFromDTO(SessionEntity entity, SaveSessionDTO dto, MovieEntity movie, HallEntity hall) {
        entity.setMovie(movie);
        entity.setHall(hall);
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());
        entity.setLanguage(dto.getLanguage());
        entity.setFormat(dto.getFormat());
    }
}
