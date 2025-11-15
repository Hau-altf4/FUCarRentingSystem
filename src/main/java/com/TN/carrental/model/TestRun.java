// Đảm bảo package khớp với vị trí bạn tạo file
package com.TN.carrental.model; 

// QUAN TRỌNG: Import 2 lớp này
import org.hibernate.Session;
import com.TN.carrental.model.util.HibernateUtil; // Import class bạn đã tạo ở bước trước

public class TestRun {

    public static void main(String[] args) {
        System.out.println("Đang thử khởi tạo SessionFactory...");
        
        // Bắt lỗi kiểu 'Throwable' sẽ an toàn hơn 
        // vì nó bao gồm cả 'ExceptionInInitializerError' (lỗi phổ biến khi Hibernate fail)
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            System.out.println("----------------------------------------");
            System.out.println("CẤU HÌNH HIBERNATE THÀNH CÔNG!");
            System.out.println("Session đã được mở: " + session);
            System.out.println("Kết nối CSDL thành công.");
            System.out.println("----------------------------------------");

        } catch (Throwable e) {
            System.err.println("----------------------------------------");
            System.err.println("CẤU HÌNH HIBERNATE THẤT BẠI!");
            System.err.println("Lỗi trong quá trình khởi tạo SessionFactory.");
            System.err.println("----------------------------------------");
            // In ra chi tiết lỗi để kiểm tra
            e.printStackTrace();
        }
    }
}