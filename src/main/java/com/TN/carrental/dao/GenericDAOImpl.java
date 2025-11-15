package com.TN.carrental.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.TN.carrental.model.util.HibernateUtil; // Import file bạn đã tạo
import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        // Đoạn code này dùng "Reflection" để tự động lấy kiểu T (ví dụ: Customer.class)
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(persistentClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Dùng HQL (Hibernate Query Language)
            // "FROM " + persistentClass.getName() sẽ trở thành "FROM Customer"
            return session.createQuery("FROM " + persistentClass.getName(), persistentClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}