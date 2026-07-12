package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.sessionseat.GetSessionSeatDTO;
import com.itpu.internship2.digital_cinema.dto.sessionseat.SaveSessionSeatDTO;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.entity.SessionEntity;
import com.itpu.internship2.digital_cinema.entity.SessionSeatEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SessionSeatMapper;
import com.itpu.internship2.digital_cinema.repository.SeatRepository;
import com.itpu.internship2.digital_cinema.repository.SessionRepository;
import com.itpu.internship2.digital_cinema.repository.SessionSeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionSeatService {

    private final SessionSeatRepository sessionSeatRepository;
    private final SessionRepository sessionRepository;
    private final SeatRepository seatRepository;
    private final SessionSeatMapper sessionSeatMapper;

    @Transactional
    public GetSessionSeatDTO create(SaveSessionSeatDTO dto) {
        log.info("Creating session seat for session id: {}, seat id: {}", dto.getSessionId(), dto.getSeatId());
        
        SessionEntity session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + dto.getSessionId()));
        SeatEntity seat = seatRepository.findById(dto.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + dto.getSeatId()));

        SessionSeatEntity entity = sessionSeatMapper.buildEntityFromDTO(dto, session, seat);
        SessionSeatEntity savedEntity = sessionSeatRepository.save(entity);
        return sessionSeatMapper.buildDTOFromEntity(savedEntity);
    }

    @Transactional(readOnly = true)
    public Page<GetSessionSeatDTO> getAll(Pageable pageable) {
        log.info("Reading all session seats");
        return sessionSeatRepository.findAll(pageable)
                .map(sessionSeatMapper::buildDTOFromEntity);
    }

    @Transactional(readOnly = true)
    public GetSessionSeatDTO getById(Long id) {
        log.info("Reading session seat by id: {}", id);
        SessionSeatEntity entity = sessionSeatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session Seat not found with id: " + id));
        return sessionSeatMapper.buildDTOFromEntity(entity);
    }

    @Transactional
    public GetSessionSeatDTO update(Long id, SaveSessionSeatDTO dto) {
        log.info("Updating session seat with id: {}", id);
        SessionSeatEntity entity = sessionSeatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session Seat not found with id: " + id));

        SessionEntity session = sessionRepository.findById(dto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + dto.getSessionId()));
        SeatEntity seat = seatRepository.findById(dto.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + dto.getSeatId()));

        sessionSeatMapper.updateEntityFromDTO(entity, dto, session, seat);
        SessionSeatEntity updatedEntity = sessionSeatRepository.save(entity);
        return sessionSeatMapper.buildDTOFromEntity(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting session seat with id: {}", id);
        SessionSeatEntity entity = sessionSeatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session Seat not found with id: " + id));
        sessionSeatRepository.delete(entity);
    }
}
