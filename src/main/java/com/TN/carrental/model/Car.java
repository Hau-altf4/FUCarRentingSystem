package com.TN.carrental.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID")
    private int carId;

    @Column(name = "CarName", nullable = false, length = 100)
    private String carName;

    @Column(name = "ModelYear", nullable = false, length = 4)
    private String modelYear;

    @Column(name = "Color", nullable = false, length = 30)
    private String color;

    @Column(name = "Capacity", nullable = false)
    private int capacity; // Số chỗ

    @Column(name = "Description", nullable = false, length = 500)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "ImportDate", nullable = false)
    private Date importDate;

    @Column(name = "RentalPrice", nullable = false)
    private double rentalPrice; // Giá thuê

    @Column(name = "Status", nullable = false, length = 50)
    private String status; // Ví dụ: "Available", "Rented", "Maintenance"

    // Mối quan hệ Nhiều-1 với CarProducer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProducerID", nullable = false)
    private CarProducer producer;

    // Mối quan hệ 1-Nhiều với CarRental
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CarRental> rentals;

    // Mối quan hệ 1-Nhiều với Review
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    // Getters and Setters...
}