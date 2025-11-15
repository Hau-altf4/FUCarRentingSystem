package com.TN.carrental.dao;

import com.TN.carrental.model.Customer;

// (1) Kế thừa GenericDAO, chỉ định Entity là Customer và Kiểu ID là Integer
public interface CustomerDAO extends GenericDAO<Customer, Integer> {

    // (2) Đây là các phương thức đặc thù chỉ Customer mới có
    // Dùng để kiểm tra trùng lặp khi đăng ký
    
    Customer findByEmail(String email);
    
    Customer findByIdentityCard(String identityCard);
}