package com.itpu.internship2.digital_cinema.service;

import com.itpu.internship2.digital_cinema.dto.seat.GetSeatDTO;
import com.itpu.internship2.digital_cinema.dto.seat.SaveSeatDTO;
import com.itpu.internship2.digital_cinema.entity.HallEntity;
import com.itpu.internship2.digital_cinema.entity.PriceCategoryEntity;
import com.itpu.internship2.digital_cinema.entity.SeatEntity;
import com.itpu.internship2.digital_cinema.exception.ResourceNotFoundException;
import com.itpu.internship2.digital_cinema.mapper.SeatMapper;
import com.itpu.internship2.digital_cinema.repository.HallRepository;
import com.itpu.internship2.digital_cinema.repository.PriceCategoryRepository;
import com.itpu.internship2.digital_cinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeatService {

    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;
    private final PriceCategoryRepository priceCategoryRepository;
    private final SeatMapper seatMapper;

    @Transactional
    public GetSeatDTO create(SaveSeatDTO dto) {
        log.info("Creating seat for hall id: {}, price category id: {}", dto.getHallId(), dto.getPriceCategoryId());
        
        HallEntity hall = hallRepository.findById(dto.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + dto.getHallId()));
        PriceCategoryEntity priceCategory = priceCategoryRepository.findById(dto.getPriceCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Price Category not found with id: " + dto.getPriceCategoryId()));

        SeatEntity entity = seatMapper.buildEntityFromDTO(dto, hall, priceCategory);
        SeatEntity savedEntity = seatRepository.save(entity);
        return seatMapper.buildDTOFromEntity(savedEntity);
    }

    @Transactional(readOnly = true)
    public Page<GetSeatDTO> getAll(Pageable pageable) {
        log.info("Reading all seats");
        return seatRepository.findAll(pageable)
                .map(seatMapper::buildDTOFromEntity);
    }

    @Transactional(readOnly = true)
    public GetSeatDTO getById(Long id) {
        log.info("Reading seat by id: {}", id);
        SeatEntity entity = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + id));
        return seatMapper.buildDTOFromEntity(entity);
    }

    @Transactional
    public GetSeatDTO update(Long id, SaveSeatDTO dto) {
        log.info("Updating seat with id: {}", id);
        SeatEntity entity = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + id));

        HallEntity hall = hallRepository.findById(dto.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall not found with id: " + dto.getHallId()));
        PriceCategoryEntity priceCategory = priceCategoryRepository.findById(dto.getPriceCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Price Category not found with id: " + dto.getPriceCategoryId()));

        seatMapper.updateEntityFromDTO(entity, dto, hall, priceCategory);
        SeatEntity updatedEntity = seatRepository.save(entity);
        return seatMapper.buildDTOFromEntity(updatedEntity);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting seat with id: {}", id);
        SeatEntity entity = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + id));
        seatRepository.delete(entity);
    }
}
