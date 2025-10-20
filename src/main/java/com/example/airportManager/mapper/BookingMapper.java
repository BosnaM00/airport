package com.example.airportManager.mapper;

import com.example.airportManager.dto.booking.BookingCreateDTO;
import com.example.airportManager.dto.booking.BookingResponseDTO;
import com.example.airportManager.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    @Mapping(target = "flight", ignore = true)
    @Mapping(target = "pnr", ignore = true)
    Booking toEntity(BookingCreateDTO dto);

    @Mapping(target = "passengerId", source = "passenger.id")
    @Mapping(target = "flightId", source = "flight.id")
    BookingResponseDTO toResponse(Booking entity);
}
