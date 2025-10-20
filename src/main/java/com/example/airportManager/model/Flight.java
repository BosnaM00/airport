package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//Flight(id, code, route_id, aircraft_id, departureScheduled,
// arrivalScheduled, gate, status: SCHEDULED|BOARDING|DELAYED|CANCELLED|IN_AIR|LANDED)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String code;

    private Long route_id;

//    private Long aircraft_id;

    private String departureScheduled;

    private String arrivalScheduled;

    private String gate;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ManyToOne
    @JoinColumn(
            name = "aircraft_id"
    )
    private Aircraft aircraft;

    @ManyToOne
    @JoinColumn(
            name = "route_id"
    )
    private Route route;
}
