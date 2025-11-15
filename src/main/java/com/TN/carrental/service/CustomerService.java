package com.TN.carrental.service;

import com.TN.carrental.dao.AccountDAO;
import com.TN.carrental.dao.AccountDAOImpl;
import com.TN.carrental.dao.CustomerDAO;
import com.TN.carrental.dao.CustomerDAOImpl;
import com.TN.carrental.model.Account;
import com.TN.carrental.model.Customer;

public class CustomerService {

    private CustomerDAO customerDAO;
    private AccountDAO accountDAO; // CustomerService cần cả AccountDAO

    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
        this.accountDAO = new AccountDAOImpl();
    }

    /**
     * Logic nghiệp vụ: Đăng ký một khách hàng mới.
     * Thao tác này liên quan đến 2 bảng (Account và Customer)
     * => Cần dùng Transaction (Giao dịch)
     */
    public boolean registerCustomer(Customer customer, String username, String password) {
        // Logic: Kiểm tra xem username hoặc email/CCCD đã tồn tại chưa
        if (accountDAO.findByUsername(username) != null) {
            System.err.println("Tên đăng nhập đã tồn tại!");
            return false;
        }
        if (customerDAO.findByEmail(customer.getEmail()) != null) { // Giả sử bạn đã tạo findByEmail
            System.err.println("Email đã tồn tại!");
            return false;
        }

        // Tạo tài khoản trước
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password); // Nhớ HASH mật khẩu này
        account.setRole("Customer");
        
        // Gán tài khoản này cho khách hàng
        customer.setAccount(account);
        
        // Vì Customer có cascade = CascadeType.ALL với Account
        // (như tôi hướng dẫn ở phần Entity trước)
        // Bạn chỉ cần lưu Customer, Hibernate sẽ tự động lưu Account.
        try {
            customerDAO.save(customer); // Thao tác này tự động lưu cả Account
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Các logic khác: cập nhật thông tin khách hàng, lấy lịch sử thuê xe...
}