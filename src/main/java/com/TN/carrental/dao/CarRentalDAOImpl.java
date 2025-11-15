package com.TN.carrental.dao;

import com.TN.carrental.model.CarRental;
import com.TN.carrental.model.util.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class CarRentalDAOImpl extends GenericDAOImpl<CarRental, Integer> implements CarRentalDAO {

    @Override
    public List<CarRental> findRentalsByDateRange(Date startDate, Date endDate, boolean descending) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // (1) Xây dựng câu lệnh HQL
            // "CarRental" là tên Class, "pickupDate" là tên thuộc tính
            // "cr" là tên bí danh (alias)
            String hql = "FROM CarRental cr " +
                         "WHERE cr.pickupDate >= :startDate AND cr.pickupDate <= :endDate " +
                         "ORDER BY cr.pickupDate " + (descending ? "DESC" : "ASC");
            
            // (2) Tạo Query từ câu HQL
            Query query = session.createQuery(hql, CarRental.class);
            
            // (3) Gán tham số (parameters)
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            
            // (4) Lấy và trả về danh sách kết quả
            return query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Hoặc trả về một danh sách rỗng: new ArrayList<>()
        }
    }
}