package com.example.airportManager.service.impl;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.mapper.BookingMapper;
import com.example.airportManager.model.Booking;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.Passenger;
import com.example.airportManager.repository.BookingRepository;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.repository.PassengerRepository;
import com.example.airportManager.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponseDTO create(BookingCreateDTO dto) {
        Passenger passenger = passengerRepository.findById(dto.passengerId())
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + dto.passengerId()));
        Flight flight = flightRepository.findById(dto.flightId())
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + dto.flightId()));

        Booking booking = bookingMapper.toEntity(dto);
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setPnr(generatePNR());

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponseDTO getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
        return bookingMapper.toResponse(booking);
    }

    private String generatePNR() {
        return "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
