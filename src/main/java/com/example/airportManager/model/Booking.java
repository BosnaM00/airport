package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//Booking(id, user_id, flight_id, pnr, bookingStatus: CONFIRMED|CANCELLED|CHECKED_IN)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long flight_id;

    private String pnr;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
