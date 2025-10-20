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
//Aircraft(id, tailNumber, model, capacity, seatMapRef, status)
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String tailNumber;

    private String model;

    private int capacity;

    private String seatMapRef;

    private String status;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;


}
