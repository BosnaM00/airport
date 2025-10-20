package com.example.airportManager.dto.booking;

import com.example.airportManager.model.BookingStatus;

public record BookingResponseDTO(
        Long id,
        Long passengerId,
        Long flightId,
        BookingStatus status
) {}
