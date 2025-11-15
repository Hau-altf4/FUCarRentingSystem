package com.TN.carrental.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountId;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "Role", nullable = false, length = 20)
    private String role; // Ví dụ: "Admin", "Customer"

    // Mối quan hệ 1-1 với Customer
    // 'mappedBy = "account"' nghĩa là Customer là bên quản lý mối quan hệ này
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    // Getters and Setters...
}