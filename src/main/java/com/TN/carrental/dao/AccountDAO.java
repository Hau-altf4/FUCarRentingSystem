package com.TN.carrental.dao;

import com.TN.carrental.model.Account;

public interface AccountDAO extends GenericDAO<Account, Integer> {
    
    // Thêm các phương thức đặc thù nếu cần, ví dụ:
    Account findByUsername(String username);
}