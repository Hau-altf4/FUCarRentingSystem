package com.TN.carrental.dao;

import java.io.Serializable;
import java.util.List;

// T là kiểu Entity (ví dụ: Customer), ID là kiểu của Khóa chính (ví dụ: Integer)
public interface GenericDAO<T, ID extends Serializable> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(ID id);

    List<T> findAll();
}