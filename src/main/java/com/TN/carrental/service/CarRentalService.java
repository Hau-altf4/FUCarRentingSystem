package com.TN.carrental.service;

import com.TN.carrental.dao.CarDAO;
import com.TN.carrental.dao.CarDAOImpl;
import com.TN.carrental.dao.CarRentalDAO;
import com.TN.carrental.dao.CarRentalDAOImpl;
import com.TN.carrental.dao.CustomerDAO;
import com.TN.carrental.dao.CustomerDAOImpl;
import com.TN.carrental.model.Car;
import com.TN.carrental.model.CarRental;
import com.TN.carrental.model.Customer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.hibernate.Session;
import org.hibernate.Transaction;
// Đảm bảo đường dẫn này chính xác tới file HibernateUtil của bạn
import com.TN.carrental.model.util.HibernateUtil; 


public class CarRentalService {

    private CarRentalDAO carRentalDAO;
    private CarDAO carDAO;
    private CustomerDAO customerDAO;
    
    public CarRentalService() {
        this.carRentalDAO = new CarRentalDAOImpl();
        this.carDAO = new CarDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
    }

    /**
     * Logic nghiệp vụ: Tạo một giao dịch thuê xe mới.
     * (Giữ nguyên code của bạn...)
     */
    public boolean createRental(int customerId, int carId, Date pickupDate, Date returnDate) {
        // 1. Kiểm tra logic ngày
        if (!pickupDate.before(returnDate)) {
            System.err.println("Logic Lỗi: Ngày nhận phải trước ngày trả.");
            return false;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // 2. Lấy đối tượng
            Car car = session.get(Car.class, carId);
            Customer customer = session.get(Customer.class, customerId);

            // 3. Kiểm tra logic xe
            if (car == null || customer == null) {
                throw new Exception("Không tìm thấy xe hoặc khách hàng.");
            }
            if (!car.getStatus().equalsIgnoreCase("Available")) {
                throw new Exception("Xe này hiện không có sẵn.");
            }

            // 4. Tính giá
            long diffInMillies = Math.abs(returnDate.getTime() - pickupDate.getTime());
            long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (days == 0) days = 1; // Thuê trong ngày vẫn tính 1 ngày
            double totalPrice = car.getRentalPrice() * days;

            // 5. Cập nhật trạng thái xe
            car.setStatus("Rented");
            session.update(car); // Cập nhật xe

            // 6. Tạo và lưu giao dịch
            CarRental rental = new CarRental();
            rental.setCustomer(customer);
            rental.setCar(car);
            rental.setPickupDate(pickupDate);
            rental.setReturnDate(returnDate);
            rental.setRentalPrice(totalPrice);
            rental.setStatus("Booked"); // Trạng thái mới là "Đã đặt"
            
            session.save(rental); // Lưu giaoịch

            // 7. Hoàn tất
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    // --- PHẦN MỚI BẠN VỪA GHÉP VÀO ---

    /**
     * Logic nghiệp vụ: Lấy báo cáo thống kê các giao dịch.
     * Sắp xếp theo ngày nhận xe giảm dần.
     */
    public List<CarRental> getStatisticsReport(Date startDate, Date endDate) {
        // Service có thể thêm logic ở đây, ví dụ kiểm tra
        // endDate phải sau startDate, hoặc set mặc định nếu null...
        
        // Gọi DAO để lấy dữ liệu, sắp xếp giảm dần (true)
        // (Điều này yêu cầu carRentalDAO phải có phương thức findRentalsByDateRange)
        return carRentalDAO.findRentalsByDateRange(startDate, endDate, true);
    }
    
} // <-- Dấu ngoặc đóng của class CarRentalService