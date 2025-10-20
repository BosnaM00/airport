package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//PassengerProfile(user_id, docType, docNumber, nationality, loyaltyTier, emergencyContact)
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String docType;

    private int docNumber;

    private String nationality;

    private String loyaltyTier;

    private String emergencyContact;

}
