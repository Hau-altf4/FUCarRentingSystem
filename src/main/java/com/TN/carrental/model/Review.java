package com.TN.carrental.model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int reviewId; // Khóa chính tự tăng

    // Mối quan hệ Nhiều-1 với Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    // Mối quan hệ Nhiều-1 với Car
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Column(name = "Rating", nullable = false)
    private int rating; // Số sao (ví dụ: 1 đến 5)

    @Column(name = "Comment", nullable = true, length = 1000) // Bình luận có thể null
    private String comment;

    // Getters and Setters...
}