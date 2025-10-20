package com.example.airportManager.service.impl;

import com.example.airportManager.dto.flight.FlightCreateDTO;
import com.example.airportManager.dto.flight.FlightResponseDTO;
import com.example.airportManager.exception.ConflictException;
import com.example.airportManager.mapper.FlightMapper;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.FlightStatus;
import com.example.airportManager.model.Route;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.repository.RouteRepository;
import com.example.airportManager.service.FlightService;
import com.example.airportManager.spec.FlightSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final RouteRepository routeRepository;
    private final FlightMapper flightMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<FlightResponseDTO> list(
            Pageable pageable,
            Optional<LocalDateTime> dateFrom,
            Optional<LocalDateTime> dateTo,
            Optional<Long> routeId,
            Optional<FlightStatus> status
    ) {
        Specification<Flight> spec = Specification.where(null);
        if (dateFrom.isPresent() || dateTo.isPresent()) {
            spec = spec.and(FlightSpecifications.departureBetween(dateFrom.orElse(null), dateTo.orElse(null)));
        }
        if (routeId.isPresent()) {
            spec = spec.and(FlightSpecifications.hasRouteId(routeId.get()));
        }
        if (status.isPresent()) {
            spec = spec.and(FlightSpecifications.hasStatus(status.get()));
        }
        return flightRepository.findAll(spec, pageable)
                .map(flightMapper::toResponse);
    }

    @Override
    @Transactional
    public FlightResponseDTO create(FlightCreateDTO dto) {
        if (flightRepository.findByCode(dto.code()).isPresent()) {
            throw new ConflictException("Flight with code " + dto.code() + " already exists");
        }

        Route route = routeRepository.findById(dto.routeId())
                .orElseThrow(() -> new EntityNotFoundException("Route not found with id: " + dto.routeId()));

        Flight flight = flightMapper.toEntity(dto);
        flight.setRoute(route);

        Flight saved = flightRepository.save(flight);
        return flightMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public FlightResponseDTO update(Long id, FlightCreateDTO dto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + id));

        Optional<Flight> existingWithCode = flightRepository.findByCode(dto.code());
        if (existingWithCode.isPresent() && !existingWithCode.get().getId().equals(id)) {
            throw new ConflictException("Flight with code " + dto.code() + " already exists");
        }

        Route route = routeRepository.findById(dto.routeId())
                .orElseThrow(() -> new EntityNotFoundException("Route not found with id: " + dto.routeId()));

        flight.setCode(dto.code());
        flight.setRoute(route);
        flight.setDepartureScheduled(dto.departureTime());
        flight.setArrivalScheduled(dto.arrivalTime());
        flight.setStatus(dto.status());

        Flight updated = flightRepository.save(flight);
        return flightMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new EntityNotFoundException("Flight not found with id: " + id);
        }
        flightRepository.deleteById(id);
    }
}
