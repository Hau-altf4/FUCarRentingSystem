package com.TN.carrental.dao;

import com.TN.carrental.model.Car;
import com.TN.carrental.model.util.HibernateUtil;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class CarDAOImpl extends GenericDAOImpl<Car, Integer> implements CarDAO {

    @Override
    public List<Car> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            Query query = session.createQuery("FROM Car WHERE status = :status", Car.class);
            query.setParameter("status", status);
            return query.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}