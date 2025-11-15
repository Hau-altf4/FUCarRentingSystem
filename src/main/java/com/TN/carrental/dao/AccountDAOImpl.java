package com.TN.carrental.dao;

import org.hibernate.Session;
import com.TN.carrental.model.Account;
import com.TN.carrental.model.util.HibernateUtil;
import javax.persistence.Query;

public class AccountDAOImpl extends GenericDAOImpl<Account, Integer> implements AccountDAO {

    @Override
    public Account findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Dùng HQL với tham số
            Query query = session.createQuery("FROM Account WHERE username = :username", Account.class);
            query.setParameter("username", username);
            
            // Dùng getSingleResult() vì username là unique
            // Dùng try-catch vì nó ném lỗi nếu không tìm thấy
            try {
                return (Account) query.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                return null; // Không tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}