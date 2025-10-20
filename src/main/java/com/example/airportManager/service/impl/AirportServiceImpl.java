package com.example.airportManager.service.impl;

import com.example.airportManager.dto.airport.AirportCreateDTO;
import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.dto.airport.AirportUpdateDTO;
import com.example.airportManager.mapper.AirportMapper;
import com.example.airportManager.model.Airport;
import com.example.airportManager.repository.AirportRepository;
import com.example.airportManager.service.AirportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AirportResponseDTO> list(Pageable pageable) {
        return airportRepository.findAll(pageable)
                .map(airportMapper::toResponse);
    }

    @Override
    @Transactional
    public AirportResponseDTO create(AirportCreateDTO dto) {
        Airport airport = airportMapper.toEntity(dto);
        Airport saved = airportRepository.save(airport);
        return airportMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AirportResponseDTO update(Long id, AirportUpdateDTO dto) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airport not found with id: " + id));
        airportMapper.update(airport, dto);
        Airport updated = airportRepository.save(airport);
        return airportMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new EntityNotFoundException("Airport not found with id: " + id);
        }
        airportRepository.deleteById(id);
    }
}
