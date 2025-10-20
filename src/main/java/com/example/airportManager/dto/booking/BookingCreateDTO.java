package com.example.airportManager.dto.booking;

import com.example.airportManager.model.BookingStatus;
import jakarta.validation.constraints.NotNull;

public record BookingCreateDTO(
        @NotNull Long passengerId,
        @NotNull Long flightId,
        @NotNull BookingStatus status
) {}
