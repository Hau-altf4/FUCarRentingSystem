package com.TN.carrental.dao;

import com.TN.carrental.model.CarRental;
import java.util.Date;
import java.util.List;

// Kế thừa GenericDAO, chỉ định Entity là CarRental và Kiểu ID là Integer
public interface CarRentalDAO extends GenericDAO<CarRental, Integer> {

    /**
     * Phương thức đặc thù cho Báo cáo Thống kê.
     * @param startDate Ngày bắt đầu
     * @param endDate Ngày kết thúc
     * @param descending Sắp xếp giảm dần (true) hay tăng dần (false)
     * @return Danh sách các giao dịch thuê xe
     */
    List<CarRental> findRentalsByDateRange(Date startDate, Date endDate, boolean descending);
    
    // Bạn có thể thêm các phương thức khác sau này, ví dụ:
    // List<CarRental> findByCustomerId(int customerId);
}