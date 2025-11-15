package com.TN.carrental.service;

import com.TN.carrental.dao.AccountDAO;
import com.TN.carrental.dao.AccountDAOImpl;
import com.TN.carrental.model.Account;
// Import thêm thư viện hash mật khẩu nếu dùng, ví dụ: BCrypt
// import org.mindrot.jbcrypt.BCrypt;

public class AccountService {

    // Service sẽ sở hữu (khởi tạo) một đối tượng DAO
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAOImpl();
    }

    /**
     * Logic nghiệp vụ: Kiểm tra đăng nhập
     * @return Trả về đối tượng Account nếu thành công, null nếu thất bại
     */
    public Account login(String username, String password) {
        // Bước 1: Lấy account từ CSDL qua DAO
        Account account = accountDAO.findByUsername(username);

        // Bước 2: Xử lý logic nghiệp vụ
        if (account != null) {
            // Nếu tìm thấy account, kiểm tra password
            // LƯU Ý: Trong dự án thực tế, KHÔNG BAO GIỜ lưu mật khẩu dạng
            // plaintext. Bạn phải HASH mật khẩu (ví dụ: dùng BCrypt).
            // Ví dụ với BCrypt: if (BCrypt.checkpw(password, account.getPassword()))
            
            // Tạm thời cho bài test, ta dùng so sánh chuỗi:
            if (account.getPassword().equals(password)) { // Đổi 'getPassword()' thành getter của bạn
                return account; // Đăng nhập thành công
            }
        }
        
        return null; // Sai username hoặc password
    }

    // Các logic khác: tạo tài khoản, đổi mật khẩu...
    public Account createAccount(String username, String password, String role) {
        // Logic nghiệp vụ: Kiểm tra username đã tồn tại chưa?
        if (accountDAO.findByUsername(username) != null) {
            // Hoặc ném ra một Exception tùy chỉnh (ví dụ: UsernameExistsException)
            return null; 
        }
        
        // Logic nghiệp vụ: Hash mật khẩu
        // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password); // Tạm thời. Nên dùng: setPassword(hashedPassword)
        newAccount.setRole(role);
        
        // Dùng DAO để lưu
        accountDAO.save(newAccount);
        return newAccount;
    }
}