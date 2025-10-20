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
//Airport(id, iata, icao, name, city, country, timezone)
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String iata;

    private String icao;

    private String name;

    private String city;

    private String country;

    private String timezone;

    @OneToMany(mappedBy = "originAirport")
    private Set<Route> originRoutes;

    @OneToMany(mappedBy = "destAirport")
    private Set<Route> destRoutes;


}
