package com.example.airportManager.web.controller;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> create(@Valid @RequestBody BookingCreateDTO dto) {
        BookingResponseDTO created = bookingService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getById(@PathVariable Long id) {
        BookingResponseDTO booking = bookingService.getById(id);
        return ResponseEntity.ok(booking);
    }
}
