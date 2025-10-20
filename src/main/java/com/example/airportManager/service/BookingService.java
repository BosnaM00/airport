package com.example.airportManager.service;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO create(BookingCreateDTO dto);
    BookingResponseDTO getById(Long id);
}
