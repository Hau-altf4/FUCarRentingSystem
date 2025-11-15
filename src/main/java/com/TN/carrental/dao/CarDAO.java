package com.TN.carrental.dao;

import com.TN.carrental.model.Car;
import java.util.List;

public interface CarDAO extends GenericDAO<Car, Integer> {

    // Hàm để tìm xe theo trạng thái (ví dụ: "Available")
    List<Car> findByStatus(String status);
    
    // Bạn có thể thêm các hàm tìm kiếm khác (theo tên, theo hãng...)
}