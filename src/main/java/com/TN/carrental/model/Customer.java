package com.TN.carrental.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "CustomerName", nullable = false, length = 100)
    private String customerName;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "DateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "IdentityCard", nullable = false, unique = true, length = 20)
    private String identityCard; // CMND/CCCD

    @Column(name = "LicenseNumber", nullable = false, unique = true, length = 20)
    private String licenseNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "LicenseIssueDate", nullable = false)
    private Date licenseIssueDate;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;
    
    // Mật khẩu nên được lưu ở Account, nhưng đề bài yêu cầu ở Customer
    // Hãy xem xét lại logic này, thông thường nó nên ở Account
    @Column(name = "Password", nullable = false, length = 255)
    private String password; 

    // Mối quan hệ 1-1 với Account
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountID", nullable = false, unique = true)
    private Account account;

    // Mối quan hệ 1-Nhiều với CarRental
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CarRental> rentals;

    // Mối quan hệ 1-Nhiều với Review
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;
    
    // Getters and Setters...
}