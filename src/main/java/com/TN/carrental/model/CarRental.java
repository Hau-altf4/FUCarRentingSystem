package com.TN.carrental.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "car_rentals")
public class CarRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RentalID")
    private int rentalId; // Khóa chính tự tăng

    // Mối quan hệ Nhiều-1 với Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    // Mối quan hệ Nhiều-1 với Car
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarID", nullable = false)
    private Car car;

    @Temporal(TemporalType.DATE)
    @Column(name = "PickupDate", nullable = false)
    private Date pickupDate; // Ngày nhận

    @Temporal(TemporalType.DATE)
    @Column(name = "ReturnDate", nullable = false)
    private Date returnDate; // Ngày trả

    @Column(name = "RentalPrice", nullable = false)
    private double rentalPrice; // Giá thuê tại thời điểm thuê

    @Column(name = "Status", nullable = false, length = 50)
    private String status; // Ví dụ: "Booked", "Completed", "Cancelled"

    // Điều kiện: PickupDate < ReturnDate
    // Điều này nên được kiểm tra ở tầng Business Logic (khi bạn tạo/cập nhật)
    // Bạn có thể thêm @PrePersist và @PreUpdate để kiểm tra
    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (pickupDate != null && returnDate != null && !pickupDate.before(returnDate)) {
            throw new IllegalArgumentException("Ngày nhận xe phải trước ngày trả xe.");
        }
    }

    // Getters and Setters...
}