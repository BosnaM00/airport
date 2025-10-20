package com.example.airportManager.web.controller;

import com.example.airportManager.dto.flight.FlightCreateDTO;
import com.example.airportManager.dto.flight.FlightResponseDTO;
import com.example.airportManager.model.FlightStatus;
import com.example.airportManager.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
@Validated
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public Page<FlightResponseDTO> list(
            @ParameterObject @PageableDefault(sort = "departureScheduled", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> dateTo,
            @RequestParam Optional<Long> routeId,
            @RequestParam Optional<FlightStatus> status
    ) {
        return flightService.list(pageable, dateFrom, dateTo, routeId, status);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDTO> create(@Valid @RequestBody FlightCreateDTO dto) {
        FlightResponseDTO created = flightService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody FlightCreateDTO dto
    ) {
        FlightResponseDTO updated = flightService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flightService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
