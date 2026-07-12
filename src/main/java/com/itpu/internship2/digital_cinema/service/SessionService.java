package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.session.GetSessionDTO;
import com.itpu.internship2.digital_cinema.dto.session.SaveSessionDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.MovieEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SessionMapper;
import com.itpu.internship2.digital_cinema.repository.HallRepository;
import com.itpu.internship2.digital_cinema.repository.MovieRepository;
import com.itpu.internship2.digital_cinema.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;
    private final SessionMapper sessionMapper;

    @Transactional
    public GetSessionDTO create(SaveSessionDTO dto) {
        log.info("Creating session for movie id: {}, hall id: {}", dto.getMovieId(), dto.getHallId());
        
        MovieEntity movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + dto.getMovieId()));
        HallEntity hall = hallRepository.findById(dto.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + dto.getHallId()));

        SessionEntity entity = sessionMapper.buildEntityFromDTO(dto, movie, hall);
        SessionEntity savedEntity = sessionRepository.save(entity);
        return sessionMapper.buildDTOFromEntity(savedEntity);
    }

    @Transactional(readOnly = true)
    public Page<GetSessionDTO> getAll(Pageable pageable) {
        log.info("Reading all sessions");
        return sessionRepository.findAll(pageable)
                .map(sessionMapper::buildDTOFromEntity);
    }

    @Transactional(readOnly = true)
    public GetSessionDTO getById(Long id) {
        log.info("Reading session by id: {}", id);
        SessionEntity entity = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        return sessionMapper.buildDTOFromEntity(entity);
    }

    @Transactional
    public GetSessionDTO update(Long id, SaveSessionDTO dto) {
        log.info("Updating session with id: {}", id);
        SessionEntity entity = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        MovieEntity movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + dto.getMovieId()));
        HallEntity hall = hallRepository.findById(dto.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + dto.getHallId()));

        sessionMapper.updateEntityFromDTO(entity, dto, movie, hall);
        SessionEntity updatedEntity = sessionRepository.save(entity);
        return sessionMapper.buildDTOFromEntity(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting session with id: {}", id);
        SessionEntity entity = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        sessionRepository.delete(entity);
    }
}
