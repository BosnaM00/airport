package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//Route(id, origin_airport_id, dest_airport_id, distanceNm, stdDurationMin)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private int distanceNm;

    private int stdDurationMin;

    @ManyToOne
    @JoinColumn(
            name = "origin_airport_id"
    )
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(
            name = "dest_airport_id"
    )
    private Airport destAirport;

    @OneToMany(mappedBy = "route")
    private Set<Flight> flights;
}
