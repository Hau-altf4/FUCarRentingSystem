package com.TN.carrental.model;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "car_producers")
public class CarProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProducerID")
    private int producerId;

    @Column(name = "ProducerName", nullable = false, length = 100)
    private String producerName;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;

    @Column(name = "Country", nullable = false, length = 50)
    private String country;

    // Mối quan hệ 1-Nhiều với Car
    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Car> cars;

    // Getters and Setters...
}