package com.TN.carrental.dao;

import org.hibernate.Session;
import com.TN.carrental.model.Customer;
import com.TN.carrental.model.util.HibernateUtil;
import javax.persistence.Query;

// (1) Kế thừa GenericDAOImpl và triển khai CustomerDAO
public class CustomerDAOImpl extends GenericDAOImpl<Customer, Integer> implements CustomerDAO {

    // (2) Triển khai phương thức findByEmail
    @Override
    public Customer findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Câu lệnh HQL, "Customer" là tên Class (Entity)
            // "email" là tên thuộc tính (property) trong Class
            Query query = session.createQuery("FROM Customer WHERE email = :emailParam", Customer.class);
            query.setParameter("emailParam", email); // Gán tham số
            
            try {
                return (Customer) query.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                return null; // Không tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // (3) Triển khai phương thức findByIdentityCard
    @Override
    public Customer findByIdentityCard(String identityCard) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Tương tự, "identityCard" là tên thuộc tính trong Class Customer
            Query query = session.createQuery("FROM Customer WHERE identityCard = :idCardParam", Customer.class);
            query.setParameter("idCardParam", identityCard);
            
            try {
                return (Customer) query.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                return null; // Không tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}