package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "passenger", indexes = {
    @Index(name = "idx_passenger_doc", columnList = "docNumber")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"bookings"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String docType;

    @Column(nullable = false)
    private String docNumber;

    @Column(nullable = false)
    private String nationality;

    private String loyaltyTier;

    private String emergencyContact;

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

}
