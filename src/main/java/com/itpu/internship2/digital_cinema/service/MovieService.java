package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.movie.GetMovieDTO;
import com.itpu.internship2.digital_cinema.dto.movie.SaveMovieDTO;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.MovieMapper;
import com.itpu.internship2.digital_cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Transactional
    public GetMovieDTO create(SaveMovieDTO dto) {
        log.info("Creating movie: {}", dto.getTitle());
        MovieEntity entity = movieMapper.buildEntityFromDTO(dto);
        MovieEntity savedEntity = movieRepository.save(entity);
        return movieMapper.buildDTOFromEntity(savedEntity);
    }

    @Transactional(readOnly = true)
    public Page<GetMovieDTO> getAll(Pageable pageable) {
        log.info("Reading all movies");
        return movieRepository.findAll(pageable)
                .map(movieMapper::buildDTOFromEntity);
    }

    @Transactional(readOnly = true)
    public GetMovieDTO getById(Long id) {
        log.info("Reading movie by id: {}", id);
        MovieEntity entity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return movieMapper.buildDTOFromEntity(entity);
    }

    @Transactional
    public GetMovieDTO update(Long id, SaveMovieDTO dto) {
        log.info("Updating movie with id: {}", id);
        MovieEntity entity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieMapper.updateEntityFromDTO(entity, dto);
        MovieEntity updatedEntity = movieRepository.save(entity);
        return movieMapper.buildDTOFromEntity(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting movie with id: {}", id);
        MovieEntity entity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        movieRepository.delete(entity);
    }
}
